package mission;

import api.Console;
import java.io.*;
import java.util.*;

public class Application {
    public static void main(String[] args) {
        //TODO: 미션 구현

        // 강의 목록 저장
        List<Lecture> lectures = getLectures("lectures.csv");

        // 예산과 강의 목록 입력
        System.out.print("총 예산을 입력해 주세요.\n> ");
        int budget = Integer.parseInt(api.Console.readLine());

        System.out.print("구입할 강의 목록을 입력해주세요.\n> ");
        String[] lectureList  = api.Console.readLine().split(",");

        // 총액 구한 후 결과 출력
        int sum = 0;
        for (String s : lectureList) {
            sum += lectures.get(Integer.parseInt(s.trim())-1)
                    .cost;
        }
        int excess = sum - budget;

        if(excess <= 0){
            System.out.println("예산을 초과하지 않았습니다.");
        } else {
            System.out.printf("예산을 초과했습니다. (초과 금액 %,d원)%n", excess);
        }
    }
    private static List<Lecture> getLectures(String fileName){
        List<Lecture> lectures = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;

            reader.readLine();

            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String type = parts[2];

                String sCost1 = parts[3];
                String sCost2 = parts[4];
                sCost1 = sCost1.replace("\"", "");
                sCost2 = sCost2.replace("\"", "");
                int cost = Integer.parseInt(sCost1 + sCost2);
                // System.out.println(id + " " + title + " " + type + " " + cost);

                lectures.add(new Lecture(id, title, type, cost));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return lectures;
    }
}
