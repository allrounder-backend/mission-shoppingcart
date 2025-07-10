package mission;

import mission.controller.implement.CartController;
import mission.domain.lecture.LectureRepository;
import mission.ui.InputView;
import mission.ui.OutputView;
import mission.ui.implement.ConsoleInputView;
import mission.ui.implement.ConsoleOutputView;

public class AppConfig {
    public CartController cartController() {
        return new CartController(
                lectureRepository(),
                inputView(),
                outputView()
        );
    }

    public LectureRepository lectureRepository() {
        return new LectureRepository();
    }

    public InputView inputView() {
        return new ConsoleInputView();
    }

    public OutputView outputView() {
        return new ConsoleOutputView();
    }
}
