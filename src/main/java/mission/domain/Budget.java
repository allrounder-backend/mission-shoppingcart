package mission.domain;

import java.util.List;

public class Budget {
    int totalBudget;
    List<GroupedPricesByType> budgetPerType;

    public void setBudget(int totalBudget, List<GroupedPricesByType> budgetPerType){
        this.totalBudget = totalBudget;
        this.budgetPerType = budgetPerType;
    }

    public List<GroupedPricesByType> getBudgetPerType() {
        return budgetPerType;
    }

    public int getTotalBudget() {
        return totalBudget;
    }
}
