package mission.domain.budget.exception;

public class InvalidBudgetFormatException extends BudgetException  {
    public InvalidBudgetFormatException() {
        super(BudgetError.INVALID_FORMAT);;
    }
}
