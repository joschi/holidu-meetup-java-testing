import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertionsTest {
    @org.junit.Test
    public void testAssertJUnit4() {
        List<Integer> list = Arrays.asList(1, 1, 2, 2, 3, 4, 5, 5);
        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());
        Assert.assertEquals(Arrays.asList(1, 1, 2, 2, 3, 4, 5, 5), list);
    }
    
    @DisplayName("Assertions for Optional with AssertJ")
    @Test
    void testOptionalAssertJ() {
        Optional<String> s = Optional.of("test");
        assertThat(s)
                .isPresent()
                .containsInstanceOf(String.class)
                .contains("test");

        OptionalInt i = OptionalInt.of(42);
        assertThat(i)
                .isPresent()
                .hasValue(42);

        Optional<String> empty = Optional.empty();
        assertThat(empty).isEmpty();
    }

    @DisplayName("Assertions for collections with AssertJ")
    @Test
    void testCollectionsAssertJ() {
        List<Integer> list = Arrays.asList(1, 1, 2, 2, 3, 4, 5, 5);
        assertThat(list)
                .isNotNull()
                .isNotEmpty()
                .containsSequence(2, 3, 4)
                .containsOnlyOnce(3)
                .endsWith(4, 5, 5)
                .isInstanceOf(Collection.class)
                .isNotInstanceOf(Set.class);
    }

    @DisplayName("Assertions for java.time with AssertJ")
    @Test
    void testJavaTimeAssertJ() {
        ZonedDateTime dateTime = ZonedDateTime.of(2018, 11, 29, 18, 0, 0, 0, ZoneId.of("Europe/Berlin"));
        ZonedDateTime later = dateTime.plusHours(1L);
        ZonedDateTime yesterday = dateTime.minusDays(1L);
        ZonedDateTime tomorrow = dateTime.plusDays(1L);
        assertThat(dateTime)
                .isAfterOrEqualTo(yesterday)
                .isBeforeOrEqualTo("2018-12-01T00:00:00.000Z")
                .isBetween(yesterday, tomorrow)
                .isEqualToIgnoringHours(later);
    }

    @DisplayName("Grouped assertions with JUnit5")
    @Test
    void testAssertJUnit5() {
        List<Integer> list = Arrays.asList(1, 1, 2, 2, 3, 4, 5, 5);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(list),
                () -> Assertions.assertFalse(list.isEmpty()),
                () -> Assertions.assertIterableEquals(Arrays.asList(1, 1, 2, 2, 3, 4, 5, 5), list));
    }
}
