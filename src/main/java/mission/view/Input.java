package mission.view;

import api.Console;

public class Input {
    public static int totalBudget() {
        System.out.println("총 예산을 입력해주세요.");
        try {
            return Integer.parseInt(Console.readLine());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
        }
        return 0;
    }

    public static String purchaseList() {
        System.out.println("구입할 강의 목록을 입력해주세요.");
        return Console.readLine();
    }
}
