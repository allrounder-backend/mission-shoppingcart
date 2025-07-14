package mission.application.domain.lecture;

import java.util.Comparator;
import java.util.List;
import mission.application.domain.Lecture;
import mission.application.domain.enums.LectureType;

public class LangLecture extends Lecture {
    public LangLecture() {
        super();
    }

    public LangLecture(int id, String name, LectureType type, int cost) {
        super(id, name, type, cost);
    }

    @Override
    public int calculateCost(List<Lecture> lectures) {
        int price = lectures.stream()
                .map(Lecture::getCost)
                .reduce(0, Integer::sum);

        if (lectures.size() >= 2) {
            price -= lectures.stream()
                    .min(Comparator.comparing(Lecture::getCost))
                    .get()
                    .getCost();
        }

        return price;
    }
}
