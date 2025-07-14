package mission.controller;

import java.util.List;
import mission.model.Lecture;
import mission.model.LectureList;

public class ShoppingCartController {
    public static void parseListId(List<Integer> id, String input) {
        String[] tokens = input.replaceAll("\\s+", "").split(",");
        for (String token : tokens) {
            try {
                id.add(Integer.parseInt(token));
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    public static int price(List<Integer> shoppingListId, LectureList lectureList) {
        int cost = 0;
        for (int id : shoppingListId) {
            Lecture lec = lectureList.findLectureByID(id);
            cost += lec.getLecturePrice();
        }
        return cost;
    }
}
