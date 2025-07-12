package mission.domain.cart;

import java.util.*;
import java.util.stream.Collectors;
import mission.domain.lecture.Lecture;
import mission.domain.lecture.LectureType;

public record Cart(List<Lecture> lectures) {

    public int calculateTotalPrice() {
        Map<LectureType, List<Lecture>> grouped = lectures.stream()
                .collect(Collectors.groupingBy(Lecture::type));

        return grouped.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(Lecture::price)
                .sum();
    }

    @Override
    public List<Lecture> lectures() {
        return Collections.unmodifiableList(lectures);
    }
}
