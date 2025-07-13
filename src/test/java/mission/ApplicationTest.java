package mission;

import static org.junit.jupiter.api.Assertions.assertTrue;

import api.TestEnvironment;
import java.util.Arrays;
import java.util.List;
import mission.ui.exception.InputError;
import org.junit.jupiter.api.Test;

public class ApplicationTest extends TestEnvironment {

    @Test
    void 총_예산을_초과한_경우_출력한다() {
        run(List.of(
                "250000",
                "DevOps-100000,F/W-100000,CS-50000",
                "1, 5, 6, 7, 12, 14, 17"
        ));

        assertTrue(output().contains("예산을 초과했습니다."));
        assertTrue(output().contains("총 예산 : 177,900원 초과"));
        assertTrue(output().contains("DevOps : OK"));
        assertTrue(output().contains("F/W : OK"));
        assertTrue(output().contains("CS : 126,000원 초과"));
    }

    @Test
    void 유형별_예산만_초과된_경우_출력한다() {
        run(List.of(
                "600000",
                "DevOps-200000,F/W-250000,CS-100000",
                "1, 3, 10, 11, 15, 16"
        ));

        assertTrue(output().contains("예산을 초과했습니다."));
        assertTrue(output().contains("총 예산 : OK"));
        assertTrue(output().contains("DevOps : OK"));
        assertTrue(output().contains("F/W : OK"));
        assertTrue(output().contains("CS : 21,000원 초과"));
    }

    @Test
    void 모든_유형이_OK이면_OK로_출력한다() {
        run(List.of(
                "600000",
                "DevOps-300000,F/W-200000,CS-100000",
                "1, 2, 3"
        ));

        assertTrue(output().contains("예산을 초과하지 않았습니다."));
        assertTrue(output().contains("총 예산 : OK"));
        assertTrue(output().contains("DevOps : OK"));
        assertTrue(output().contains("F/W : OK"));
        assertTrue(output().contains("CS : OK"));
    }

    @Test
    void 예산이_음수면_예외메시지를_출력한다() {
        run(List.of(
                "-10000",
                "DevOps-100000,F/W-100000,CS-60000",
                "1, 2, 3"
        ));

        assertTrue(output().contains("예산은 음수가 될 수 없습니다."));
    }

    @Test
    void 숫자가_아닌_문자열을_입력하면_INVALID_BUDGET_예외메시지를_출력한다() {
        run(List.of(
                "abc",
                "DevOps-100000,F/W-100000,CS-60000",
                "1, 2, 3"
        ));

        assertTrue(output().contains(InputError.INVALID_BUDGET.getMessage()));
    }

    @Test
    void 강의ID에_문자가_포함되어있으면_INVALID_LECTURE_ID_예외메시지를_출력한다() {
        run(List.of(
                "300000",
                "DevOps-100000,F/W-100000,CS-60000",
                "1, a, 3"
        ));

        assertTrue(output().contains(InputError.INVALID_LECTURE_ID.getMessage()));
    }

    @Test
    void 강의ID입력이_null이면_NULL_INPUT_예외메시지를_출력한다() {
        run(Arrays.asList(
                "100000",
                "DevOps-100000,F/W-100000,CS-60000",
                null
        ));
        assertTrue(output().contains(InputError.NULL_INPUT.getMessage()));
    }

    @Test
    void 예산입력이_null이면_NULL_INPUT_예외메시지를_출력한다() {
        run(Arrays.asList(
                null,
                "DevOps-100000,F/W-100000,CS-60000",
                "1, 2, 3"
        ));
        assertTrue(output().contains(InputError.NULL_INPUT.getMessage()));
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }

}
