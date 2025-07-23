package mission.controller;

import java.util.List;
import mission.domain.Budget;
import mission.domain.PurchasingInfo;
import mission.domain.GroupedPricesByType;
import mission.dto.BudgetCalculationDTO;
import mission.service.RepositoryInitializingService;
import mission.utility.DebugLogging;
import mission.service.ShoppingCartCalculationService;
import mission.view.InputView;
import mission.view.OutputView;

public class ShoppingCartController {
    InputView inputView;
    OutputView outputView;
    ShoppingCartCalculationService shoppingCartCalculationService;
    RepositoryInitializingService repositoryInitializingService;

    public ShoppingCartController(
            InputView inputView,
            OutputView outputView,
            ShoppingCartCalculationService shoppingCartCalculationService,
            RepositoryInitializingService repositoryInitializingService
    ){
        this.inputView = inputView;
        this.outputView = outputView;
        this.shoppingCartCalculationService = shoppingCartCalculationService;
        this.repositoryInitializingService = repositoryInitializingService;
    }

    public void initializingData() throws Exception {
        try{
            repositoryInitializingService.initializingLecture();
            repositoryInitializingService.initializingPromotion();

        } catch (Exception e){
            DebugLogging.addTrace(this.getClass().getName());
            throw e;
        }
    }

    public void inputBudget(Budget userBudget){
        try {
            int totalBudget = inputView.inputTotalBudget();
            List<GroupedPricesByType> budgetPerType = inputView.inputTypeBudget();

            shoppingCartCalculationService.setBudgets(userBudget, totalBudget, budgetPerType);

        } catch(Exception e){
            DebugLogging.addTrace(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
            DebugLogging.addTrace(this.getClass().getName());
            throw e;
        }
    }

    public void inputPurchaseLectureList(PurchasingInfo userPurchasingInfo){
        try {
            List<Integer> purchaseList = inputView.inputLectureList();
            shoppingCartCalculationService.setPurchaseList(userPurchasingInfo, purchaseList);

        } catch(Exception e){
            DebugLogging.addTrace(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
            DebugLogging.addTrace(this.getClass().getName());
            throw e;
        }
    }

    public void outputResult(Budget userBudget, PurchasingInfo userPurchasingInfo){
        try {
            BudgetCalculationDTO response = shoppingCartCalculationService.calculateWithPromotion(userBudget, userPurchasingInfo);
            outputView.outputBudgetCalculationResult(response);

        } catch(Exception e){
            DebugLogging.addTrace(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
            DebugLogging.addTrace(this.getClass().getName());
            throw e;
        }
    }
}
