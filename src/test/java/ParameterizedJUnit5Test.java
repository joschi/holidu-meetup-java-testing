import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

class ParameterizedJUnit5Test {
    @ParameterizedTest(name = "Test case #{index}: sum(0..{0}) == {1}")
    @CsvSource({"0, 0", "1, 1", "2, 3", "3, 6", "4, 10", "5, 15", "6, 21"})
    void test(int input, int expected) {
        int sum = IntStream.rangeClosed(0, input).sum();
        assertEquals(expected, sum);
    }
}
