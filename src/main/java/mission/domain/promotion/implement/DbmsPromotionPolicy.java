package mission.domain.promotion.implement;

import java.util.List;
import mission.domain.lecture.Lecture;
import mission.domain.promotion.PromotionPolicy;

public class DbmsPromotionPolicy implements PromotionPolicy {
    @Override
    public int apply(List<Lecture> lectures) {
        return lectures.stream()
                .mapToInt(l -> Math.max(0, l.price() - 5000))
                .sum();
    }
}
