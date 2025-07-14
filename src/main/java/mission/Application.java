package mission;

import mission.application.service.ShoppingCartService;

public class Application {
    public static void main(String[] args) {
        //TODO: 미션 구현
        ShoppingCartService shoppingCartService = ShoppingCartService.getInstance();
        shoppingCartService.run();
    }
}
