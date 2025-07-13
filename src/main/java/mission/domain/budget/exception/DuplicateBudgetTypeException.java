package mission.domain.budget.exception;

public class DuplicateBudgetTypeException extends BudgetException {
    public DuplicateBudgetTypeException() {
        super(BudgetError.DUPLICATE_TYPE);
    }
}
