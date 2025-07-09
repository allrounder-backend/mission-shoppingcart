package mission.ui.implement;

import static mission.ui.validate.InputValidator.validateNoDuplicate;
import static mission.ui.validate.InputValidator.validateNotEmpty;

import api.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import mission.ui.InputView;
import mission.ui.exception.InputError;
import mission.ui.exception.InvalidInputException;

public class ConsoleInputView implements InputView {

    @Override
    public int inputTotalBudget() {
        System.out.println("총 예산을 입력해 주세요.");
        try {
            return Integer.parseInt(Console.readLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(InputError.INVALID_BUDGET);
        }
    }

    @Override
    public List<Integer> inputLectureIds() {
        System.out.println("구입할 강의 목록을 입력해주세요.");
        String input = Console.readLine().trim();
        try {
            List<Integer> ids = Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            validateNotEmpty(ids);
            validateNoDuplicate(ids);
            return ids;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(InputError.INVALID_LECTURE_ID);
        }
    }
}
