package mission.domain.cart;

import java.util.List;
import mission.domain.lecture.Lecture;
import mission.domain.lecture.LectureRepository;

public class CartService {
    private final LectureRepository lectureRepository;

    public CartService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public CartResultDto processCart(int budgetValue, List<Integer> lectureIds) {
        CartBudget budget = new CartBudget(budgetValue);
        List<Lecture> lectures = lectureRepository.findByIds(lectureIds);
        Cart cart = new Cart(lectures);
        int totalPrice = cart.calculateTotalPrice();

        return CartResultDto.of(budget, totalPrice);
    }
}
