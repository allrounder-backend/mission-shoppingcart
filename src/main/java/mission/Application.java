package mission;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import mission.controller.ShoppingCartController;
import mission.domain.User;
import mission.utility.DebugLogging;

public class Application {
    public static void main(String[] args) {
        //TODO: 미션 구현
        System.out.println("THIS IS REAL MAIN: " + Application.class.getProtectionDomain().getCodeSource().getLocation());
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_16));
        Config appConfig = new Config();
        new Application().run(appConfig);
    }

    public void run(Config appConfig){
        ShoppingCartController controller = appConfig.shoppingCartController();
        //웹에서의 컨트롤러 동작과 유사하게 만들고 싶어,
        //앱 실행 시 접속한 유저가 프로그램을 사용하는 것을 흉내내려 유저 코드를 만들었습니다.
        User user = User.newUser();
        try{
            controller.initializingData();
            controller.inputBudget(user.getBudget());
            controller.inputPurchaseLectureList(user.getPurchasingInfo());
            controller.outputResult(user.getBudget(), user.getPurchasingInfo());

        }catch (Exception e){
            DebugLogging.print();
            throw new RuntimeException(e.getClass().getSimpleName() + " : " +e.getMessage());
        }

    }
}
