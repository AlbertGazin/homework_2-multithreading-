package ru.digitalhabbits.homework2;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.impl.LetterCounterImpl;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class LetterCounterTest {
    @Test
    void letter_counter_return_expected_result() {
        LetterCounter counter = new LetterCounterImpl();
        String text = "Happy new year!";

        Map<Character, Long> count = counter.count(text);

        assertThat(count).containsOnly(
                entry('H', 1L),
                entry('a', 2L),
                entry('p', 2L),
                entry('y', 2L),
                entry(' ', 2L),
                entry('n', 1L),
                entry('e', 2L),
                entry('w', 1L),
                entry('r', 1L),
                entry('!', 1L)
        );
    }
}
