package mission.application.domain;

import java.util.List;
import mission.application.domain.enums.LectureType;

public class Lecture {
    int id;
    String name;
    LectureType type;
    int cost;

    public Lecture() {}

    public Lecture(int id, String name, LectureType type, int cost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LectureType getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public int calculateCost(List<Lecture> lectures) {
        return lectures.stream()
                .map(Lecture::getCost)
                .reduce(0, Integer::sum);
    }
}
