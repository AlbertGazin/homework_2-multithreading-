package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.FileLetterCounter;
import ru.digitalhabbits.homework2.FileReader;
import ru.digitalhabbits.homework2.LetterCountMerger;
import ru.digitalhabbits.homework2.LetterCounter;

import java.io.File;
import java.util.Map;
import java.util.concurrent.*;

public class AsyncFileLetterCounter implements FileLetterCounter {
    private Map<Character, Long> resultMap;
    private final LetterCountMerger merger;
    private final LetterCounter counter;
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
    private static final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    public AsyncFileLetterCounter() {
        this.resultMap = new ConcurrentHashMap<>();
        this.merger = new LetterCountMergerImpl();
        this.counter = new LetterCounterImpl();
    }

    public AsyncFileLetterCounter(Map<Character, Long> resultMap, LetterCountMerger merger, LetterCounter counter) {
        this.resultMap = resultMap;
        this.merger = merger;
        this.counter = counter;
    }

    @Override
    public Map<Character, Long> count(File input) {
        FileReader fileReader = new FileReaderImpl();

        fileReader.readLines(input).map(line -> executorService.submit(new CallableTaskForThreadPool(line, counter)))
                .forEach(mapFuture -> executorService.submit(() -> merger.merge(resultMap, mapFuture.get())));
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
