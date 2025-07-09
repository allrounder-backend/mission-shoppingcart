package mission.ui.validate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import mission.ui.exception.InputError;
import mission.ui.exception.InvalidInputException;

public class InputValidator {
    public static void validateNotEmpty(List<Integer> ids) {
        if (ids.isEmpty()) {
            throw new InvalidInputException(InputError.EMPTY_LIST);
        }
    }

    public static void validateNoDuplicate(List<Integer> ids) {
        Set<Integer> set = new HashSet<>(ids);
        if (set.size() != ids.size()) {
            throw new InvalidInputException(InputError.DUPLICATE_IDS);
        }
    }
}
