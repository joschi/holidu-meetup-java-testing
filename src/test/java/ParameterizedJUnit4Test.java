import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedJUnit4Test {
    @Parameterized.Parameters(name = "Test case #{index}: sum(0..{0}) == {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 0}, {1, 1}, {2, 3}, {3, 6}, {4, 10}, {5, 15}, {6, 21}
        });
    }

    private final int input;
    private final int expected;

    public ParameterizedJUnit4Test(int input, int expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void test() {
        int sum = IntStream.rangeClosed(0, input).sum();
        assertEquals(expected, sum);
    }
}
