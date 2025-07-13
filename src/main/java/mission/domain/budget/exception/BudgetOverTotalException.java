package mission.domain.budget.exception;

public class BudgetOverTotalException extends BudgetException {
    public BudgetOverTotalException() {
        super(BudgetError.OVER_TOTAL);
    }
}
