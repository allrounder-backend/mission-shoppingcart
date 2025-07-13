package mission.domain.budget.validate;

import java.util.Map;
import mission.domain.budget.exception.BudgetOverTotalException;
import mission.domain.budget.exception.InvalidBudgetFormatException;
import mission.domain.budget.exception.NegativeBudgetException;
import mission.domain.lecture.LectureType;

public class BudgetValidator {
    public static int parsePositiveAmount(String raw) {
        try {
            int value = Integer.parseInt(raw);
            if (value < 0) throw new NegativeBudgetException();
            return value;
        } catch (NumberFormatException e) {
            throw new InvalidBudgetFormatException();
        }
    }

    public static void validateTotalNotExceeded(Map<LectureType, Integer> map, int total) {
        int sum = map.values().stream().mapToInt(Integer::intValue).sum();
        if (sum > total) throw new BudgetOverTotalException();
    }
}
