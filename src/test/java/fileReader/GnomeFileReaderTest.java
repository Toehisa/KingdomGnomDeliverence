package fileReader;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.dao.perform.GnomeFileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GnomeFileReaderTest {

    private static final String VALID = "Борис Ульянов;Шахтёр;boris@aston.com";
    private static final int NO_LIMIT = 1000;

    @TempDir
    Path tempDir;

    private GnomeFileReader reader;

    @BeforeEach
    void setUp() {
        reader = new GnomeFileReader();
    }

    private List<Gnome> read(int count, String... lines) throws IOException {
        File file = tempDir.resolve("gnomes.txt").toFile();
        Files.writeString(file.toPath(), String.join("\n", lines), StandardCharsets.UTF_8);
        return reader.readGnomesFromFile(file, count);
    }

    private List<Gnome> read(String... lines) throws IOException {
        return read(NO_LIMIT, lines);
    }

    @Test
    void shouldReadValidLine() throws IOException {
        assertEquals(1, read(VALID).size());
    }

    @Test
    void shouldReturnEmptyListWhenFileIsEmpty() throws IOException {
        assertTrue(read("").isEmpty());
    }

    @Test
    void shouldSkipCommentsAndBlankLines() throws IOException {
        assertEquals(1, read("# комментарий", "", "   ", VALID).size());
    }

    @Test
    void shouldSkipLineWithWrongFieldCount() throws IOException {
        List<Gnome> gnomes = read(
                "Иван Иванов",
                "Мария Петрова;Шахтёр",
                "Алексей Сидоров;Шахтёр;alex@dreamteam.com;лишнее",
                "Екатерина Волкова;Шахтёр;",
                ";;;");
        assertTrue(gnomes.isEmpty());
    }

    @Test
    void shouldSkipLineWithWrongSeparator() throws IOException {
        List<Gnome> gnomes = read(
                "Артем Морозов:Шахтёр:artem@dreamteam.com",
                "Светлана Захарова\tШахтёр\tsvetlana@company.com",
                "Константин Новиков,Шахтёр,konstantin@dreamteam.com");
        assertTrue(gnomes.isEmpty());
    }

    @Test
    void shouldSkipLineWithUnknownRole() throws IOException {
        assertTrue(read("Анна Смирнова;Космонавт;anna@dreamteam.com").isEmpty());
    }

    @Test
    void shouldSkipLineWithInvalidEmail() throws IOException {
        List<Gnome> gnomes = read(
                "Татьяна Григорьева;Шахтёр;tatyanacompany.com",
                "Михаил Орлов;Шахтёр;mikhail@@dreamteam.com",
                "Надежда Соколова;Шахтёр;nadezhda@");
        assertTrue(gnomes.isEmpty());
    }

    @Test
    void shouldSkipLineWithEmptyName() throws IOException {
        assertTrue(read(";Шахтёр;empty@company.com").isEmpty());
    }

    @Test
    void shouldContinueReadingAfterBadLine() throws IOException {
        List<Gnome> gnomes = read(VALID, "Иван Иванов", "Анна;Космонавт;a@b.com", VALID);
        assertEquals(2, gnomes.size());
    }

    @Test
    void shouldStopReadingAfterCountReached() throws IOException {
        assertEquals(2, read(2, VALID, VALID, VALID).size());
    }

    @Test
    void shouldReadWholeFileWhenCountIsLargerThanLines() throws IOException {
        assertEquals(3, read(100, VALID, VALID, VALID).size());
    }

    @Test
    void shouldSilentlySkipDataLineContainingHash() throws IOException {
        // Проигнорирует строку из-за "#"
        assertTrue(read("Виктор Орлов;Шахтёр;viktor#test@company.com").isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenFileDoesNotExist() {
        File missing = tempDir.resolve("no_such_file.txt").toFile();
        List<Gnome> gnomes = reader.readGnomesFromFile(missing, NO_LIMIT);
        assertNotNull(gnomes);
        assertTrue(gnomes.isEmpty());
    }

    @Test
    void findFileShouldReturnFileByAbsolutePath() throws IOException {
        File file = Files.writeString(tempDir.resolve("found.txt"), VALID).toFile();
        assertEquals(file.getAbsolutePath(), reader.findFile(file.getAbsolutePath()).getAbsolutePath());
    }

    @Test
    void findFileShouldReturnNullWhenFileMissing() {
        assertNull(reader.findFile("definitely_missing_" + System.nanoTime() + ".txt"));
    }
}
