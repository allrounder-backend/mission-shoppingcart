package mission.domain.cart.exception;

public enum BudgetError {
    NEGATIVE("예산은 음수가 될 수 없습니다."),
    INVALID_FORMAT("형식이 잘못된 예산 항목입니다."),
    TYPE_NOT_FOUND("존재하지 않는 강의 유형입니다."),
    OVER_TOTAL("총예산보다 유형별 예산 합계가 더 큽니다."),
    DUPLICATE_TYPE("중복된 유형 예산이 존재합니다.");

    private final String message;

    BudgetError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
