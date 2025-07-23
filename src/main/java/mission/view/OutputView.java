package mission.view;

import java.util.List;
import mission.domain.GroupedPricesByType;
import mission.dto.BudgetCalculationDTO;
import mission.message.SystemMessage;

public class OutputView {
    static final String PASS = "OK";
    static final String FAIL = "%,d원 초과";
    static final String BULLET_FORMAT = "\n - %s : ";
    static final int IS_OK = 0;

    public void outputBudgetCalculationResult(BudgetCalculationDTO budgetCalculationDTO){
        String result = budgetCalculationDTO.success() ? NotOverBudget() : OverBudget(budgetCalculationDTO);
        System.out.println(result);
    }

    private String NotOverBudget(){
        return SystemMessage.OUTPUT_NOT_OVERBUDGET;
    }

    private String OverBudget(BudgetCalculationDTO budgetCalculationDTO){
        StringBuilder result = new StringBuilder();
        int totalBudget = budgetCalculationDTO.totalBudget();
        List<GroupedPricesByType> typeBudget = budgetCalculationDTO.typeBudget();

        result.append(SystemMessage.OUTPUT_OVERBUDGET)
                .append(String.format(BULLET_FORMAT, "총 예산"))
                .append(
                        totalBudget==IS_OK ? PASS : String.format(FAIL, totalBudget)
                );

        //유형별 예산 처리
        for(GroupedPricesByType pair : typeBudget){
            String typeName = pair.getType().getName();
            int budget = pair.getFirstPrice();
            result.append(String.format(BULLET_FORMAT, typeName))
                    .append(
                            budget==IS_OK ? PASS : String.format(FAIL, budget)
                    );
        }

        return result.toString();
    }
}
