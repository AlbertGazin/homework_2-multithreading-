package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCounter;

import java.util.HashMap;
import java.util.Map;

// получает строку и подсчитывает количество
public class LetterCounterImpl implements LetterCounter {
    @Override
    public Map<Character, Long> count(String input) {
        if (input.length() == 0) {
            return new HashMap<>();
        }

        char[] chars = input.toCharArray();
        Map<Character, Long> resultMap = new HashMap<>();

        for (int i = 0; i < chars.length; i++) {
            long count = resultMap.getOrDefault(chars[i], 0L);

            if (count > 0) {
                count++;
                resultMap.put(chars[i], count);
            } else {
                resultMap.put(chars[i], 1L);
            }
        }
        return resultMap;
    }
}
