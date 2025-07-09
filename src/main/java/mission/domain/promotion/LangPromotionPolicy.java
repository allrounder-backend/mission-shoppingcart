package mission.domain.promotion;

import java.util.Comparator;
import java.util.List;
import mission.domain.lecture.Lecture;

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
