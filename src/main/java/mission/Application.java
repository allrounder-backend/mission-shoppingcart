package mission;

import api.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Lecture{
    private int id;
    private String name;
    private String type;
    private int price;


    public Lecture(int id, String name, String type, int price){
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public int getId(){
        return id;
    }

    public int getPrice(){
        return price;
    }
}

public class Application {
    public static void main(String[] args) {
        //TODO: 미션 구현
        List<Lecture> lectures = new ArrayList<>(
                Arrays.asList(
                        new Lecture(1, "쿠버네티스 어나더 클래스", "DevOps", 77000),
                        new Lecture(2, "이론과 실습으로 실력이 높아지는 대세는 쿠버네티스", "DevOps", 44000),
                        new Lecture(3, "비전공자도 이해할 수 이쓴 AWS 입문/실전", "DevOps", 66000),
                        new Lecture(4, "처음하는 MongoDB와 NoSQL", "DBMS", 69300),
                        new Lecture(5, "비전공자도 이해할 수 있는 DB 설계 입문/실전", "DBMS", 66000),
                        new Lecture(6, "데이터 분석을 위한 기초 SQL", "DBMS", 16500),
                        new Lecture(7, "한 번에 끝내는 자바스크립트", "Lang", 35200),
                        new Lecture(8, "실전 자바-기본편", "Lang", 44000),
                        new Lecture(9, "코딩으로 학습하는 GoF의 디자인 패턴", "Lang", 88000),
                        new Lecture(10, "스프링 핵심 원리-기본편", "F/W", 88000),
                        new Lecture(11, "스프링 MVC 1편-벡엔드 웹 개발 핵심 기술", "F/W", 99000),
                        new Lecture(12, "Spring Boot를 활용하여 채팅 플랫폼 만들어보기", "F/W", 57200),
                        new Lecture(13, "실전!스프링 부트와 JPA 활용", "F/W", 88000),
                        new Lecture(14, "실습으로 배우는 핵심 네트워크 기술", "CS", 132000),
                        new Lecture(15, "모든 개발자를 위한 HTTP 웹 기본 지식", "CS", 44000),
                        new Lecture(16, "그림으로 쉽게 배우는 운영체계", "CS", 77000),
                        new Lecture(17, "외워서 끝내는 SSL과 최소한의 암호기술", "CS", 44000)
                )
        );

        System.out.println("Enter your total budget.");

        int budget = Integer.parseInt(Console.readLine());

        if(budget <= 0){
            throw new IllegalArgumentException("budget must be a positive number");
        }

        System.out.println("Enter your lecture list to buy");

        String input = Console.readLine();
        String[] tokens = input.split(",");
        int[] lectureList = new int[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            lectureList[i] = Integer.parseInt(tokens[i].trim());
        }

        int total = 0;
        for(int i=0; i<lectureList.length; i++){
            total += getPricebyId(lectures, lectureList[i]);
        }

        if(total > budget){
            System.out.println("over");
            System.out.printf("%,dwon%n", (total - budget));
        }
        else
            System.out.println("not over");


    }

    static int getPricebyId(List<Lecture> lectures, int id){
        for(Lecture lec : lectures){
            if(lec.getId() == id){
                return lec.getPrice();
            }
        }
        throw new IllegalArgumentException("lecture with id " + id + " not found");
    }

}
