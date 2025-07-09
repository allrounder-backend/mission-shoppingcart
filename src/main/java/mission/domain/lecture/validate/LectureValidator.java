package mission.domain.lecture.validate;

import java.util.List;
import mission.domain.lecture.Lecture;
import mission.domain.lecture.exception.lecture.LectureError;
import mission.domain.lecture.exception.lecture.LectureNotFoundException;

public class LectureValidator {
    public static void validateAllFound(List<Integer> requested, List<Lecture> found) {
        if (requested.size() != found.size()) {
            throw new LectureNotFoundException(LectureError.NULL_LECTURE_ID_LIST);
        }
    }
}
