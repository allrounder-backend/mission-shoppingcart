package mission;

import api.Console;
import java.util.*;


class Lecture {
    String type;
    int price;

    Lecture(String type, int price){
        this.type = type;
        this.price = price;
    }
}

public class Application {
    public static void main(String[] args){
        List<Lecture> lectures = new ArrayList<>();
        lectures.add(new Lecture("DevOps", 77000));    // ID 1
        lectures.add(new Lecture("DevOps", 44000));   // ID 2
        lectures.add(new Lecture("DevOps", 66000));   // ID 3
        lectures.add(new Lecture("DBMS", 69300));   // ID 4
        lectures.add(new Lecture("DBMS", 66000));   // ID 5
        lectures.add(new Lecture("DBMS", 16500));   // ID 6
        lectures.add(new Lecture("Lang", 35200));   // ID 7
        lectures.add(new Lecture("Lang", 44000));   // ID 8
        lectures.add(new Lecture("Lang", 88000));   // ID 9
        lectures.add(new Lecture("F/W", 88000));   // ID 10
        lectures.add(new Lecture("F/W", 99000));   // ID 11
        lectures.add(new Lecture("F/W", 57200));   // ID 12
        lectures.add(new Lecture("F/W", 88000));   // ID 13
        lectures.add(new Lecture("CS", 132000));   // ID 14
        lectures.add(new Lecture("CS", 44000));   // ID 15
        lectures.add(new Lecture("CS", 77000));   // ID 16
        lectures.add(new Lecture("CS", 44000));   // ID 17
    

        Console.println("총 예산을 입력 해주세요.");
        Console.print("> ");
        int totalBudget = Integer.parseInt(Console.readLine().trim());
        
        String [] types = {"DevOps","DBMS","Lang","F/W","CS"};
        int [] typeBudget = new int[types.length];
        Console.println("유형별 예산을 입력해 주세요.");
        Console.print("> ");
        String[] tbparts = Console.readLine().split(",");

        for(String part: tbparts){
            String[] ab = part.split("-");
            String t = ab[0];
            int b = Integer.parseInt(ab[1].trim());


            for(int i=0; i<types.length; i++){
                if(types[i].equals(t)){
                    typeBudget[i] = b;
                    break;
                }
            }
        }
    
        

        Console.println("구입할 강의 목록을 입력해주세요.");
        Console.print("> ");
        String [] parts = Console.readLine().split(",");

        int[] cart = new int[parts.length];
        for(int i=0; i<parts.length; i++){
            cart[i] = Integer.parseInt(parts[i].trim());
        }

        int totalCost = 0;
        int[] typeCost = new int[types.length];
        
        for (int id:cart) {
            Lecture lec = lectures.get(id-1);
            totalCost = totalCost + lec.price;

            for (int i=0;i<types.length;i++) {
                if(lec.type.equals(types[i])){
                    typeCost[i] =  typeCost[i] + lec.price;
                    break;
                }
            }
        }
        
        Console.println(" ");
        boolean anyExceed = false;

        if(totalCost > totalBudget){
            anyExceed = true;
        }

        for(int i=0; i<types.length; i++){
            if(typeCost[i] > typeBudget[i]){
                anyExceed = true;
                break;
            }
        }

        if(anyExceed){
            Console.println("예산을 초과했습니다.");

            if(totalCost > totalBudget){
                Console.println(" - 총 예산: "+ (totalCost - totalBudget)+"원 초과");
            }else{
                Console.println(" - 총 예산 : OK");
            }

            for(int i=0; i<types.length; i++){
                if (typeCost[i] > typeBudget[i]) {
                    Console.println(" - " + types[i] + " : " + (typeCost[i] - typeBudget[i]) + "원 초과");
                } else {
                    Console.println(" - " + types[i] + " : OK");
                }
            }
        }else{
            Console.println("예산을 초과하지 않았습니다.");
        }
        
    }


}


