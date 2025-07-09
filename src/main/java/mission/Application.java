package mission;

import mission.controller.CartController;

public class Application {
    public static void main(String[] args) {
        CartController controller = new CartController();
        controller.run();
    }
}
