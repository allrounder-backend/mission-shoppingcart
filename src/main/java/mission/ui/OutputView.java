package mission.ui;

import mission.domain.cart.CartResultDto;

public interface OutputView {
    void printOverBudget(CartResultDto result);
    void printWithinBudget();
    void printError(Exception e);
}
