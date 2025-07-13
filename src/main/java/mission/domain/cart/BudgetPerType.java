package mission.domain.cart;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import mission.domain.lecture.LectureType;

public class BudgetPerType {
    private final Map<LectureType, Integer> map;

    public BudgetPerType(String input) {
        this.map = parse(input);
    }

    private Map<LectureType, Integer> parse(String input) {
        if (input == null || input.isBlank()) return Map.of();
        return Arrays.stream(input.split(","))
                .map(s -> s.split("-"))
                .collect(Collectors.toMap(
                        arr -> LectureType.from(arr[0]),
                        arr -> Integer.parseInt(arr[1])
                ));
    }

    public Optional<Integer> get(LectureType type) {
        return Optional.ofNullable(map.get(type));
    }

    public Set<LectureType> types() {
        return map.keySet();
    }
}
