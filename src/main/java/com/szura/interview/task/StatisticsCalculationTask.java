package com.szura.interview.task;

import com.szura.interview.model.StatisticResult;
import com.szura.interview.service.calculations.Statistics;
import lombok.AllArgsConstructor;

import java.util.concurrent.Callable;

@AllArgsConstructor
public class StatisticsCalculationTask implements Callable<StatisticResult> {
    private Statistics statistics;
    private String line;

    @Override
    public StatisticResult call() throws Exception {
        return statistics.calculateStatistics(line);
    }
}
