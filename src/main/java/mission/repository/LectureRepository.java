package mission.repository;

import java.util.HashMap;
import java.util.Map;
import mission.domain.Lecture;
import mission.domain.Type;
import mission.message.ExceptionMessage;

public class LectureRepository {
    Map<Integer, Lecture> repository = new HashMap<>();
    int indexCounter = 0;

    public void add(int id, String name, String typeName, int price){
        repository.put(id, new Lecture(name, Type.createType(typeName), price));
        if(id>indexCounter) indexCounter = id;
    }

    public void add(String name, String typeName, int price){
        repository.put(indexCounter+1, new Lecture(name, Type.createType(typeName), price));
        indexCounter++;
    }

    public Lecture getLecture(int id){
        Lecture lecture = repository.get(id);
        if(lecture==null) throw new IndexOutOfBoundsException(ExceptionMessage.INVALID_LECTURE);
        return lecture;
    }

    public boolean isIndex(int number){
        return number<indexCounter && number>-1;
    }
}
