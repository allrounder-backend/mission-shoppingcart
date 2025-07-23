package mission.service;

import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import mission.domain.Budget;
import mission.domain.Lecture;
import mission.domain.PurchasingInfo;
import mission.domain.Type;
import mission.domain.GroupedPricesByType;
import mission.dto.BudgetCalculationDTO;
import mission.dto.DifferenceResultDTO;
import mission.repository.LectureRepository;
import mission.repository.PromotionRepository;
import mission.utility.DebugLogging;
import mission.message.ExceptionMessage;
import mission.validator.BudgetValidator;

public class ShoppingCartCalculationService {
    LectureRepository lectureRepository;
    PromotionRepository promotionRepository;

    public ShoppingCartCalculationService(
            LectureRepository lectureRepository,
            PromotionRepository promotionRepository
    ){
        this.lectureRepository = lectureRepository;
        this.promotionRepository = promotionRepository;
    }
    public void setBudgets(Budget budget, int totalBudget, List<GroupedPricesByType> budgetPerType){
        if(!BudgetValidator.isTotalBudgetValid(totalBudget)
            ||!BudgetValidator.isTypeBudgetInBound(totalBudget, budgetPerType)){
            DebugLogging.addTrace(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
            DebugLogging.addTrace(this.getClass().getName());
            throw new IllegalArgumentException(ExceptionMessage.INVALID_MONEY);
        }
        budget.setBudget(totalBudget, budgetPerType);
    }

    public void setPurchaseList(PurchasingInfo purchasingInfo, List<Integer> purchaseList){
        for(int id : purchaseList){
            Lecture lecture = lectureRepository.getLecture(id);
            purchasingInfo.add(lecture.getType(), lecture.getPrice());
        }
    }

    public BudgetCalculationDTO calculateWithPromotion(Budget budget, PurchasingInfo purchasingInfo){
        List<GroupedPricesByType> resultList = new ArrayList<>(budget.getBudgetPerType());
        int expectedTotal = 0;
        boolean overallSuccess = true;

        for(GroupedPricesByType item : resultList){
            DifferenceResultDTO typeResult = calculatePerType(item, purchasingInfo);
            expectedTotal += typeResult.expectedPrice();
            if (typeResult.overBudget()){
               overallSuccess=false;
            }
            item.resetPriceList(typeResult.difference());
        }

        DifferenceResultDTO totalResult = adjustIfOver(budget.getTotalBudget()-expectedTotal, expectedTotal);
        if(totalResult.overBudget()){
            overallSuccess=false;
        }

        return new BudgetCalculationDTO(overallSuccess, totalResult.difference(), resultList);
    }

    private DifferenceResultDTO calculatePerType(GroupedPricesByType item, PurchasingInfo purchasingInfo){
        Type type = item.getType();
        Function<GroupedPricesByType, Integer> promotion = promotionRepository.findByType(type);
        GroupedPricesByType targetGroup = purchasingInfo.getGroupedPrices(type);
        int expectedPrice = promotion.apply(targetGroup);

        return adjustIfOver(item.getFirstPrice()-expectedPrice, expectedPrice);
    }

    private DifferenceResultDTO adjustIfOver(int difference, int expectedPrice) {
        if (difference < 0) {
            return new DifferenceResultDTO(Math.abs(difference), expectedPrice,true);
        }
        return new DifferenceResultDTO(0, expectedPrice,false);
    }
}
