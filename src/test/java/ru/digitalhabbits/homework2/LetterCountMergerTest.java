package ru.digitalhabbits.homework2;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.impl.LetterCountMergerImpl;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class LetterCountMergerTest {
    Map<Character, Long> getFirstMap() {
        Map<Character, Long> map = new HashMap<>();

        map.put('A', 2L);
        map.put('B', 3L);
        map.put('C', 1L);
        map.put('D', 4L);
        map.put('E', 2L);

        return map;
    }

    Map<Character, Long> getSecondMap() {
        Map<Character, Long> map = new HashMap<>();

        map.put('A', 5L);
        map.put('F', 2L);
        map.put('E', 1L);
        map.put('R', 7L);
        map.put('D', 5L);
        map.put('Z', 9L);

        return map;
    }

    @Test
    void letter_merger_return_expected_result() {
        LetterCountMerger merger = new LetterCountMergerImpl();
        Map<Character, Long> map1 = getFirstMap();
        Map<Character, Long> map2 = getSecondMap();

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
