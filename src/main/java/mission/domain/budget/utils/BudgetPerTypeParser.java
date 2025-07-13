package mission.domain.budget.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import mission.domain.budget.BudgetPerType;
import mission.domain.budget.exception.DuplicateBudgetTypeException;
import mission.domain.budget.validate.BudgetValidator;
import mission.domain.lecture.LectureType;

public class BudgetPerTypeParser {
    public static BudgetPerType parse(String input, int totalBudget) {
        Map<LectureType, Integer> map = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(BudgetEntryParser::parse)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> {
                            throw new DuplicateBudgetTypeException();
                        }
                ));

        BudgetValidator.validateTotalNotExceeded(map, totalBudget);

        return new BudgetPerType(map);
    }

}
