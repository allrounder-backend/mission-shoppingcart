package mission;

import java.util.ArrayList;
import java.util.List;
import mission.controller.ShoppingCartController;
import mission.view.Input;

public class Application {

    public static void main(String[] args) {
        mission.model.LectureList lectureList = new mission.model.LectureList();
        ShoppingCartController shoppingCartController = new ShoppingCartController();
        int budget = Input.totalBudget();
        String input = Input.purchaseList();
        List<Integer> shoppingListId = new ArrayList<>();

        shoppingCartController.parseListId(shoppingListId, input);
        int price = shoppingCartController.price(shoppingListId, lectureList);
        shoppingCartController.comparePrice(budget, price);
    }
}