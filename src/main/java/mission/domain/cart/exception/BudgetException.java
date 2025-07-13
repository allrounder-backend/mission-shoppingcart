package mission.domain.cart.exception;

public class BudgetException extends IllegalArgumentException {
    public BudgetException(BudgetError error) {
        super(error.getMessage());
    }
}
