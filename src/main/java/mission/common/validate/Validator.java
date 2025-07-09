package mission.common.validate;

public class Validator {
    public static <T> T notNull(T target, String message) {
        if (target == null) {
            throw new IllegalArgumentException(message);
        }
        return target;
    }
}
