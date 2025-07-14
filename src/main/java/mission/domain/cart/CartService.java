package mission.domain.cart;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mission.domain.budget.BudgetPerType;
import mission.domain.budget.TotalBudget;
import mission.domain.lecture.Lecture;
import mission.domain.lecture.LectureRepository;
import mission.domain.lecture.LectureType;
import mission.domain.promotion.PromotionPolicy;
import mission.domain.promotion.PromotionPolicyFactory;

public class CartService {
    private final LectureRepository lectureRepository;

    public CartService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public CartResultDto processCart(
            TotalBudget totalBudget,
            BudgetPerType budgetPerType,
            List<Integer> lectureIds
    ) {
        List<Lecture> lectures = lectureRepository.findByIds(lectureIds);
        Map<LectureType, List<Lecture>> lecturesByType = lectures.stream()
                .collect(Collectors.groupingBy(Lecture::type));

        Map<LectureType, Integer> discountedPriceByType = applyPromotionByType(lecturesByType);
        int totalDiscountedPrice = discountedPriceByType.values().stream().mapToInt(Integer::intValue).sum();
        boolean overTotal = totalBudget.isOver(totalDiscountedPrice);
        int excessTotalAmount = totalBudget.excessAmount(totalDiscountedPrice);


        Map<LectureType, Integer> exceededTypes = calculateExceededTypes(budgetPerType, discountedPriceByType);
        boolean overAny = overTotal || !exceededTypes.isEmpty();

        return new CartResultDto(overAny, overTotal, excessTotalAmount, exceededTypes, budgetPerType);
    }

    private Map<LectureType, Integer> applyPromotionByType(Map<LectureType, List<Lecture>> lecturesByType) {
        Map<LectureType, Integer> result = new EnumMap<>(LectureType.class);

        for (Map.Entry<LectureType, List<Lecture>> entry : lecturesByType.entrySet()) {
            PromotionPolicy policy = PromotionPolicyFactory.getPolicy(entry.getKey());
            int discountedPrice = policy.apply(entry.getValue());
            result.put(entry.getKey(), discountedPrice);
        }

        return result;
    }


    private Map<LectureType, Integer> calculateExceededTypes(
            BudgetPerType budgetPerType,
            Map<LectureType, Integer> priceByType
    ) {
        return budgetPerType.types().stream()
                .filter(type -> priceByType.getOrDefault(type, 0) > budgetPerType.get(type).orElse(0))
                .collect(Collectors.toMap(
                        type -> type,
                        type -> priceByType.getOrDefault(type, 0) - budgetPerType.get(type).orElse(0)
                ));
    }

}
