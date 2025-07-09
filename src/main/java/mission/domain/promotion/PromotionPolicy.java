package mission.domain.promotion;

import java.util.List;
import mission.domain.lecture.Lecture;

@FunctionalInterface
public interface PromotionPolicy {
    int apply(List<Lecture> lectures);
}
