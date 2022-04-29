package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCountMerger;

import java.util.HashMap;
import java.util.Map;

// по итогу смержить со всех потоков информацию
public class LetterCountMergerImpl implements LetterCountMerger {
    @Override
    public Map<Character, Long> merge(Map<Character, Long> first, Map<Character, Long> second) {
        Map<Character, Long> resultMap = new HashMap<>(first);

        for (Map.Entry<Character, Long> entry : second.entrySet()) {
            Character key = entry.getKey();

            if (resultMap.containsKey(key)) {
                Long charsCount = resultMap.get(key);
                resultMap.put(entry.getKey(), charsCount + entry.getValue());
            } else {
                resultMap.put(entry.getKey(), entry.getValue());
            }
        }
        return resultMap;
    }
}
