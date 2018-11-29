import org.junit.jupiter.api.DisplayName;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class AssumptionsTest {
    @org.junit.Test
    public void testAssumptionsJUnit4() {
        LocalDate date = LocalDate.of(2018, 11, 29);
        org.junit.Assume.assumeTrue("Only run on Sunday", date.getDayOfWeek() == DayOfWeek.SUNDAY);
    }

    @DisplayName("Assumptions with JUnit5")
    @org.junit.jupiter.api.Test
    void testAssumptionsJUnit5() {
        LocalDate date = LocalDate.of(2018, 11, 29);
        org.junit.jupiter.api.Assumptions.assumeTrue(date.getDayOfWeek() == DayOfWeek.SUNDAY, "Only run on Sunday");
    }

    @DisplayName("Assumptions with AssertJ")
    @org.junit.jupiter.api.Test
    void testAssumptionsAssertJ() {
        LocalDate date = LocalDate.of(2018, 11, 29);
        org.assertj.core.api.Assumptions.assumeThat(date.getDayOfWeek())
                .describedAs("Only run on Sunday")
                .isEqualTo(DayOfWeek.SUNDAY);
    }
}
