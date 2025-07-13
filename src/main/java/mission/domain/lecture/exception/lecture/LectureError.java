package mission.domain.lecture.exception.lecture;

public enum LectureError {
    NULL_LECTURE_ID_LIST("강의 ID 리스트가 null입니다."),
    EMPTY_LIST("강의를 1개 이상 선택해주세요."),
    LECTURE_NOT_FOUND("존재하지 않는 강의가 포함되어 있습니다.");

    private final String message;

    LectureError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
