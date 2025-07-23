package mission;

import static org.junit.jupiter.api.Assertions.assertTrue;

import api.Application;
import api.TestEnvironment;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ApplicationTest extends TestEnvironment {
    @Test
    void testApplication() {
        run(List.of("400000", "DevOps-50000,DBMS-50000,F/W-50000,CS-50000", "1, 6, 11, 12, 14, 15, 16"));
        System.out.println(output());
        System.err.println("Current directory: " + new File(".").getAbsolutePath());
        System.err.println("Files in ./compiled/:");
        Arrays.stream(new File("compiled").listFiles()).forEach(System.err::println);
        assertTrue(output().contains("예산을 초과했습니다."));
        assertTrue(output().contains("19,300원"));
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
