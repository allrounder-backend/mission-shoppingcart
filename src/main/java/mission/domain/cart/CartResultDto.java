package mission.domain.cart;

public class CartResultDto {
    private final boolean overBudget;
    private final int excessAmount;

    private CartResultDto(boolean overBudget, int excessAmount) {
        this.overBudget = overBudget;
        this.excessAmount = excessAmount;
    }

    public static CartResultDto of(CartBudget budget, int totalPrice) {
        boolean over = budget.isOver(totalPrice);
        int excess = over ? budget.excessAmount(totalPrice) : 0;
        return new CartResultDto(over, excess);
    }

    public boolean isOverBudget() {
        return overBudget;
    }

    public int excessAmount() {
        return excessAmount;
    }
}
