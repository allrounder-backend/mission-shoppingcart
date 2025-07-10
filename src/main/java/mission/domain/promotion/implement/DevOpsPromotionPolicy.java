package mission.domain.promotion.implement;

import java.util.List;
import mission.domain.lecture.Lecture;
import mission.domain.promotion.PromotionPolicy;

public class DevOpsPromotionPolicy implements PromotionPolicy {
    @Override
    public int apply(List<Lecture> lectures) {
        return lectures.stream()
                .mapToInt(l -> (int) (l.price() * 0.9))
                .sum();
    }
}
