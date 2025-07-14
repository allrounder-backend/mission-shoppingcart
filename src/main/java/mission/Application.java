package mission;

import api.Console;

class Basket {
    private final int[] budget;
    private final int[] price;
    private final Lecture[][] lectures;

    Basket(int budget) {
        this.budget = new int[6];
        this.price = new int[6];
        this.lectures = new Lecture[5][];
        this.budget[0] = budget;
        this.lectures[0] = new Lecture[3]; // DevOps
        this.lectures[1] = new Lecture[3]; // DBMS
        this.lectures[2] = new Lecture[3]; // Lang
        this.lectures[3] = new Lecture[4]; // F/W
        this.lectures[4] = new Lecture[4]; // CS
    }

    public void setBudget(String[] budgets) {
        for (String budget : budgets) {
            String[] s = budget.split("-");
            switch (s[0]) {
                case "DevOps" -> this.budget[1] = Integer.parseInt(s[1]);
                case "DBMS" -> this.budget[2] = Integer.parseInt(s[1]);
                case "Lang" -> this.budget[3] = Integer.parseInt(s[1]);
                case "F/W" -> this.budget[4] = Integer.parseInt(s[1]);
                case "CS" -> this.budget[5] = Integer.parseInt(s[1]);
                default -> throw new IllegalArgumentException("잘못된 카테고리: " + s[0]);
            }
        }
    }

    public void categorize(String[] id) {
        for (String s : id) {
            try {
                // 강의 ID로 Lecture enum을 찾습니다.
                for (Lecture l : Lecture.values()) {
                    if (l.getId().equals(s)) {
                        int lectureIndex = l.getCategory();
                        // 강의를 추가합니다.
                        for (int i = 0; i < lectures[lectureIndex].length; i++) {
                            if (lectures[lectureIndex][i] == null) {
                                lectures[lectureIndex][i] = l;
                                break;
                            }
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[오류] 잘못된 강의 ID: " + s);
            }
        }
    }

    public void getPromotion(int categoryIndex) {
        switch (categoryIndex) {
            case 0 -> { // DevOps
                for (Lecture l : lectures[0]) {
                    if (l != null) price[1] += (int) (l.getPrice() * 0.9);
                }
            }
            case 1 -> { // DBMS
                for (Lecture l : lectures[1]) {
                    if (l != null) price[2] += (l.getPrice() - 5000);
                }
            }
            case 2 -> { // Lang
                int cnt = 0;
                for (Lecture l : lectures[2]) {
                    // 2개 이상 구매 시 가장 저렴한 강의 하나 무료
                    if (l != null) cnt++;
                    if (cnt >= 2) {
                        // 가장 저렴한 강의를 찾습니다.
                        int minPrice = Integer.MAX_VALUE;
                        Lecture cheapestLecture = null;
                        for (Lecture lecture : lectures[2]) {
                            if (lecture != null && lecture.getPrice() < minPrice) {
                                minPrice = lecture.getPrice();
                                cheapestLecture = lecture;
                            }
                        }
                        // 가장 저렴한 강의를 제외한 나머지 강의 가격의 합을 구합니다.
                        for (Lecture lecture : lectures[2]) {
                            if (lecture != null && lecture != cheapestLecture) {
                                price[3] += lecture.getPrice();
                            }
                        }
                    } else {
                        for (Lecture lecture : lectures[2]) {
                            if (lecture != null) price[3] += lecture.getPrice();
                        }
                    }
                }
            }
            case 3 -> { // F/W
                int temp = 0;
                for (Lecture l : lectures[3]) {
                    if (l != null) temp += l.getPrice();
                }
                price[4] += (temp >= 90000) ? (temp - 30000) : temp;
            }
            case 4 -> { // CS
                int temp = 0; int cnt = 0;
                for (Lecture l : lectures[4]) {
                    if (l != null) {
                        temp += l.getPrice();
                        cnt++;
                    }
                }
                price[5] += (cnt >= 3) ? (int) (temp * 0.7) : temp;
            }
        }
    }

    public int getBudget(int categoryIndex) {
        return budget[categoryIndex];
    }

    public int getPrice(int categoryIndex) {
        return price[categoryIndex];
    }

    // 총 가격을 계산하여 price 배열의 index 0에 저장합니다.
    public void setTotalPrice() {
        int total = 0;
        for (int i : price) total += i;
        price[0] = total;
    }

    // 예산이 가격과 맞아 떨어지는 경우와 아예 구입하지 않는 경우를 구분합니다.
    // index가 0인 경우는 총 예산을 의미하므로 항상 true를 반환합니다.
    public boolean isNotNull(int categoryIndex) {
        if (categoryIndex == 0) return true;
        else return lectures[categoryIndex - 1][0] != null;
    }
}
public class Application {
    public static void main(String[] args) {
        System.out.println("총 예산을 입력해 주세요.");
        Basket basket = new Basket(Integer.parseInt(Console.readLine()));
        System.out.println("유형 별 예산을 입력해 주세요.");
        basket.setBudget(Console.readLine().split(","));
        System.out.println("구입할 강의 목록을 입력해주세요.");
        basket.categorize(Console.readLine().split(", "));
        for (int i = 0; i < 5; basket.getPromotion(i++));
        basket.setTotalPrice();
        int[] temp = new int[6];
        boolean isOverBudget = false;
        for (int i = 0; i < 6; i++) {
            temp[i] = basket.getPrice(i) - basket.getBudget(i);
            if (temp[i] > 0) isOverBudget = true;
        }
        String[] category = {"총 예산", "DevOps", "DBMS", "Lang", "F/W", "CS"};
        if (isOverBudget) {
            System.out.println("예산을 초과했습니다.");
            for (int i = 0; i < 6; i++) {
                if (basket.isNotNull(i)) {
                    System.out.print("- " + category[i] + " : ");
                    System.out.printf(temp[i] > 0 ? "%,d원 초과\n" : "OK\n", temp[i]);
                }
            }
        } else {
            System.out.println("예산 내에 구매할 수 있습니다.");
        }
    }
}