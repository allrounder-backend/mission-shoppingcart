package mission.domain.cart.exception;

public class BudgetOverTotalException extends BudgetException {
    public BudgetOverTotalException() {
        super(BudgetError.OVER_TOTAL);
    }
}
