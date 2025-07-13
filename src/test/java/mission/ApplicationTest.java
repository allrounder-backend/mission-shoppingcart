package mission;

import static org.junit.jupiter.api.Assertions.assertTrue;

import api.TestEnvironment;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ApplicationTest extends TestEnvironment {
    /*
    @Test
    void testApplication() {
        run(List.of("250000", "1, 14, 17"));
        assertTrue(output().contains("over"));
        assertTrue(output().contains("3,000won"));
    }
     */

    @Test
    void testApplication() {
        run(List.of(
                "500000",
                "DevOps-200000,F/W-250000,CS-100000",
                "1, 14, 17"
        ));

        assertTrue(output().contains("budget over"));
        assertTrue(output().contains("Total budget : OK"));
        assertTrue(output().contains("DevOps : OK"));
        assertTrue(output().contains("F/W : OK"));
        assertTrue(output().contains("CS :"));
    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
