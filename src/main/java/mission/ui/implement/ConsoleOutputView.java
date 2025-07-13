package mission.ui.implement;

import mission.domain.cart.CartResultDto;
import mission.domain.lecture.LectureType;
import mission.ui.OutputView;

public class ConsoleOutputView implements OutputView {

    @Override
    public void printOverBudget(CartResultDto result) {
        System.out.println("예산을 초과했습니다.");
        System.out.printf(" - 총 예산 : %s%n",
                result.totalOver() ? String.format("%,d원 초과", result.excessAmount()) : "OK");

        for (LectureType type : result.budgetPerType().types()) {
            String typeName = type.displayName();

            if (result.exceededTypes().containsKey(type)) {
                int amount = result.exceededTypes().get(type);
                System.out.printf(" - %s : %,d원 초과%n", typeName, amount);
            } else {
                System.out.printf(" - %s : OK%n", typeName);
            }
        }
    }

    public void printWithinBudget(CartResultDto result) {
        System.out.println("예산을 초과하지 않았습니다.");
        System.out.println(" - 총 예산 : OK");
        for (LectureType type : result.budgetPerType().types()) {
            System.out.printf(" - %s : OK%n", type.displayName());
        }
    }


    @Override
    public void printError(Exception e) {
        System.out.println(e.getClass().getSimpleName() + " : " + e.getMessage());
    }

}
