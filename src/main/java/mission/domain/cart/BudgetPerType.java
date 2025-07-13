package mission.domain.cart;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import mission.domain.cart.exception.BudgetOverTotalException;
import mission.domain.cart.exception.DuplicateBudgetTypeException;
import mission.domain.cart.exception.InvalidBudgetFormatException;
import mission.domain.cart.exception.NegativeBudgetException;
import mission.domain.lecture.LectureType;

public class BudgetPerType {
    private final Map<LectureType, Integer> map;

    public BudgetPerType(String input, int totalBudget) {
        this.map = parse(input);
        validateTotalUnderLimit(totalBudget);
    }

    private Map<LectureType, Integer> parse(String input) {
        if (input == null || input.isBlank()) return Map.of();

        Map<LectureType, Integer> result = new HashMap<>();

        for (String part : input.split(",")) {
            String[] arr = part.split("-", 2);
            if (arr.length != 2) {
                throw new InvalidBudgetFormatException();
            }

            LectureType type = LectureType.from(arr[0]);
            int value;

            try {
                value = Integer.parseInt(arr[1]);
            } catch (NumberFormatException e) {
                throw new InvalidBudgetFormatException();
            }

            if (value < 0) throw new NegativeBudgetException();
            if (result.containsKey(type)) throw new DuplicateBudgetTypeException();

            result.put(type, value);
        }

        return result;
    }

    private void validateTotalUnderLimit(int totalBudget) {
        int typeSum = map.values().stream().mapToInt(Integer::intValue).sum();
        if (typeSum > totalBudget) {
            throw new BudgetOverTotalException();
        }
    }

    public Optional<Integer> get(LectureType type) {
        return Optional.ofNullable(map.get(type));
    }

    public Set<LectureType> types() {
        return map.keySet();
    }
}
