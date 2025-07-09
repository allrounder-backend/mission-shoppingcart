package mission.controller;

import java.util.List;
import mission.domain.cart.Budget;
import mission.domain.cart.Cart;
import mission.domain.lecture.Lecture;
import mission.domain.lecture.LectureRepository;
import mission.ui.InputView;
import mission.ui.OutputView;

public class CartController implements Controller {
    private final LectureRepository lectureRepository;
    private final InputView inputView;
    private final OutputView outputView;

    public CartController(LectureRepository lectureRepository, InputView inputView, OutputView outputView) {
        this.lectureRepository = lectureRepository;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int budgetValue = inputView.inputTotalBudget();
        Budget budget = new Budget(budgetValue);

        List<Integer> lectureIds = inputView.inputLectureIds();
        List<Lecture> lectures = lectureRepository.findByIds(lectureIds);
        Cart cart = new Cart(lectures);

        int totalPrice = cart.calculateTotalPrice();

        if (budget.isOver(totalPrice)) {
            outputView.printOverBudget(budget.excessAmount(totalPrice));
        } else {
            outputView.printWithinBudget();
        }
    }
}
