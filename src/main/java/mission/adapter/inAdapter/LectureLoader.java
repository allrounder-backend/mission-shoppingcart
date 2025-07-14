package mission.adapter.inAdapter;

import java.util.List;
import mission.application.domain.Lecture;
import mission.application.domain.enums.LectureType;
import mission.application.port.inport.LectureDatabase;

public class LectureLoader implements LectureDatabase {
    private static final LectureLoader instance = new LectureLoader();

    private final List<Lecture> lectureList;

    LectureLoader() {
        lectureList = List.of(
                new Lecture(1, "쿠버네티스 어나더 클래스", LectureType.DevOps, 77_000),
                new Lecture(2, "이론과 실습으로 실력이 높아지는 대세는 쿠버네티스", LectureType.DevOps, 44_000),
                new Lecture(3, "비전공자도 이해할 수 있는 AWS 입문/실전", LectureType.DevOps, 66_000),
                new Lecture(4, "처음하는 MongoDB와 NoSQL", LectureType.DBMS, 69_300),
                new Lecture(5, "비전공자도 이해할 수 있는 DB 설계 입문/실전", LectureType.DBMS, 66_000),
                new Lecture(6, "데이터 분석을 위한 기초 SQL", LectureType.DBMS, 16_500),
                new Lecture(7, "한 번에 끝내는 자바스크립트", LectureType.Lang, 35_200),
                new Lecture(8, "실전 자바 - 기본편", LectureType.Lang, 44_000),
                new Lecture(9, "코딩으로 학습하는 GoF의 디자인 패턴", LectureType.Lang, 88_000),
                new Lecture(10, "스프링 핵심 원리 - 기본편", LectureType.FW, 88_000),
                new Lecture(11, "스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술", LectureType.FW, 99_000),
                new Lecture(12, "Spring Boot를 활용하여 채팅 플랫폼 만들어보기", LectureType.FW, 57_200),
                new Lecture(13, "실전! 스프링 부트와 JPA 활용", LectureType.FW, 88_000),
                new Lecture(14, "실습으로 배우는 핵심 네트워크 기술", LectureType.CS, 132_000),
                new Lecture(15, "모든 개발자를 위한 HTTP 웹 기본 지식", LectureType.CS, 44_000),
                new Lecture(16, "그림으로 쉽게 배우는 운영체제", LectureType.CS, 77_000),
                new Lecture(17, "외워서 끝내는 SSL과 최소한의 암호기술", LectureType.CS, 44_000)
        );
    }

    public static LectureLoader getInstance() {
        return instance;
    }

    @Override
    public Lecture findLecture(int lectureId) {
        return lectureList.stream()
                .filter(lecture -> lecture.getId() == lectureId)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Can't find lecture, id = " + lectureId));
    }
}
