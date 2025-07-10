package mission;

public enum Lecture {
    LEC1("1", "쿠버네티스 어나더 클래스", "DevOps", 77_000),
    LEC2("2", "이론과 실습으로 시력이 높아지는 대세는 쿠버네티스", "DevOps", 44_000),
    LEC3("3", "비전공지도 이해할 수 있는 AWS 입문/실전", "DevOps", 66_000),
    LEC4("4", "처음하는 MongoDB와 NoSQL", "DMBS", 69_300),
    LEC5("5", "비전공자도 이해할 수 있는 DB 설계 입문/실전", "DMBS", 66_000),
    LEC6("6", "데이터 분석을 위한 기초 SQL", "DMBS", 16_500),
    LEC7("7", "한 번에 끝내는 자바스크립트", "Lang", 35_200),
    LEC8("8", "실전 자바 - 기본편", "Lang", 44_000),
    LEC9("9", "코딩으로 학습하는 GoF의 디자인 패턴", "Lang", 88_000),
    LEC10("10", "스프링 핵심 원리 - 기본편", "F/W", 88_000),
    LEC11("11", "스프링 MVC 1편 - 백엔드 웹 개발 핵심 기술", "F/W", 99_000),
    LEC12("12", "Spring Boot를 활용하여 채팅 플랫폼 만들어보기", "F/W", 57_200),
    LEC13("13", "실전! 스프링 부트와 JPA 활용", "F/W", 88_000),
    LEC14("14", "실습으로 배우는 핵심 네트워크 기술", "CS", 132_000),
    LEC15("15", "모든 개발자를 위한 HTTP 웹 기본 지식", "CS", 44_000),
    LEC16("16", "그림으로 쉽게 배우는 운영체제", "CS", 77_000),
    LEC17("17", "외워서 끝내는 SSL과 최소한의 암호기술", "CS", 44_000);

    private final String id;
    private final String name;
    private final String category;
    private final int price;

    Lecture(String id, String name, String category, int price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }
}
