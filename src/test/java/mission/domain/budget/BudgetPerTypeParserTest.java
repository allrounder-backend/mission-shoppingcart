package mission.domain.budget;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import mission.domain.budget.exception.BudgetOverTotalException;
import mission.domain.budget.exception.DuplicateBudgetTypeException;
import mission.domain.budget.utils.BudgetPerTypeParser;
import mission.domain.budget.exception.InvalidBudgetFormatException;
import mission.domain.budget.exception.NegativeBudgetException;
import mission.domain.lecture.LectureType;
import mission.domain.lecture.exception.type.InvalidLectureTypeException;
import org.junit.jupiter.api.Test;

class BudgetPerTypeParserTest {

    @Test
    void 정상적인_입력은_정상적으로_매핑된다() {
        BudgetPerType budget = BudgetPerTypeParser.parse("DevOps-100000,F/W-200000,CS-50000", 500000);
        assertEquals(Set.of(LectureType.DEVOPS, LectureType.FW, LectureType.CS), budget.types());
    }

    @Test
    void 하이픈이_없는_입력은_예외를_던진다() {
        assertThrows(InvalidBudgetFormatException.class, () ->
                BudgetPerTypeParser.parse("DevOps100000", 500000));
    }

    @Test
    void 음수_입력은_예외를_던진다() {
        assertThrows(NegativeBudgetException.class, () ->
                BudgetPerTypeParser.parse("DevOps--10000", 500000));
    }

    @Test
    void 존재하지_않는_유형은_예외를_던진다() {
        assertThrows(InvalidLectureTypeException.class, () ->
                BudgetPerTypeParser.parse("Unknown-10000", 500000));
    }

    @Test
    void 중복된_유형이_입력되면_예외를_던진다() {
        assertThrows(DuplicateBudgetTypeException.class, () ->
                BudgetPerTypeParser.parse("CS-10000,CS-20000", 500000));
    }

    @Test
    void 유형별_예산_총합이_총예산을_초과하면_예외를_던진다() {
        assertThrows(BudgetOverTotalException.class, () ->
                BudgetPerTypeParser.parse("CS-300000,DevOps-300000", 500000));
    }
}
