package com.szura.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SentenceCountResult implements StatisticResult{

    private long count;

    @Override
    public StatisticResult sum(StatisticResult statisticResult) {
        return new SentenceCountResult(count + statisticResult.getCount());
    }
}
