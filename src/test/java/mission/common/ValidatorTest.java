package mission.common;

import mission.common.validate.Validator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void notNull_정상값이면_그대로반환() {
        String input = "hello";
        String result = Validator.notNull(input, "null이면 안됨");
        assertEquals("hello", result);
    }

    @Test
    void notNull_null이면_예외발생() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Validator.notNull(null, "null이면 안됨")
        );
        assertEquals("null이면 안됨", exception.getMessage());
    }

    @Test
    void notBlank_공백이_아니면_그대로반환() {
        String input = "test";
        String result = Validator.notBlank(input, "공백이면 안됨");
        assertEquals("test", result);
    }

    @Test
    void notBlank_null이면_예외발생() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Validator.notBlank(null, "공백이면 안됨")
        );
        assertEquals("공백이면 안됨", exception.getMessage());
    }

    @Test
    void notBlank_빈문자열이면_예외발생() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Validator.notBlank("", "공백이면 안됨")
        );
        assertEquals("공백이면 안됨", exception.getMessage());
    }

    @Test
    void notBlank_공백만_있어도_예외발생() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Validator.notBlank("   ", "공백이면 안됨")
        );
        assertEquals("공백이면 안됨", exception.getMessage());
    }

    @Test
    void notEmpty_리스트가_비어있지_않으면_그대로반환() {
        List<String> input = List.of("one");
        List<String> result = Validator.notEmpty(input, "비어있으면 안됨");
        assertEquals(input, result);
    }

    @Test
    void notEmpty_null이면_예외발생() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Validator.notEmpty(null, "비어있으면 안됨")
        );
        assertEquals("비어있으면 안됨", exception.getMessage());
    }

    @Test
    void notEmpty_빈컬렉션이면_예외발생() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Validator.notEmpty(List.of(), "비어있으면 안됨")
        );
        assertEquals("비어있으면 안됨", exception.getMessage());
    }
}
