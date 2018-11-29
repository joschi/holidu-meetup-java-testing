import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.Timeout;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class TimeoutTest {
    @org.junit.Test
    public void testTimeoutIncorrect() {
        boolean successful = doSomething();
        assertTrue(successful);
    }

    @org.junit.Test(timeout = 1000L)
    public void testTimeout1() {
        boolean successful = doSomething();
        assertTrue(successful);
    }

    // JUnit 4
    @Rule
    public final Timeout globalTimeout = Timeout.seconds(4L);

    @org.junit.Test
    public void testTimeout2() {
        boolean successful = doSomething();
        assertTrue(successful);
    }

    @DisplayName("Use assertTimeout for timeout")
    @org.junit.jupiter.api.Test
    void testTimeout3() {
        Boolean successful = assertTimeout(Duration.ofSeconds(1L), this::doSomething);
        org.junit.jupiter.api.Assertions.assertTrue(successful);
    }

    @DisplayName("Use Awaitility for timeout")
    @org.junit.jupiter.api.Test
    void testTimeout4() {
        await()
                .atMost(1, SECONDS)
                .until(this::doSomething);
    }

    @SuppressWarnings("all")
    private boolean doSomething() {
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }
}
