package mission.domain.lecture;

import mission.domain.lecture.exception.lecture.LectureError;
import mission.domain.lecture.exception.lecture.LectureNotFoundException;
import mission.ui.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LectureRepositoryTest {

    private final LectureRepository repository = new LectureRepository();

    @Test
    void null_입력은_IllegalArgumentException_예외를_던진다() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> repository.findByIds(null)
        );
        assertEquals("강의 ID 리스트가 null입니다.", exception.getMessage());
    }

    @Test
    void 존재하지_않는_ID가_포함되면_LectureNotFoundException_예외를_던진다() {
        List<Integer> ids = List.of(1, 999); // 999는 존재하지 않음

        LectureNotFoundException exception = assertThrows(
                LectureNotFoundException.class,
                () -> repository.findByIds(ids)
        );

        assertEquals("존재하지 않는 강의가 포함되어 있습니다.", exception.getMessage());
        assertEquals(LectureError.LECTURE_NOT_FOUND, exception.getError());
    }

    @Test
    void 올바른_ID입력이면_해당_Lecture_리스트를_반환한다() {
        List<Integer> ids = List.of(1, 2, 3);

        List<Lecture> result = repository.findByIds(ids);

        assertEquals(3, result.size());
        assertEquals(1, result.get(0).id());
        assertEquals(2, result.get(1).id());
        assertEquals(3, result.get(2).id());
    }
}
