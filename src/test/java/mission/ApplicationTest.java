package mission;

import static org.junit.jupiter.api.Assertions.assertTrue;

import api.TestEnvironment;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ApplicationTest extends TestEnvironment {
    @Test
    void testApplication() {
        run(List.of("250000", "1, 14, 17"));
        assertTrue(output().contains("over"));
        assertTrue(output().contains("3,000won"));
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
