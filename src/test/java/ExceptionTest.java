import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionTest {
    // This test is incorrect!
    @org.junit.Test
    public void testExceptionIncorrect() {
        try {
            // Should throw an IllegalArgumentException
            doSomething(0);
        } catch (Throwable t) {
            assertTrue(t instanceof IllegalArgumentException);
            assertEquals("Argument has to be positive or zero", t.getMessage());
        }
    }

    @org.junit.Test
    public void testException1() {
        try {
            doSomething(-1);
            fail("Exception expected");
        } catch (Throwable t) {
            assertTrue(t instanceof IllegalArgumentException);
            assertEquals("Argument has to be positive or zero", t.getMessage());
        }
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testException2() {
        doSomething(-1);
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @org.junit.Test
    public void testException3() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Argument has to be positive or zero");
        doSomething(-1);
    }

    @DisplayName("Use assertThrows for exceptions")
    @org.junit.jupiter.api.Test
    void testException4() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> doSomething(-1));
        org.junit.jupiter.api.Assertions.assertEquals("Argument has to be positive or zero", e.getMessage());
    }

    @DisplayName("Use AssertJ for exceptions")
    @org.junit.jupiter.api.Test
    void testException5() {
        Assertions.assertThatThrownBy(() -> doSomething(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Argument has to be positive or zero");
    }

    private void doSomething(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Argument has to be positive or zero");
        }
    }
}
