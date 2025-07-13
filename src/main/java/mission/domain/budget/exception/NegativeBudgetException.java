package mission.domain.budget.exception;

public class NegativeBudgetException extends BudgetException {
  public NegativeBudgetException() {
    super(BudgetError.NEGATIVE);
  }
}
