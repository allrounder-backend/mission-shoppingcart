package mission.domain.cart;

public class CartResultDto {
    private final boolean overBudget;
    private final int excessAmount;

    public CartResultDto(boolean overBudget, int excessAmount) {
        this.overBudget = overBudget;
        this.excessAmount = excessAmount;
    }

    public boolean isOverBudget() {
        return overBudget;
    }

    public int excessAmount() {
        return excessAmount;
    }
}
