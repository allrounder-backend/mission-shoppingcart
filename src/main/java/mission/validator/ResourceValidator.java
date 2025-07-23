package mission.validator;

import java.util.ArrayList;
import java.util.List;
import mission.dto.LectureResourceDTO;
import mission.message.ExceptionMessage;
import mission.utility.DebugLogging;

public class ResourceValidator {
    static final int LECTURE_COLUMN_SIZE = 4;
    static final int PROMOTION_COLUMN_SIZE = 2;

    public static List<LectureResourceDTO> isLectureValid(List<String> input){
        try {
            List<LectureResourceDTO> result = new ArrayList<>();
            if(input.size() % LECTURE_COLUMN_SIZE != 0) {
                DebugLogging.flagSuspicious(input.toString()+"\n"+String.valueOf(input.size()));
                throw new RuntimeException();
            }
            for(int i=0; i<input.size(); i+=LECTURE_COLUMN_SIZE){
                int index = Integer.parseInt(input.get(i));
                String name = input.get(i+1);
                String typeName = input.get(i+2);
                int price = Integer.parseInt(input.get(i+3).replace(",",""));
                result.add(new LectureResourceDTO(index, name, typeName, price));
            }
            return result;
        }catch (Exception e){
            DebugLogging.addTrace(ResourceValidator.class.getName());
            throw new IllegalStateException(ExceptionMessage.RESULT_FILE_ERROR);
        }
    }

    //public List<>
}
