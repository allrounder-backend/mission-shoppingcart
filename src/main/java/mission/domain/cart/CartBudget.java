package mission.domain.cart;

public record CartBudget(int value) {
    public CartBudget {
        if (value < 0) {
            throw new IllegalArgumentException("예산은 음수가 될 수 없습니다.");
        }
    }

    public boolean isOver(int totalPrice) {
        return totalPrice > value;
    }

    public int excessAmount(int totalPrice) {
        return Math.max(0, totalPrice - value);
    }
}
