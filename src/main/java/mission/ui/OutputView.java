package mission.ui;

public class OutputView {
    public void printOverBudget(int excess) {
        System.out.printf("예산을 초과했습니다. (초과 금액 %,d원)%n", excess);
    }

    public void printWithinBudget() {
        System.out.println("예산을 초과하지 않았습니다.");
    }
}
