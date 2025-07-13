package mission;

import static org.junit.jupiter.api.Assertions.assertTrue;

import api.TestEnvironment;
import java.util.Arrays;
import java.util.List;
import mission.ui.exception.InputError;
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

    @Test
    void 숫자가_아닌_문자열을_입력하면_INVALID_BUDGET_예외메시지를_출력한다() {
        run(List.of("abc", "1, 2, 3")); // abc는 예산 입력

        assertTrue(output().contains(InputError.INVALID_BUDGET.getMessage()));
    }

    @Test
    void 강의ID에_문자가_포함되어있으면_INVALID_LECTURE_ID_예외메시지를_출력한다() {
        run(List.of("100000", "1, a, 3")); // 예산은 정상, ID에 문자 포함

        assertTrue(output().contains(InputError.INVALID_LECTURE_ID.getMessage()));
    }

    @Test
    void 강의ID입력이_null이면_NULL_INPUT_예외메시지를_출력한다() {
        run(Arrays.asList("100000", null)); // 강의 ID 입력이 null

        assertTrue(output().contains(InputError.NULL_INPUT.getMessage()));
    }

    @Test
    void 예산입력이_null이면_NULL_INPUT_예외메시지를_출력한다() {
        run(Arrays.asList(null, "1, 2, 3")); // 예산 입력이 null

        assertTrue(output().contains(InputError.NULL_INPUT.getMessage()));
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

}
