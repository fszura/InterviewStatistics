package com.szura.interview.model;

import lombok.Data;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class NumberStatisticsResult implements StatisticResult<Map<NumberResultType, Long>> {

    Map<NumberResultType, Long> count;

    public NumberStatisticsResult(Map<NumberResultType, Long> count) {
        this.count = count;
    }

    @Override
    public StatisticResult<Map<NumberResultType, Long>> sum(StatisticResult<Map<NumberResultType, Long>> statisticResult) {
        return new NumberStatisticsResult(statisticResult.getCount().keySet().stream().collect(Collectors.toMap(Function.identity(), e->count.get(e) + statisticResult.getCount().get(e))));
    }
}
