package mission;

import static org.junit.jupiter.api.Assertions.assertTrue;

import api.TestEnvironment;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ApplicationTest extends TestEnvironment {

    @Test
    void 예산을_초과한_경우_출력한다() {
        run(List.of("250000", "1, 14, 17"));
        assertTrue(output().contains("예산을 초과했습니다."));
        assertTrue(output().contains("3,000원"));
    }

    @Test
    void 예산이_초과하지않은_경우_출력한다() {
        run(List.of("260000", "1, 14, 17"));
        assertTrue(output().contains("예산을 초과하지 않았습니다."));
    }

    @Test
    void 예산이_음수면_예외메시지를_출력한다() {
        run(List.of("-10000", "1, 2, 3"));
        assertTrue(output().contains("예산은 음수가 될 수 없습니다."));
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

}
