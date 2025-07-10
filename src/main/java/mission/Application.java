package mission;

import mission.controller.implement.CartController;

public class Application {
    public static void main(String[] args) {
        AppConfig config = new AppConfig();
        CartController controller = config.cartController();
        controller.run();
    }
}
