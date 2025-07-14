package mission.application.domain.lecture;

import java.util.List;
import mission.application.domain.Lecture;
import mission.application.domain.enums.LectureType;

public class CSLecture extends Lecture {
    public CSLecture() {
        super();
    }

    public CSLecture(int id, String name, LectureType type, int cost) {
        super(id, name, type, cost);
    }

    @Override
    public int calculateCost(List<Lecture> lectures) {
        int price = lectures.stream()
                .map(Lecture::getCost)
                .reduce(0, Integer::sum);
        if (lectures.size() >= 3) {
            return (int) (price * 0.7);
        }
        return price;
    }
}
