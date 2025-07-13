package mission.domain.lecture;

import mission.common.validate.Validator;
import mission.domain.lecture.exception.type.InvalidLectureTypeException;
import mission.domain.lecture.exception.type.LectureTypeError;

public enum LectureType {
    DEVOPS, DBMS, LANG, FW, CS;

    public static LectureType from(String name) {
        name = Validator.notBlank(name, LectureTypeError.TYPE_IS_NULL.getMessage());

        return switch (name.toLowerCase()) {
            case "devops" -> DEVOPS;
            case "dbms" -> DBMS;
            case "lang" -> LANG;
            case "f/w", "fw" -> FW;
            case "cs" -> CS;
            default -> throw new InvalidLectureTypeException(LectureTypeError.TYPE_NOT_FOUND);
        };
    }

}
