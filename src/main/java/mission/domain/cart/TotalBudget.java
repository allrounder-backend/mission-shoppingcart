package mission.domain.cart;

import mission.domain.cart.exception.NegativeBudgetException;

public record TotalBudget(int value) {
    public TotalBudget {
        if (value < 0) {
            throw new NegativeBudgetException();
        }
    }

    public boolean isOver(int totalPrice) {
        return totalPrice > value;
    }

    public int excessAmount(int totalPrice) {
        return Math.max(0, totalPrice - value);
    }
}
