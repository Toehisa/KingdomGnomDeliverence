package sort;

import com.kingdom.gnome.dao.entity.Email;
import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.dao.entity.GnomeRole;
import com.kingdom.gnome.service.sorting.MergeSort;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSortTest {

    @Test
    void shouldSortGnomesByName() {

        Gnome[] gnomes = {
                gnome("Чарли", GnomeRole.BLACKSMITH, "email1@mail.ru"),
                gnome("Боб", GnomeRole.WARRIOR, "email2@mail.ru"),
                gnome("Алиса", GnomeRole.MINER, "email3@mail.ru"),
                gnome("Дмитрий", GnomeRole.WARRIOR, "email4@mail.ru"),
                gnome("Эдуард", GnomeRole.BLACKSMITH, "email5@mail.ru"),
                gnome("Фёдор", GnomeRole.MINER, "email6@mail.ru"),
                gnome("Георгий", GnomeRole.WARRIOR, "email7@mail.ru"),
                gnome("Иван", GnomeRole.BLACKSMITH, "email8@mail.ru")
        };

        Gnome[] expected = {
                gnomes[2], // Алиса
                gnomes[1], // Боб
                gnomes[6], // Георгий
                gnomes[3], // Дмитрий
                gnomes[7], // Иван
                gnomes[5], // Фёдор
                gnomes[0], // Чарли
                gnomes[4]  // Эдуард
        };

        MergeSort<Gnome> mergeSort = new MergeSort<>(
                gnomes,
                Comparator.comparing(Gnome::getName)
        );

        mergeSort.sort();

        assertArrayEquals(expected, mergeSort.getSortedItems());
    }

    @Test
    void shouldSortGnomesByNameDescending() {

        Gnome[] gnomes = {
                gnome("Чарли", GnomeRole.BLACKSMITH, "email1@mail.ru"),
                gnome("Боб", GnomeRole.WARRIOR, "email2@mail.ru"),
                gnome("Алиса", GnomeRole.MINER, "email3@mail.ru"),
                gnome("Дмитрий", GnomeRole.WARRIOR, "email4@mail.ru"),
                gnome("Эдуард", GnomeRole.BLACKSMITH, "email5@mail.ru"),
                gnome("Фёдор", GnomeRole.MINER, "email6@mail.ru"),
                gnome("Георгий", GnomeRole.WARRIOR, "email7@mail.ru"),
                gnome("Иван", GnomeRole.BLACKSMITH, "email8@mail.ru")
        };

        Gnome[] expected = {
                gnomes[4], // Эдуард
                gnomes[0], // Чарли
                gnomes[5], // Фёдор
                gnomes[7], // Иван
                gnomes[3], // Дмитрий
                gnomes[6], // Георгий
                gnomes[1], // Боб
                gnomes[2]  // Алиса
        };

        MergeSort<Gnome> mergeSort = new MergeSort<>(
                gnomes,
                Comparator.comparing(Gnome::getName).reversed()
        );

        mergeSort.sort();

        assertArrayEquals(expected, mergeSort.getSortedItems());
    }

    @Test
    void shouldSortAlreadySortedArray() {

        Gnome[] gnomes = {
                gnome("Алиса", GnomeRole.MINER, "email1@mail.ru"),
                gnome("Боб", GnomeRole.WARRIOR, "email2@mail.ru"),
                gnome("Виктор", GnomeRole.BLACKSMITH, "email3@mail.ru"),
                gnome("Георгий", GnomeRole.WARRIOR, "email4@mail.ru"),
                gnome("Дмитрий", GnomeRole.BLACKSMITH, "email5@mail.ru")
        };

        Gnome[] expected = gnomes.clone();

        MergeSort<Gnome> mergeSort = new MergeSort<>(
                gnomes,
                Comparator.comparing(Gnome::getName)
        );

        mergeSort.sort();

        assertArrayEquals(expected, mergeSort.getSortedItems());
    }

    @Test
    void shouldSortReversedArray() {

        Gnome[] gnomes = {
                gnome("Иван", GnomeRole.BLACKSMITH, "email1@mail.ru"),
                gnome("Георгий", GnomeRole.WARRIOR, "email2@mail.ru"),
                gnome("Фёдор", GnomeRole.MINER, "email3@mail.ru"),
                gnome("Эдуард", GnomeRole.BLACKSMITH, "email4@mail.ru"),
                gnome("Дмитрий", GnomeRole.WARRIOR, "email5@mail.ru"),
                gnome("Чарли", GnomeRole.BLACKSMITH, "email6@mail.ru"),
                gnome("Боб", GnomeRole.WARRIOR, "email7@mail.ru"),
                gnome("Алиса", GnomeRole.MINER, "email8@mail.ru")
        };

        Gnome[] expected = {
                gnomes[7], // Алиса
                gnomes[6], // Боб
                gnomes[1], // Георгий
                gnomes[4], // Дмитрий
                gnomes[0], // Иван
                gnomes[2], // Фёдор
                gnomes[5], // Чарли
                gnomes[3]  // Эдуард
        };

        MergeSort<Gnome> mergeSort = new MergeSort<>(
                gnomes,
                Comparator.comparing(Gnome::getName)
        );

        mergeSort.sort();

        assertArrayEquals(expected, mergeSort.getSortedItems());
    }

    @Test
    void shouldSortArrayWithEqualNames() {

        Gnome[] gnomes = {
                gnome("Боб", GnomeRole.BLACKSMITH, "email1@mail.ru"),
                gnome("Алиса", GnomeRole.MINER, "email2@mail.ru"),
                gnome("Боб", GnomeRole.WARRIOR, "email3@mail.ru"),
                gnome("Чарли", GnomeRole.BLACKSMITH, "email4@mail.ru"),
                gnome("Алиса", GnomeRole.WARRIOR, "email5@mail.ru")
        };

        Gnome[] expected = {
                gnomes[1], // Алиса
                gnomes[4], // Алиса
                gnomes[0], // Боб
                gnomes[2], // Боб
                gnomes[3]  // Чарли
        };

        MergeSort<Gnome> mergeSort = new MergeSort<>(
                gnomes,
                Comparator.comparing(Gnome::getName)
        );

        mergeSort.sort();

        assertArrayEquals(expected, mergeSort.getSortedItems());
    }

    @Test
    void shouldSortOneGnome() {

        Gnome[] gnomes = {
                gnome("Борис", GnomeRole.WARRIOR, "email@mail.ru")
        };

        Gnome[] expected = {
                gnomes[0]
        };

        MergeSort<Gnome> mergeSort = new MergeSort<>(
                gnomes,
                Comparator.comparing(Gnome::getName)
        );

        mergeSort.sort();

        assertArrayEquals(expected, mergeSort.getSortedItems());
    }

    @Test
    void shouldSortEmptyArray() {

        Gnome[] gnomes = {};
        Gnome[] expected = {};

        MergeSort<Gnome> mergeSort = new MergeSort<>(
                gnomes,
                Comparator.comparing(Gnome::getName)
        );

        mergeSort.sort();

        assertArrayEquals(expected, mergeSort.getSortedItems());
    }

    @Test
    void shouldSortGnomesByRole() {

        Gnome[] gnomes = {
                gnome("Чарли", GnomeRole.BLACKSMITH, "charlie@mail.ru"),
                gnome("Боб", GnomeRole.WARRIOR, "bob@mail.ru"),
                gnome("Алиса", GnomeRole.MINER, "alice@mail.ru"),
                gnome("Дмитрий", GnomeRole.KING, "dmitry@mail.ru"),
                gnome("Эдуард", GnomeRole.BUILDER, "eduard@mail.ru"),
                gnome("Фёдор", GnomeRole.JEWELER, "fedor@mail.ru")
        };

        Gnome[] expected = {
                gnomes[2], // MINER
                gnomes[0], // BLACKSMITH
                gnomes[1], // WARRIOR
                gnomes[4], // BUILDER
                gnomes[5], // JEWELER
                gnomes[3]  // KING
        };

        MergeSort<Gnome> mergeSort = new MergeSort<>(
                gnomes,
                Comparator.comparing(Gnome::getRole)
        );

        mergeSort.sort();

        assertArrayEquals(expected, mergeSort.getSortedItems());
    }

    @Test
    void shouldSortGnomesByEmail() {

        Gnome[] gnomes = {
                gnome("Чарли", GnomeRole.BLACKSMITH, "charlie@mail.ru"),
                gnome("Боб", GnomeRole.WARRIOR, "bob@mail.ru"),
                gnome("Алиса", GnomeRole.MINER, "alice@mail.ru"),
                gnome("Дмитрий", GnomeRole.KING, "dmitry@mail.ru"),
                gnome("Эдуард", GnomeRole.BUILDER, "eduard@mail.ru"),
                gnome("Фёдор", GnomeRole.JEWELER, "fedor@mail.ru")
        };

        Gnome[] expected = {
                gnomes[2], // alice@mail.ru
                gnomes[1], // bob@mail.ru
                gnomes[0], // charlie@mail.ru
                gnomes[3], // dmitry@mail.ru
                gnomes[4], // eduard@mail.ru
                gnomes[5]  // fedor@mail.ru
        };

        MergeSort<Gnome> mergeSort = new MergeSort<>(
                gnomes,
                Comparator.comparing(gnome -> gnome.getEmail().value())
        );

        mergeSort.sort();

        assertArrayEquals(expected, mergeSort.getSortedItems());
    }

    private static Gnome gnome(
            String name,
            GnomeRole role,
            String email
    ) {
        return Gnome.builder()
                .name(name)
                .role(role)
                .email(new Email(email))
                .build();
    }

}