package mission.domain.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mission.domain.lecture.Lecture;
import mission.domain.lecture.LectureRepository;
import mission.domain.lecture.LectureType;

public class CartService {
    private final LectureRepository lectureRepository;

    public CartService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public CartResultDto processCart(CartBudget cartBudget, BudgetPerType budgetPerType, List<Integer> lectureIds) {
        List<Lecture> lectures = lectureRepository.findByIds(lectureIds);
        Cart cart = new Cart(lectures);

        int totalPrice = cart.calculateTotalPrice();
        boolean overTotal = cartBudget.isOver(totalPrice);
        int excessTotalAmount = cartBudget.excessAmount(totalPrice);

        Map<LectureType, Integer> priceByType = lectures.stream()
                .collect(Collectors.groupingBy(
                        Lecture::type,
                        Collectors.summingInt(Lecture::price)
                ));

        Map<LectureType, Integer> exceededTypes = new HashMap<>();
        for (LectureType type : budgetPerType.types()) {
            int used = priceByType.getOrDefault(type, 0);
            int limit = budgetPerType.get(type).orElse(0);
            if (used > limit) {
                exceededTypes.put(type, used - limit);
            }
        }

        boolean overAny = overTotal || !exceededTypes.isEmpty();
        return new CartResultDto(overAny, overTotal, excessTotalAmount, exceededTypes, budgetPerType);

    }
}
