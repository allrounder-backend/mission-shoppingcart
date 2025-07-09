package mission.domain.lecture.exception.type;

public class InvalidLectureTypeException extends IllegalArgumentException {
    private final LectureTypeError error;

    public InvalidLectureTypeException(LectureTypeError error) {
        super(error.getMessage());
        this.error = error;
    }

    public LectureTypeError getError() {
        return error;
    }
}
