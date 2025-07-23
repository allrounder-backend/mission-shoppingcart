package mission.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import mission.domain.Type;
import mission.domain.GroupedPricesByType;
import mission.utility.DebugLogging;
import mission.message.ExceptionMessage;

public class InputFormatValidator {
    //총 예산 입력 검증
    public static int totalBudgetValidate(String input){
        try{
            return Integer.parseInt(input);
        }catch (Exception e){
            DebugLogging.addTrace(InputFormatValidator.class.getName()); //디버깅 로그 추가
            throw new IllegalArgumentException(ExceptionMessage.INVALID_MONEY);
        }
    }

    //유형 별 예산 입력 검증
    public static List<GroupedPricesByType> typeBudgetValidate(String input){
        List<GroupedPricesByType> result = new ArrayList<>();
        try{
            for (String entry : input.split(",")) {
                String[] parts = entry.split("-");

                if(parts.length<2){
                    throw new IllegalArgumentException(ExceptionMessage.INVALID_FORMAT);
                }
                if(!Type.exists(parts[0])){
                    throw new IllegalArgumentException(ExceptionMessage.INVALID_TYPE);
                }

                Type type = Type.createType(parts[0]);
                int price = Integer.parseInt(parts[1]);
                result.add(new GroupedPricesByType(type, price));
            }
            return result;
        }catch (Exception e){
            DebugLogging.addTrace(InputFormatValidator.class.getName()); //디버깅 로그 추가
            throw e;
        }
    }

    public static List<Integer> lectureListIsNumberValidate(String input){
        try {
            String[] splitted = input.split(",");
            return Arrays.stream(splitted).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
        }catch (Exception e){
            DebugLogging.addTrace(InputFormatValidator.class.getName()); //디버깅 로그 추가
            throw new IllegalArgumentException(ExceptionMessage.INVALID_FORMAT);
        }

    }

}
