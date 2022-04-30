package ru.digitalhabbits.homework2.impl;

import ru.digitalhabbits.homework2.LetterCountMerger;
import ru.digitalhabbits.homework2.LetterCounter;

import java.util.Map;

public class RunnableTaskForThreadPool implements Runnable {
    private final String stringToProcess;
    private final LetterCounter letterCounter;
    private final LetterCountMerger letterCountMerger;

    public RunnableTaskForThreadPool(String line,
                                     LetterCounter letterCounter,
                                     LetterCountMerger letterCountMerger) {
        this.stringToProcess = line;
        this.letterCounter = letterCounter;
        this.letterCountMerger = letterCountMerger;
    }

    @Override
    public void run() {
        Map<Character, Long> tempMap = letterCounter.count(stringToProcess);

        // 1. - узкое место - merger
        // 2. - приходится создавать статик переменную
        synchronized (letterCountMerger) {
            //AsyncFileLetterCounter.resultMap = letterCountMerger.merge(AsyncFileLetterCounter.resultMap, tempMap);
        }
    }
}
