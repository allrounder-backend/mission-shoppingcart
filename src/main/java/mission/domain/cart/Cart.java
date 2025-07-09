package mission.domain.cart;

import java.util.*;
import java.util.stream.Collectors;
import mission.domain.lecture.Lecture;
import mission.domain.lecture.LectureType;
import mission.domain.promotion.PromotionPolicy;
import mission.domain.promotion.PromotionPolicyFactory;

public record Cart(List<Lecture> lectures) {

    public int calculateTotalPrice() {
        Map<LectureType, List<Lecture>> grouped = lectures.stream()
                .collect(Collectors.groupingBy(Lecture::type));

        return grouped.entrySet().stream()
                .mapToInt(entry -> {
                    PromotionPolicy policy = PromotionPolicyFactory.getPolicy(entry.getKey());
                    return policy.apply(entry.getValue());
                })
                .sum();
    }

    @Override
    public List<Lecture> lectures() {
        return Collections.unmodifiableList(lectures);
    }
}
