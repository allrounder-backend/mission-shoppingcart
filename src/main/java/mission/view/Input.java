package mission.view;

import java.util.Scanner;

public class Input {
    public static int totalBudget() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("총 예산을 입력해주세요.");
        return scanner.nextInt();
    }

    public static String purchaseList() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("구입할 강의 목록을 입력해주세요.");
        return scanner.nextLine();
    }
}
