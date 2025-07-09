package mission.domain.cart;

public class Budget {
    private final int totalBudget;

    public Budget(int totalBudget) {
        if (totalBudget < 0) {
            throw new IllegalArgumentException("예산은 음수가 될 수 없습니다.");
        }
        this.totalBudget = totalBudget;
    }

    public boolean isOver(int totalPrice) {
        return totalPrice > totalBudget;
    }

    public int excessAmount(int totalPrice) {
        return Math.max(0, totalPrice - totalBudget);
    }

    public int getValue() {
        return totalBudget;
    }
}
