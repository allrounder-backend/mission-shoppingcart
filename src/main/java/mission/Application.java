package mission;

import java.util.ArrayList;
import java.util.List;
import mission.controller.ShoppingCartController;
import mission.view.Input;
import mission.view.Output;

public class Application {
    public static void main(String[] args) {
        mission.model.LectureList lectureList = new mission.model.LectureList();
        int budget = Input.totalBudget();
        String input = Input.purchaseList();
        List<Integer> shoppingListId = new ArrayList<>();

        ShoppingCartController.parseListId(shoppingListId, input);
        int price = ShoppingCartController.price(shoppingListId, lectureList);
        Output.comparePrice(budget, price);
    }
}