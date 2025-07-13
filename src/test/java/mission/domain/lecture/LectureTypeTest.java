package mission.domain.lecture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import mission.domain.lecture.exception.type.InvalidLectureTypeException;
import org.junit.jupiter.api.Test;

class LectureTypeTest {

    @Test
    void from_정상_변환() {
        assertEquals(LectureType.DEVOPS, LectureType.from("devops"));
        assertEquals(LectureType.FW, LectureType.from("f/w"));
        assertEquals(LectureType.FW, LectureType.from("FW"));
    }

    @Test
    void from_존재하지_않는_유형_예외() {
        assertThrows(InvalidLectureTypeException.class,
                () -> LectureType.from("unknown"));
    }

    @Test
    void from_null입력_예외() {
        assertThrows(IllegalArgumentException.class,
                () -> LectureType.from(null));
    }
}
