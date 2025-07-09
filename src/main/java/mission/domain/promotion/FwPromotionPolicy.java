package mission.domain.promotion;

import java.util.List;
import mission.domain.lecture.Lecture;

public class FwPromotionPolicy implements PromotionPolicy {
    @Override
    public int apply(List<Lecture> lectures) {
        int total = lectures.stream().mapToInt(Lecture::price).sum();
        return total > 90000 ? total - 30000 : total;
    }
}
