import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.ProvideSystemProperty;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SystemRulesTest {
    @Rule
    public final ProvideSystemProperty properties =
            new ProvideSystemProperty("prop1", "value")
                    .and("prop2", null);

    @Test
    public void overrideProperty() {
        assertEquals("value", System.getProperty("prop1"));
        assertNull(System.getProperty("prop2"));
    }

    @Rule
    public final EnvironmentVariables environmentVariables = new EnvironmentVariables().set("MY_ENV", "value");

    @Test
    public void setEnvironmentVariable() {
        assertEquals("value", System.getenv("MY_ENV"));
    }

    @Rule
    public final SystemErrRule systemErrRule = new SystemErrRule().enableLog();

    @Test
    public void writesTextToSystemErr() {
        System.err.print("hello world");
        assertEquals("hello world", systemErrRule.getLog());
    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog()
            .muteForSuccessfulTests();

    @Test
    public void writesTextToSystemOut() {
        System.out.print("hello world");
        assertEquals("hello world", systemOutRule.getLog());
    }

    @Test
    public void writesTextToSystemOutAndFails() {
        System.out.print("hello world");
        throw new RuntimeException("Boom");
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void exits() {
        exit.expectSystemExitWithStatus(1);
        System.exit(1);
    }
}
