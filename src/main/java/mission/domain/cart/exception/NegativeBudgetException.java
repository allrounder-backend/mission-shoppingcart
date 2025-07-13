package mission.domain.cart.exception;

public class NegativeBudgetException extends BudgetException {
  public NegativeBudgetException() {
    super(BudgetError.NEGATIVE);
  }
}
