package mission.dto;

import java.util.List;
import mission.domain.GroupedPricesByType;

public record BudgetCalculationDTO(
        boolean success,
        int totalBudget,
        List<GroupedPricesByType>typeBudget
) {}
