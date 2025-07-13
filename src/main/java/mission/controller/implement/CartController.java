package mission.controller.implement;

import java.util.List;
import mission.controller.Controller;
import mission.domain.budget.TotalBudget;
import mission.domain.budget.utils.BudgetPerTypeParser;
import mission.domain.cart.CartResultDto;
import mission.domain.cart.CartService;
import mission.domain.budget.BudgetPerType;
import mission.ui.InputView;
import mission.ui.OutputView;

public class CartController implements Controller {
    private final CartService cartService;
    private final InputView inputView;
    private final OutputView outputView;

    public CartController(CartService cartService, InputView inputView, OutputView outputView) {
        this.cartService = cartService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    @Override
    public void run() {
        try {
            int budgetValue = inputView.inputTotalBudget();
            TotalBudget totalBudget = new TotalBudget(budgetValue);

            BudgetPerType budgetPerType = receiveBudgetPerType(totalBudget);

            List<Integer> lectureIds = inputView.inputLectureIds();

            CartResultDto result = cartService.processCart(totalBudget, budgetPerType, lectureIds);

            printResult(result);
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
        }
    }

    private BudgetPerType receiveBudgetPerType(TotalBudget totalBudget) {
        String typeBudgetInput = inputView.inputTypeBudgets();
        return BudgetPerTypeParser.parse(typeBudgetInput, totalBudget.value());
    }

    private void printResult(CartResultDto result) {
        if (result.overBudget()) {
            outputView.printOverBudget(result);
        } else {
            outputView.printWithinBudget(result);
        }
    }

}
