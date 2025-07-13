package mission.ui.implement;

import mission.ui.OutputView;

public class ConsoleOutputView implements OutputView {

    @Override
    public void printOverBudget(int excess) {
        System.out.printf("예산을 초과했습니다. (초과 금액 %,d원)%n", excess);
    }

    @Override
    public void printWithinBudget() {
        System.out.println("예산을 초과하지 않았습니다.");
    }

    @Override
    public void printError(Exception e) {
        System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
    }

}
