package mission.domain.cart.exception;

public class InvalidBudgetFormatException extends BudgetException  {
    public InvalidBudgetFormatException() {
        super(BudgetError.INVALID_FORMAT);;
    }
}
