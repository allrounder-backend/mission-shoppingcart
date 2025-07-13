package mission.ui;

public interface OutputView {
    void printOverBudget(int excess);
    void printWithinBudget();
    void printError(Exception e);
}
