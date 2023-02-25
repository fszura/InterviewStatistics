package com.szura.interview.model;

import lombok.Data;

@Data
public class NumberStatisticsResult implements StatisticResult<Long> {

    Long count;

    public NumberStatisticsResult(long count) {
        this.count = count;
    }

    @Override
    public StatisticResult<Long> sum(StatisticResult<Long> statisticResult) {
        return new NumberStatisticsResult(count + statisticResult.getCount());
    }
}
