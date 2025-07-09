package mission;

import static org.junit.jupiter.api.Assertions.assertTrue;

import api.TestEnvironment;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ApplicationTest extends TestEnvironment {
    @Test
    void testApplication() {
        run(List.of("250000", "1, 3, 10, 11, 15, 16"));
        assertTrue(output().contains("예산을 초과했습니다."));
        assertTrue(output().contains("3,000원"));
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
