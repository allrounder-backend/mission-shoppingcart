package mission.domain.promotion.implement;

import java.util.List;
import mission.domain.lecture.Lecture;
import mission.domain.promotion.PromotionPolicy;

public class FwPromotionPolicy implements PromotionPolicy {
    @Override
    public int apply(List<Lecture> lectures) {
        int total = lectures.stream().mapToInt(Lecture::price).sum();
        return total > 90000 ? total - 30000 : total;
    }
}
