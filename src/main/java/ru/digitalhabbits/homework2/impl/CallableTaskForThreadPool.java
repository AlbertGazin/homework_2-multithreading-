package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCounter;

import java.util.Map;
import java.util.concurrent.Callable;

public class CallableTaskForThreadPool implements Callable<Map<Character, Long>> {
    private final String lineForCounting;
    private final LetterCounter letterCounter;

    public CallableTaskForThreadPool(String lineForCounting, LetterCounter letterCounter) {
        this.lineForCounting = lineForCounting;
        this.letterCounter = letterCounter;
    }

    @Override
    public Map<Character, Long> call() {
        return letterCounter.count(lineForCounting);
    }
}
