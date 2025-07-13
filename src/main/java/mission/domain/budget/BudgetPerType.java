package mission.domain.budget;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import mission.domain.lecture.LectureType;

public class BudgetPerType {
    private final Map<LectureType, Integer> budgetMap;

    public BudgetPerType(Map<LectureType, Integer> budgetMap) {
        this.budgetMap = budgetMap;
    }

    public Optional<Integer> get(LectureType type) {
        return Optional.ofNullable(budgetMap.get(type));
    }

    public Set<LectureType> types() {
        return budgetMap.keySet();
    }
}
