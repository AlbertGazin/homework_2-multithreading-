package ru.digitalhabbits.homework2.impl;

import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class ForkJoinImpl extends RecursiveTask<Map<Character, Long>> {

    int from, to;

    public ForkJoinImpl(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected Map<Character, Long> compute() {
        if ((to - from) <= AsyncFileLetterCounter.strings.size()) {
            for (int i = from; i < to; i++) {
                AsyncFileLetterCounter.resultMap = AsyncFileLetterCounter.merger.merge(AsyncFileLetterCounter.resultMap,
                        AsyncFileLetterCounter.counter.count(AsyncFileLetterCounter.strings.get(i)));
            }
            return AsyncFileLetterCounter.resultMap;
        } else {
            int middle = (to - from) / 2;
            ForkJoinImpl first = new ForkJoinImpl(from, middle);
            first.fork();
            ForkJoinImpl second = new ForkJoinImpl(middle + 1, to);
            second.fork();
            Map<Character, Long> map = second.compute();
            return AsyncFileLetterCounter.merger.merge(map, first.compute());
        }
    }
}
