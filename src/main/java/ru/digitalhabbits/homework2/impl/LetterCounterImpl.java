package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCounter;

import java.util.HashMap;
import java.util.Map;

public class LetterCounterImpl implements LetterCounter {
    @Override
    public Map<Character, Long> count(String input) {
        if (input.length() == 0) {
            return new HashMap<>();
        }

        char[] chars = input.toCharArray();
        Map<Character, Long> resultMap = new HashMap<>();

        for (int i = 0; i < chars.length; i++) {
            resultMap.merge(chars[i], 1L, Long::sum);
        }
        return resultMap;
    }
}
