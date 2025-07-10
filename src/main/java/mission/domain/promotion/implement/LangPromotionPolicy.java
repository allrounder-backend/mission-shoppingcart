package mission.domain.promotion.implement;

import java.util.Comparator;
import java.util.List;
import mission.domain.lecture.Lecture;
import mission.domain.promotion.PromotionPolicy;

public class LangPromotionPolicy implements PromotionPolicy {
    @Override
    public int apply(List<Lecture> lectures) {
        if (lectures.size() < 2) {
            return lectures.stream().mapToInt(Lecture::price).sum();
        }

        int min = lectures.stream()
                .min(Comparator.comparingInt(Lecture::price))
                .map(Lecture::price)
                .orElse(0);

        return lectures.stream().mapToInt(Lecture::price).sum() - min;
    }
}
