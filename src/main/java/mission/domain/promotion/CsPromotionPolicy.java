package mission.domain.promotion;

import java.util.List;
import mission.domain.lecture.Lecture;

public class CsPromotionPolicy implements PromotionPolicy {
    @Override
    public int apply(List<Lecture> lectures) {
        if (lectures.size() >= 3) {
            return (int) (lectures.stream().mapToInt(Lecture::price).sum() * 0.7);
        }
        return lectures.stream().mapToInt(Lecture::price).sum();
    }
}
