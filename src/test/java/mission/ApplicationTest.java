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

    /*@Test
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
     */


    @Test
    void testApplicationWithPromotion() {
        run(List.of(
                "400000",
                "DevOps-50000,DBMS-50000,F/W-50000,CS-50000",
                "1, 6, 11, 12, 14, 15, 16"
        ));


        String out = output();

        assertTrue(out.contains("budget over"), "Expected 'budget over' message");
        assertTrue(out.contains(" - Total budget : OK"), "Expected 'Total budget : OK'");
        assertTrue(out.contains(" - DevOps : 19,300won over"), "Expected 'DevOps : 19,300won over'");
        assertTrue(out.contains(" - DBMS : OK"), "Expected 'DBMS : OK'");
        assertTrue(out.contains(" - F/W : 76,200won over"), "Expected 'F/W : 76,200won over'");
        assertTrue(out.contains(" - CS : 127,100won over"), "Expected 'CS : 200won over'");
    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
