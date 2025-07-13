package mission.domain.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mission.domain.budget.BudgetPerType;
import mission.domain.budget.TotalBudget;
import mission.domain.lecture.Lecture;
import mission.domain.lecture.LectureRepository;
import mission.domain.lecture.LectureType;

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
        Cart cart = new Cart(lectures);

        int totalPrice = cart.calculateTotalPrice();
        boolean overTotal = totalBudget.isOver(totalPrice);
        int excessTotalAmount = totalBudget.excessAmount(totalPrice);

        Map<LectureType, Integer> priceByType = calculatePriceByType(lectures);
        Map<LectureType, Integer> exceededTypes = calculateExceededTypes(budgetPerType, priceByType);
        boolean overAny = overTotal || !exceededTypes.isEmpty();

        return new CartResultDto(overAny, overTotal, excessTotalAmount, exceededTypes, budgetPerType);
    }

    private Map<LectureType, Integer> calculatePriceByType(List<Lecture> lectures) {
        return lectures.stream()
                .collect(Collectors.groupingBy(
                        Lecture::type,
                        Collectors.summingInt(Lecture::price)
                ));
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
