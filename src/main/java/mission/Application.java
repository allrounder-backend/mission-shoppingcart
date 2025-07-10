package mission;

import api.Console;
import java.util.Scanner;

class Basket {
    private final int PROMOTION_RATIO_DEVOPS = 10;
    private final int PROMOTION_VALUE_DBMS = 5_000;
    private final int PROMOTION_VALUE_LANG = 0;
    private final int PROMOTION_VALUE_FW = 30_000;
    private final int PROMOTION_RATIO_CS = 30;

    private int budget;
    private int price;
    // 배열은 final 키워드를 사용하여 불변성을 보장합니다.
    private final Lecture[][] lectures;

    Basket(int budget) {
        this.budget = budget;
        this.price = 0;
        // 5개의 유형에 대한 강의를 저장하기 위한 2차원 배열을 초기화합니다.
        this.lectures = new Lecture[5][];
        this.lectures[0] = new Lecture[3]; // DevOps
        this.lectures[1] = new Lecture[3]; // DMBS
        this.lectures[2] = new Lecture[3]; // Lang
        this.lectures[3] = new Lecture[4]; // F/W
        this.lectures[4] = new Lecture[4]; // CS
    }

    public void categorize(String[] id) {
        for (String s : id) {
            // TODO: 강의 ID로 Lecture enum을 찾습니다.
            Lecture l = Lecture.valueOf("LEC" + s);
            int lectureIndex = switch(l.getCategory()) {
                case "DevOps" -> 0;
                case "DBMS" -> 1;
                case "Lang" -> 2;
                case "F/W" -> 3;
                case "CS" -> 4;
                // switch는 모든 경우를 처리해야 하므로, default 케이스를 추가합니다.
                default -> throw new IllegalStateException("[오류] 알 수 없는 유형: " + l.getCategory());
            };
            // 강의를 추가합니다.
            for (int i = 0; i < lectures[lectureIndex].length; i++) {
                if (lectures[lectureIndex][i] == null) {
                    lectures[lectureIndex][i] = l;
                    break;
                }
            }
        }
    }
}

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("총 예산을 입력해 주세요.");
        Basket basket = new Basket(scanner.nextInt());
        System.out.println("구입할 강의 목록을 입력해주세요.");
        basket.categorize(scanner.next().split(", "));
    }ㅣ
}
