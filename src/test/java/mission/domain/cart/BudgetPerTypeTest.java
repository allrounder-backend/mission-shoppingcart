package mission.domain.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import mission.domain.cart.exception.BudgetOverTotalException;
import mission.domain.cart.exception.DuplicateBudgetTypeException;
import mission.domain.cart.exception.InvalidBudgetFormatException;
import mission.domain.cart.exception.NegativeBudgetException;
import mission.domain.lecture.exception.type.InvalidLectureTypeException;
import org.junit.jupiter.api.Test;

class BudgetPerTypeTest {

    @Test
    void 정상적인_입력은_정상적으로_매핑된다() {
        BudgetPerType budget = new BudgetPerType("DevOps-100000,F/W-200000,CS-50000", 500000);
        assertEquals(3, budget.types().size());
    }

    @Test
    void 하이픈이_없는_잘못된_입력은_예외를_던진다() {
        assertThrows(InvalidBudgetFormatException.class, () ->
                new BudgetPerType("DevOps100000", 500000));
    }

    @Test
    void 음수_입력은_예외를_던진다() {
        assertThrows(NegativeBudgetException.class, () ->
                new BudgetPerType("DevOps--10000", 500000));
    }

    @Test
    void 존재하지_않는_유형은_예외를_던진다() {
        assertThrows(InvalidLectureTypeException.class, () ->
                new BudgetPerType("Unknown-10000", 500000));
    }

    @Test
    void 중복된_유형이_입력되면_예외를_던진다() {
        assertThrows(DuplicateBudgetTypeException.class, () ->
                new BudgetPerType("CS-10000,CS-20000", 500000));
    }

    @Test
    void 유형별_예산_총합이_총예산을_초과하면_예외를_던진다() {
        assertThrows(BudgetOverTotalException.class, () ->
                new BudgetPerType("CS-300000,DevOps-300000", 500000)); // 총합 600000
    }

}
