package mission.domain.lecture.exception.lecture;

public enum LectureError {
    LECTURE_NOT_FOUND("존재하지 않는 강의가 포함되어 있습니다.");

    private final String message;

    LectureError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
