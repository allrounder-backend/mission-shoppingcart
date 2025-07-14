package mission.controller;

import java.util.List;
import mission.model.Lecture;
import mission.model.LectureList;

public class ShoppingCartController {
    public void parseListId(List<Integer> id, String input) {
        String[] tokens = input.replaceAll("\\s+", "").split(",");
        for (String token : tokens) {
            try {
                id.add(Integer.parseInt(token));
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    public int price(List<Integer> shoppingListId, LectureList lectureList) {
        int cost = 0;
        for (int id : shoppingListId) {
            Lecture lec = lectureList.findLectureByID(id);
            cost += lec.getLecturePrice();
        }
        return cost;
    }

    public void comparePrice(int budget, int price) {
        if (budget >= price) {
            System.out.print("예산을 초과하지 않았습니다.");
        } else {
            System.out.print("에산을 초과했습니다. 초과금액(" + (price - budget) + "원" + ")");
        }
    }
}
