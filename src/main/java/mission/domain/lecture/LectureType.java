package mission.domain.lecture;

public enum LectureType {
    DEVOPS, DBMS, LANG, FW, CS;

    public static LectureType from(String name) {
        return switch (name.toLowerCase()) {
            case "devops" -> DEVOPS;
            case "dbms" -> DBMS;
            case "lang" -> LANG;
            case "f/w", "fw" -> FW;
            case "cs" -> CS;
            default -> throw new IllegalArgumentException("존재하지 않는 강의 유형입니다: " + name);
        };
    }
}
