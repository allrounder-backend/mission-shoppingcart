package mission.ui;

import mission.ui.exception.InputError;
import mission.ui.exception.InvalidInputException;
import mission.ui.validate.InputValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    @Test
    void 빈_리스트는_InvalidInputException_예외를_던진다() {
        List<Integer> emptyList = List.of();

        InvalidInputException exception = assertThrows(
                InvalidInputException.class,
                () -> InputValidator.validateNotEmpty(emptyList)
        );

        assertEquals(InputError.EMPTY_LIST, exception.getError());
    }

    @Test
    void 중복된_ID가_존재하면_InvalidInputException_예외를_던진다() {
        List<Integer> ids = List.of(1, 2, 2);

        InvalidInputException exception = assertThrows(
                InvalidInputException.class,
                () -> InputValidator.validateNoDuplicate(ids)
        );

        assertEquals(InputError.DUPLICATE_IDS, exception.getError());
    }

    @Test
    void 요소가_존재하면_예외가_발생하지_않는다() {
        List<Integer> ids = List.of(1, 2, 3);

        assertDoesNotThrow(() -> InputValidator.validateNotEmpty(ids));
    }

    @Test
    void 중복이_없으면_예외가_발생하지_않는다() {
        List<Integer> ids = List.of(1, 2, 3);

        assertDoesNotThrow(() -> InputValidator.validateNoDuplicate(ids));
    }

}
