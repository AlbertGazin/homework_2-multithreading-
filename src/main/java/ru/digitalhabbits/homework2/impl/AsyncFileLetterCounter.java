package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.FileLetterCounter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AsyncFileLetterCounter implements FileLetterCounter {

    public static volatile AtomicInteger atomicCounter = new AtomicInteger(0);
    static Map<Character, Long> resultMap = new HashMap<>();
    static List<String> strings = new ArrayList<>();
    static LetterCountMergerImpl merger = new LetterCountMergerImpl();
    static LetterCounterImpl counter = new LetterCounterImpl();
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    @Override
    public Map<Character, Long> count(File input) {
        FileReaderImpl fileReader = new FileReaderImpl();
        Stream<String> streamLines = fileReader.readLines(input);

        strings = streamLines.collect(Collectors.toList());

//        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
//        for (int i = 0; i < strings.size(); i++) {
//            executorService.execute(new ThreadPoolImpl(counter, merger));
//        }
//        executorService.shutdown();
//
//        try {
//            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
//                executorService.shutdown();
//            }
//            return resultMap;
//        } catch (InterruptedException e) {
//            executorService.shutdown();
//        }

        ForkJoinPool pool = new ForkJoinPool(THREAD_COUNT);
        return pool.invoke(new ForkJoinImpl(0, strings.size()));
//        return resultMap;
    }
}
