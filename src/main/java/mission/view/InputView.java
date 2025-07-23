package mission.view;

import api.Console;
import java.util.List;
import mission.domain.GroupedPricesByType;
import mission.utility.DebugLogging;
import mission.message.SystemMessage;
import mission.validator.InputFormatValidator;

public class InputView {
    public int inputTotalBudget(){
        try{
            System.out.println(SystemMessage.INPUT_TOTAL_BUDGET);
            return InputFormatValidator.totalBudgetValidate(Console.readLine());
        }catch (Exception e){
            DebugLogging.addTrace(this.getClass().getName()); //디버깅 로그 추가
            throw e;
        }
    }

    public List<GroupedPricesByType> inputTypeBudget(){
        try{
            System.out.println(SystemMessage.INPUT_TYPE_BUDGET);
            return InputFormatValidator.typeBudgetValidate(Console.readLine());
        }catch (Exception e){
            DebugLogging.addTrace(this.getClass().getName()); //디버깅 로그 추가
            throw e;
        }
    }

    public List<Integer> inputLectureList(){
        try{
            System.out.println(SystemMessage.INPUT_PURCHASE_LIST);
            return InputFormatValidator.lectureListIsNumberValidate(Console.readLine());
        }catch (Exception e){
            DebugLogging.addTrace(this.getClass().getName()); //디버깅 로그 추가
            throw e;
        }
    }
}
