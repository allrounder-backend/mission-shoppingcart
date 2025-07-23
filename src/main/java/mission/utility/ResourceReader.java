package mission.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import mission.message.ExceptionMessage;

public class ResourceReader {
    public static List<String> readFile(String fileName){
        List<String> lines = new ArrayList<>();
        try (InputStream inputStream = ResourceReader.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException | NullPointerException e) {
            DebugLogging.addTrace(ResourceReader.class.getName()); //디버깅 로그 추가
            throw new RuntimeException(ExceptionMessage.READING_FILE_ERROR, e);
        }
        return lines;
    }
}
