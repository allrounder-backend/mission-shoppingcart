package mission.domain;

public class User {
    final Budget budget;
    final PurchasingInfo priceEstimation;

    User(Budget budget, PurchasingInfo purchasingInfo){
        this.budget = budget;
        this.priceEstimation = purchasingInfo;
    }

    public static User newUser(){
        return new User(new Budget(), new PurchasingInfo());
    }

    public Budget getBudget() {
        return budget;
    }

    public PurchasingInfo getPurchasingInfo() {
        return priceEstimation;
    }
}
