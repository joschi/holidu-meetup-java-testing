import dk.bitcraft.lc.JUnit4LogCollector;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class LogCollectorTest {
    private static final Logger logger = LoggerFactory.getLogger("holidu");

    @Rule
    public final JUnit4LogCollector collector = new JUnit4LogCollector(logger);

    @Test
    public void test() {
        logger.warn("warn");
        logger.error("error");

        List<String> logs = collector.getLogs();
        assertThat(logs).hasSize(2);
    }
}
