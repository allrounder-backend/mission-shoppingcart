package mission.domain;

public class Lecture {
    String name;
    Type type;
    int price;

    public Lecture(String name, Type type, int price){
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }
}
