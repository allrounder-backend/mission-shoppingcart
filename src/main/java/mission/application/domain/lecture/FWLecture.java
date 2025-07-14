package mission.application.domain.lecture;

import java.util.List;
import mission.application.domain.Lecture;
import mission.application.domain.enums.LectureType;

public class FWLecture extends Lecture {
    public FWLecture() {
        super();
    }

    public FWLecture(int id, String name, LectureType type, int cost) {
        super(id, name, type, cost);
    }

    @Override
    public int calculateCost(List<Lecture> lectures) {
        int price = lectures.stream()
                .map(Lecture::getCost)
                .reduce(0, Integer::sum);

        if (price > 90000) {
            return price - 30000;
        }
        return price;
    }
}
