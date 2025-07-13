package mission.domain.budget.utils;

import mission.domain.budget.exception.InvalidBudgetFormatException;
import mission.domain.budget.validate.BudgetValidator;
import mission.domain.lecture.LectureType;

import java.util.Map;

public class BudgetEntryParser {

    public static Map.Entry<LectureType, Integer> parse(String item) {
        if (!item.contains("-")) {
            throw new InvalidBudgetFormatException();
        }

        String[] split = item.split("-", 2);

        if (split.length != 2) {
            throw new InvalidBudgetFormatException();
        }

        String type = split[0].trim();
        String amount = split[1].trim();

        return Map.entry(
                LectureType.from(type),
                BudgetValidator.parsePositiveAmount(amount)
        );
    }
}
