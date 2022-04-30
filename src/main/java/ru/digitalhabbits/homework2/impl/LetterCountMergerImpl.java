package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.Map;

public class LetterCountMergerImpl implements LetterCountMerger {
    @Override
    public Map<Character, Long> merge(Map<Character, Long> first, Map<Character, Long> second) {

        for (Map.Entry<Character, Long> entry : second.entrySet()) {
            first.merge(entry.getKey(), entry.getValue(), Long::sum);
        }
        return first;
    }
}