package com.szura.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SentenceCountResult implements StatisticResult<Long>{

    private Long count;

    @Override
    public StatisticResult<Long> sum(StatisticResult<Long> statisticResult) {
        return new SentenceCountResult(count + statisticResult.getCount());
    }
}
