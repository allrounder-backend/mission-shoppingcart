package mission.controller.implement;

import java.util.List;
import mission.controller.Controller;
import mission.domain.cart.CartResultDto;
import mission.domain.cart.CartService;
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

    public void run() {
        int budgetValue = inputView.inputTotalBudget();
        List<Integer> lectureIds = inputView.inputLectureIds();

        CartResultDto result = cartService.processCart(budgetValue, lectureIds);

        if (result.isOverBudget()) {
            outputView.printOverBudget(result.excessAmount());
        } else {
            outputView.printWithinBudget();
        }
    }
}
