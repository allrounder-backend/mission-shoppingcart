package mission.ui;

import mission.domain.cart.CartResultDto;

public interface OutputView {
    void printOverBudget(CartResultDto result);
    void printWithinBudget(CartResultDto result);
    void printError(Exception e);
}
