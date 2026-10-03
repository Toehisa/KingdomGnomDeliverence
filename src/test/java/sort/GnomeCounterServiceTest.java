package sort;

import com.kingdom.gnome.dao.entity.Email;
import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.dao.entity.GnomeRole;
import com.kingdom.gnome.service.counting.GnomeCounterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GnomeCounterServiceTest {

    private GnomeCounterService counterService;
    private List<Gnome> testGnomes;
    private ExecutorService executorService;

    @BeforeEach
    void setUp() {
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        counterService = new GnomeCounterService(new ForkJoinPool(Runtime.getRuntime().availableProcessors()));
        testGnomes = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    @Test
    void shouldReturnZeroWhenCollectionIsEmpty() {
        int count = counterService.getOccurrencesCount("NAME", "Гимли", testGnomes);
        assertEquals(0, count, "Если коллекция пуста, результат должен быть 0");
    }

    @Test
    void shouldCountByNameCorrectly() {
        testGnomes.add(Gnome.builder().name("Гимли").role(GnomeRole.WARRIOR).email(new Email("gimli@mail.ru")).build());
        testGnomes.add(Gnome.builder().name("Торин").role(GnomeRole.KING).email(new Email("thorin@mail.ru")).build());
        testGnomes.add(Gnome.builder().name("Гимли").role(GnomeRole.MINER).email(new Email("gimli2@mail.ru")).build());

        int count = counterService.getOccurrencesCount("NAME", "Гимли", testGnomes);
        assertEquals(2, count);
    }

    @Test
    void shouldCountByRoleCorrectly() {
        testGnomes.add(Gnome.builder().name("Балин").role(GnomeRole.MINER).email(new Email("balin@mail.ru")).build());
        testGnomes.add(Gnome.builder().name("Глоин").role(GnomeRole.MINER).email(new Email("gloin@mail.ru")).build());
        testGnomes.add(Gnome.builder().name("Двалин").role(GnomeRole.WARRIOR).email(new Email("dvalin@mail.ru")).build());

        int count = counterService.getOccurrencesCount("ROLE", "Шахтёр", testGnomes);
        assertEquals(2, count);
    }

    @Test
    void shouldReturnZeroWhenNoMatchesFound() {
        testGnomes.add(Gnome.builder().name("Гимли").role(GnomeRole.WARRIOR).email(new Email("gimli@mail.ru")).build());

        int count = counterService.getOccurrencesCount("NAME", "Леголас", testGnomes);
        assertEquals(0, count);
    }

    @Test
    void stressTestWithRemainderDistribution() {
        String targetName = "ЦелевойГном";
        int expectedCount = 500;

        for (int i = 0; i < 80003; i++) {
            if (i < expectedCount) {
                testGnomes.add(Gnome.builder().name(targetName).role(GnomeRole.BUILDER).email(new Email("test" + i + "@mail.ru")).build());
            } else {
                testGnomes.add(Gnome.builder().name("ОбычныйГном").role(GnomeRole.BUILDER).email(new Email("test" + i + "@mail.ru")).build());
            }
        }

        int actualCount = counterService.getOccurrencesCount("NAME", targetName, testGnomes);
        assertEquals(expectedCount, actualCount, "Математика границ потеряла элементы при делении с остатком!");
    }
}
