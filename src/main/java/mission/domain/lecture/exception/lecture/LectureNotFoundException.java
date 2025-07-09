package mission.domain.lecture.exception.lecture;

public class LectureNotFoundException extends IllegalArgumentException {
    private final LectureError error;

    public LectureNotFoundException(LectureError error) {
        super(error.getMessage());
        this.error = error;
    }

    public LectureError getError() {
        return error;
    }
}
