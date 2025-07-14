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
        int totalBudget = Integer.parseInt(api.Console.readLine());
        if(totalBudget < 0){
            System.out.println("IllegalArgumentException : 올바른 금액을 입력해주세요.");
            return;
        }

        System.out.print("유형별 예산을 입력해 주세요.\n> ");
        String[] budgetList = api.Console.readLine().split(",");
        Map<String, Integer> budgetMap  = new LinkedHashMap<>();
        Map<String, Integer> costMap  = new LinkedHashMap<>();
        for(String s: budgetList){
            if(s.isEmpty()){
                continue;
            }
            String[] arr = s.split("-");
            String type = arr[0].trim();
            Integer budget = Integer.valueOf(arr[1].trim());
            budgetMap.put(type, budget);
            costMap.put(type, 0);
        }

        System.out.print("구입할 강의 목록을 입력해주세요.\n> ");
        String[] lectureList  = api.Console.readLine().split(",");

        // 총액 구한 후 결과 출력
        int sum = 0;
        for (String s : lectureList) {
            int index = Integer.parseInt(s.trim()) - 1;
            if(index < 0 || index >= lectures.size()){
                System.out.println("강의 목록에 강의가 없습니다.");
                return;
            }
            Lecture lec = lectures.get(index);
            sum += lec.cost;
            if(!costMap.containsKey(lec.type)){
                costMap.put(lec.type, 0);
            }
            costMap.put(lec.type, costMap.get(lec.type) + lec.cost);
        }

        int excess = sum - totalBudget;
        budgetMap.forEach((type, budget) -> {
            budgetMap.put(type, budget - costMap.get(type));
        });

        boolean isExcess = false;
        for(Map.Entry<String, Integer> entry: budgetMap.entrySet()){
            if(entry.getValue() < 0) {
                isExcess = true;
                break;
            }
        }

        if(excess <= 0 && !isExcess){
            System.out.println("예산을 초과하지 않았습니다.");
        } else {
            System.out.println("예산을 초과했습니다.");
        }

        if(excess <= 0){
            System.out.println("- 총 예산 : OK");
        } else {
            System.out.printf("- 총 예산 : %,d원 초과%n", excess);
        }
        budgetMap.forEach((type, budget) -> {
            System.out.printf("- %s : ", type);
            if(budget >= 0)
                System.out.println("OK");
            else
                System.out.println(-budget+"원 초과");
        });
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
