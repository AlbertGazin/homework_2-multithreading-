package ru.digitalhabbits.homework2.impl;

import java.util.Map;

public class ThreadPoolImpl implements Runnable {
    LetterCounterImpl counter;
    LetterCountMergerImpl merger;

    public ThreadPoolImpl(LetterCounterImpl counter, LetterCountMergerImpl merger) {
        this.counter = counter;
        this.merger = merger;
    }

    @Override
    public void run() {
        synchronized (merger) {
            Map<Character, Long> middleMap =
                    counter.count(AsyncFileLetterCounter.strings.get(AsyncFileLetterCounter.atomicCounter.getAndIncrement()));

            AsyncFileLetterCounter.resultMap = merger.merge(AsyncFileLetterCounter.resultMap, middleMap);
        }
    }
}
