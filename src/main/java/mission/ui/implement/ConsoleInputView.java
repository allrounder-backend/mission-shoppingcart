package mission.ui.implement;

import api.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import mission.common.validate.Validator;
import mission.ui.InputView;
import mission.ui.exception.InputError;
import mission.ui.exception.InvalidInputException;
import mission.ui.validate.InputValidator;

public class ConsoleInputView implements InputView {

    @Override
    public int inputTotalBudget() {
        System.out.println("총 예산을 입력해 주세요.");
        try {
            String input = Validator.notNull(Console.readLine(), InputError.NULL_INPUT.getMessage()).trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(InputError.INVALID_BUDGET);
        }
    }

    @Override
    public List<Integer> inputLectureIds() {
        System.out.println("구입할 강의 목록을 입력해주세요.");
        String input = Validator.notNull(Console.readLine(), InputError.NULL_INPUT.getMessage()).trim();
        try {
            List<Integer> ids = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            InputValidator.validateNotEmpty(ids);
            InputValidator.validateNoDuplicate(ids);
            return ids;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(InputError.INVALID_LECTURE_ID);
        }
    }
}
