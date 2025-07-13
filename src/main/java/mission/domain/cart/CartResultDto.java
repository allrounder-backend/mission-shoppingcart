package mission.domain.cart;

import java.util.Map;
import mission.domain.budget.BudgetPerType;
import mission.domain.lecture.LectureType;

public class CartResultDto {
    private final boolean overBudget;
    private final boolean totalOver;
    private final int excessAmount;
    private final Map<LectureType, Integer> exceededTypes;
    private final BudgetPerType budgetPerType;

    public CartResultDto(boolean overBudget, boolean totalOver, int excessAmount,
                         Map<LectureType, Integer> exceededTypes,
                         BudgetPerType budgetPerType) {
        this.overBudget = overBudget;
        this.totalOver = totalOver;
        this.excessAmount = excessAmount;
        this.exceededTypes = exceededTypes;
        this.budgetPerType = budgetPerType;
    }

    public boolean isOverBudget() {
        return overBudget;
    }

    public boolean isTotalOver() {
        return totalOver;
    }

    public int excessAmount() {
        return excessAmount;
    }

    public Map<LectureType, Integer> getExceededTypes() {
        return exceededTypes;
    }

    public BudgetPerType getBudgetPerType() {
        return budgetPerType;
    }
}
