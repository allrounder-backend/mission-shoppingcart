package mission.ui;

import api.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    public int inputTotalBudget() {
        System.out.println("총 예산을 입력해 주세요.");
        return Integer.parseInt(Console.readLine().trim());
    }

    public List<Integer> inputLectureIds() {
        System.out.println("구입할 강의 목록을 입력해주세요.");
        String input = Console.readLine().trim();
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
