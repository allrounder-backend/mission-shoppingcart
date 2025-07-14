package mission.application.domain.enums;

public enum MessageConstant {
    INPUT_TOTAL_BUDGET_GUIDE("총 예산을 입력해 주세요. "),
    INPUT_LECTURE_ID_GUIDE("구입할 강의 목록을 입력해주세요. "),
    INPUT_EACH_BUDGET_GUIDE("유형별 예산을 입력해 주세요. "),
    OUTPUT_BUDGET_EXCESS_MESSAGE("예산을 초과했습니다."),
    OUTPUT_EACH_BUDGET_FIT_MESSAGE(" - %s : OK"),
    OUTPUT_EACH_BUDGET_EXCESS_MESSAGE(" - %s : %,d원 초과"),
    OUTPUT_BUDGET_FIT_MESSAGE("예산을 초과하지 않았습니다. ");

    String message;

    MessageConstant(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
