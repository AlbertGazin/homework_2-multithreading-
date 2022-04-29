package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.LetterCountMerger;
import ru.digitalhabbits.homework2.LetterCounter;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncFileLetterCounter implements FileLetterCounter {
    public static Map<Character, Long> resultMap = new ConcurrentHashMap<>();
    private final LetterCountMerger merger = new LetterCountMergerImpl();
    private final LetterCounter counter = new LetterCounterImpl();
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    @Override
    public Map<Character, Long> count(File input) {
        FileReader fileReader = new FileReaderImpl();

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        fileReader.readLines(input).forEach(line -> executorService.execute(new TaskForThreadPool(line, counter, merger)));
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdown();
            }
            return resultMap;
        } catch (InterruptedException e) {
            executorService.shutdown();
        }

        return resultMap;
    }
}
