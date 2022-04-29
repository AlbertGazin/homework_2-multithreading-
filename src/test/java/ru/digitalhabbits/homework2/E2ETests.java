package ru.digitalhabbits.homework2;

import org.junit.jupiter.api.Test;
import ru.digitalhabbits.homework2.impl.AsyncFileLetterCounter;
import ru.digitalhabbits.homework2.impl.LetterCountMergerImpl;
import ru.digitalhabbits.homework2.impl.LetterCounterImpl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.io.Resources.getResource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class E2ETests {

    @Test
    void async_file_letter_counting_should_return_predicted_count() {
        var file = getFile("test.txt");
        var counter = new AsyncFileLetterCounter();

        Map<Character, Long> count = counter.count(file);

        assertThat(count).containsOnly(
                entry('a', 2697L),
                entry('b', 2683L),
                entry('c', 2647L),
                entry('d', 2613L),
                entry('e', 2731L),
                entry('f', 2629L)
        );
    }

    @Test
    void letter_counter_return_expected_value() {
        LetterCounter counter = new LetterCounterImpl();
        Map<Character, Long> expected = new HashMap<>();
        String text = "Happy new year!";
        expected.put('H', 1L);
        expected.put('a', 2L);
        expected.put('p', 2L);
        expected.put('y', 2L);
        expected.put(' ', 2L);
        expected.put('n', 1L);
        expected.put('e', 2L);
        expected.put('w', 1L);
        expected.put('r', 1L);
        expected.put('!', 1L);

        assertThat(counter.count(text)).isEqualTo(expected);
    }

    @Test
    void merger_works_correctly() {
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

        Map<Character, Long> expected = new HashMap<>();
        expected.put('A', 7L);
        expected.put('B', 3L);
        expected.put('C', 1L);
        expected.put('D', 9L);
        expected.put('E', 3L);
        expected.put('F', 2L);
        expected.put('R', 7L);
        expected.put('Z', 9L);

        assertThat(merger.merge(map1, map2)).isEqualTo(expected);
    }

    private File getFile(String name) {
        return new File(getResource(name).getPath());
    }
}
