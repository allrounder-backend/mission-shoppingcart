package mission.domain.cart;

import java.util.List;
import mission.domain.lecture.Lecture;
import mission.domain.lecture.LectureRepository;

public class CartService {
    private final LectureRepository lectureRepository;

    public CartService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public CartResultDto processCart(CartBudget cartBudget, List<Integer> lectureIds) {
        List<Lecture> lectures = lectureRepository.findByIds(lectureIds);
        Cart cart = new Cart(lectures);
        int totalPrice = cart.calculateTotalPrice();

        boolean overBudget = cartBudget.isOver(totalPrice);
        int excessAmount = overBudget ? cartBudget.excessAmount(totalPrice) : 0;

        return new CartResultDto(overBudget, excessAmount);
    }
}
