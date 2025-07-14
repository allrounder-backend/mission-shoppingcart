package mission.application.domain.lecture;

import java.util.List;
import mission.application.domain.Lecture;
import mission.application.domain.enums.LectureType;

public class DevOpsLecture extends Lecture {
    public DevOpsLecture() {
        super();
    }

    public DevOpsLecture(int id, String name, LectureType type, int cost) {
        super(id, name, type, cost);
    }

    @Override
    public int calculateCost(List<Lecture> lectures) {
        return (int) (lectures.stream()
                .map(Lecture::getCost)
                .reduce(0, Integer::sum) * 0.9);
    }
}
