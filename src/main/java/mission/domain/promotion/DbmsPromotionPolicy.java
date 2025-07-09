package mission.domain.promotion;

import java.util.List;
import mission.domain.lecture.Lecture;

public class DbmsPromotionPolicy implements PromotionPolicy {
    @Override
    public int apply(List<Lecture> lectures) {
        return lectures.stream()
                .mapToInt(l -> Math.max(0, l.price() - 5000))
                .sum();
    }
}
