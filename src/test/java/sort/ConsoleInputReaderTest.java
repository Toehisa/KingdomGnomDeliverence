package sort;

import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleInputReaderTest {

    private ConsoleInputReader createReader(String simulatedInput) {
        return new ConsoleInputReader(new Scanner(simulatedInput));
    }

    @Test
    void readLine() {
        // Эмулируем: пустой ввод -> пробелы -> нормальный текст
        ConsoleInputReader reader = createReader("\n   \nПривет, гномы\n");
        String result = reader.readLine("Введите текст: ");
        assertEquals("Привет, гномы", result);
    }

    @Test
    void readIntInRange() {
        // Эмулируем: буквы -> число ниже min (0) -> число выше max (6) -> валидное число (3)
        ConsoleInputReader reader = createReader("abc\n0\n6\n3\n");
        int result = reader.readIntInRange("Выбери от 1 до 5: ", 1, 5);
        assertEquals(3, result);
    }

    @Test
    void readPositiveInteger() {
        // Эмулируем: буквы -> отрицательное число (-1) -> ноль (который проходит по твоему условию >= 0)
        ConsoleInputReader reader = reader = createReader("text\n-1\n0\n");
        int result = reader.readPositiveInteger("Введите возраст: ");
        assertEquals(0, result);
    }

    @Test
    void readLetters() {
        // Эмулируем: цифры -> спецсимволы -> русские/английские буквы с пробелом
        ConsoleInputReader reader = createReader("12345\n@#$%\nКороль Гномов\n");
        String result = reader.readLetters("Введите имя: ");
        assertEquals("Король Гномов", result);
    }
}
