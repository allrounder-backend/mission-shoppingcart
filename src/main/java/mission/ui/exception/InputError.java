package mission.ui.exception;

public enum InputError {
    NULL_INPUT("입력이 null입니다. 입력을 다시 확인해주세요."),
    INVALID_BUDGET("올바른 금액을 입력해주세요."),
    EMPTY_LIST("구입할 강의 목록을 입력해주세요."),
    DUPLICATE_IDS("동일한 강의가 중복 입력되었습니다."),
    INVALID_LECTURE_ID("올바른 강의 ID를 입력해주세요.");

    private final String message;

    InputError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
