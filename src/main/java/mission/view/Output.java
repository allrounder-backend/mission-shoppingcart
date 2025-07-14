package mission.view;

public class Output {
    public static void comparePrice(int budget, int price) {
        if (budget >= price) {
            System.out.print("예산을 초과하지 않았습니다.");
        } else {
            System.out.print("에산을 초과했습니다. 초과금액(" + (price - budget) + "원" + ")");
        }
    }
}
