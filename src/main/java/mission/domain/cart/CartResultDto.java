package mission.domain.cart;

import java.util.Map;
import mission.domain.budget.BudgetPerType;
import mission.domain.lecture.LectureType;

public record CartResultDto(
        boolean overBudget,
        boolean totalOver,
        int excessAmount,
        Map<LectureType, Integer> exceededTypes,
        BudgetPerType budgetPerType
) {}
