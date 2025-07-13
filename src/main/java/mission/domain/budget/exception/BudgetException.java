package mission.domain.budget.exception;

public class BudgetException extends IllegalArgumentException {
    public BudgetException(BudgetError error) {
        super(error.getMessage());
    }
}
