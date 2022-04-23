package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.FileLetterCounter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// для тестов видимо нужен
public class FileLetterCounterImpl implements FileLetterCounter {
    @Override
    public Map<Character, Long> count(File input) {
        Map<Character, Long> resultMap = new HashMap<>();
        FileReaderImpl reader = new FileReaderImpl();
        LetterCounterImpl counter = new LetterCounterImpl();
        LetterCountMergerImpl merger = new LetterCountMergerImpl();

        List<String> stringList = reader.readLines(input).collect(Collectors.toList());

        for (int i = 0; i < stringList.size(); i++) {
            resultMap = merger.merge(resultMap, counter.count(stringList.get(i)));
        }
        return resultMap;
    }
}
