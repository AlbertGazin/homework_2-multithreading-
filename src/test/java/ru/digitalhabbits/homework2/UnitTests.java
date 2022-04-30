package ru.digitalhabbits.homework2;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.impl.LetterCountMergerImpl;
import ru.digitalhabbits.homework2.impl.LetterCounterImpl;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class UnitTests {
    @Test
    void letter_counter_return_expected_value() {
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

    @Test
    void merge_two_maps() {
        LetterCountMerger merger = new LetterCountMergerImpl();
        Map<Character, Long> map1 = new HashMap<>();
        Map<Character, Long> map2 = new HashMap<>();
        map1.put('A', 2L);
        map1.put('B', 3L);
        map1.put('C', 1L);
        map1.put('D', 4L);
        map1.put('E', 2L);

        map2.put('A', 5L);
        map2.put('F', 2L);
        map2.put('E', 1L);
        map2.put('R', 7L);
        map2.put('D', 5L);
        map2.put('Z', 9L);

        Map<Character, Long> mergeResult = merger.merge(map1, map2);

        assertThat(mergeResult).containsOnly(
                entry('A', 7L),
                entry('B', 3L),
                entry('C', 1L),
                entry('D', 9L),
                entry('E', 3L),
                entry('F', 2L),
                entry('R', 7L),
                entry('Z', 9L));
    }
}
