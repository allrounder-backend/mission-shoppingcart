package mission;

import mission.controller.CartController;
import mission.domain.lecture.LectureRepository;
import mission.ui.InputView;
import mission.ui.OutputView;

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
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }
}
