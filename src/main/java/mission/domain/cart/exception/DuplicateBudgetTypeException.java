package mission.domain.cart.exception;

public class DuplicateBudgetTypeException extends BudgetException {
    public DuplicateBudgetTypeException() {
        super(BudgetError.DUPLICATE_TYPE);
    }
}
