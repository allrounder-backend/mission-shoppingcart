package mission.domain.lecture;

import java.util.*;

public class LectureRepository {

    private static final List<Lecture> lectures = List.of(
            new Lecture(1, "쿠버네티스 어나더 클래스", LectureType.DEVOPS, 77000),
            new Lecture(2, "이론과 실습으로 실력이 높아지는 대세는 쿠버네티스", LectureType.DEVOPS, 44000),
            new Lecture(3, "비전공자도 이해할 수 있는 AWS 입문/실전", LectureType.DEVOPS, 66000),
            new Lecture(4, "처음하는 MongoDB와 NoSQL", LectureType.DBMS, 69300),
            new Lecture(5, "비전공자도 이해할 수 있는 DB 설계 입문/실전", LectureType.DBMS, 66000),
            new Lecture(6, "데이터 분석을 위한 기초 SQL", LectureType.DBMS, 16500),
            new Lecture(7, "한 번에 끝내는 자바스크립트", LectureType.LANG, 35200),
            new Lecture(8, "실전 자바 - 기본편", LectureType.LANG, 44000),
            new Lecture(9, "코딩으로 학습하는 GoF의 디자인 패턴", LectureType.LANG, 88000),
            new Lecture(10, "스프링 핵심 원리 - 기본편", LectureType.FW, 88000),
            new Lecture(11, "스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술", LectureType.FW, 99000),
            new Lecture(12, "Spring Boot를 활용하여 채팅 플랫폼 만들어보기", LectureType.FW, 57200),
            new Lecture(13, "실전! 스프링 부트와 JPA 활용", LectureType.FW, 88000),
            new Lecture(14, "실습으로 배우는 핵심 네트워크 기술", LectureType.CS, 132000),
            new Lecture(15, "모든 개발자를 위한 HTTP 웹 기본 지식", LectureType.CS, 44000),
            new Lecture(16, "그림으로 쉽게 배우는 운영체제", LectureType.CS, 77000),
            new Lecture(17, "외워서 끝내는 SSL과 최소한의 암호기술", LectureType.CS, 44000)
    );

    public List<Lecture> findByIds(List<Integer> ids) {
        List<Lecture> found = lectures.stream()
                .filter(l -> ids.contains(l.id()))
                .toList();
        if (found.size() != ids.size()) {
            throw new IllegalArgumentException("강의 목록에 없는 강의가 입력됨.");
        }
        return found;
    }
}
