package mission.controller.implement;

import java.util.List;
import mission.controller.Controller;
import mission.domain.cart.TotalBudget;
import mission.domain.cart.CartResultDto;
import mission.domain.cart.CartService;
import mission.domain.cart.BudgetPerType;
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

            BudgetPerType budgetPerType = new BudgetPerType(inputView.inputTypeBudgets(), budgetValue);

            List<Integer> lectureIds = inputView.inputLectureIds();

            CartResultDto result = cartService.processCart(totalBudget, budgetPerType, lectureIds);

            if (result.isOverBudget()) {
                outputView.printOverBudget(result);
            } else {
                outputView.printWithinBudget(result);
            }
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
        }
    }

}
