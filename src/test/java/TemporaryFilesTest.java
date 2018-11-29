import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.TemporaryFolder;
import org.junitpioneer.jupiter.TempDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertTrue;

public class TemporaryFilesTest {
    // JUnit 4
    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @org.junit.Test
    public void testTempJUnit4() throws IOException {
        File file = temporaryFolder.newFile("file-junit4");
        assertTrue(Files.isRegularFile(file.toPath()));

        File directory = temporaryFolder.newFolder("dir-junit4");
        assertTrue(Files.isDirectory(directory.toPath()));
    }

    @DisplayName("Temporary files and directories")
    @org.junit.jupiter.api.Test
    @ExtendWith(TempDirectory.class)
    void testTempJUnit5(@TempDirectory.TempDir Path tempDir) throws IOException {
        File file = tempDir.resolve("file-junit5").toFile();
        org.junit.jupiter.api.Assertions.assertTrue(file.createNewFile());
        org.junit.jupiter.api.Assertions.assertTrue(Files.isRegularFile(file.toPath()));

        File directory = tempDir.resolve("dir-junit5").toFile();
        org.junit.jupiter.api.Assertions.assertTrue(directory.mkdir());
        org.junit.jupiter.api.Assertions.assertTrue(Files.isDirectory(directory.toPath()));
    }
}
