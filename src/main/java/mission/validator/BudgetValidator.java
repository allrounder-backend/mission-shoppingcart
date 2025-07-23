package mission.validator;

import java.util.List;
import mission.domain.GroupedPricesByType;

public class BudgetValidator {
    public static boolean isTotalBudgetValid(int totalBudget){
        return totalBudget >= 0;
    }

    public static boolean isTypeBudgetInBound(int totalBudget, List<GroupedPricesByType> budgetPerType){
        int typeBudgetSum = budgetPerType.stream()
                .mapToInt(GroupedPricesByType::getFirstPrice)
                .sum();
        return totalBudget>=typeBudgetSum;
    }
}
