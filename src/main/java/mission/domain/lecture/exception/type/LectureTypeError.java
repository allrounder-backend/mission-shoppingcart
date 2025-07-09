package mission.domain.lecture.exception.type;

public enum LectureTypeError {
    TYPE_NOT_FOUND("존재하지 않는 강의 유형입니다."),
    TYPE_IS_NULL("강의 유형이 null입니다.");

    private final String message;

    LectureTypeError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
