package com.szura.interview.service;

import com.szura.interview.exception.StatisticsCalculationException;
import com.szura.interview.model.StatisticResult;
import com.szura.interview.model.StatisticsType;
import com.szura.interview.service.calculations.Statistics;
import com.szura.interview.task.StatisticsCalculationTask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatisticsService {

    private final Map<StatisticsType, Statistics> availableStatistics;
    private final ExecutorService executorService;

    public StatisticsService(Map<StatisticsType, Statistics> availableStatistics) {
        this.availableStatistics = availableStatistics;
        this.executorService =  Executors.newFixedThreadPool(availableStatistics.size());
    }

    public Map<StatisticsType, StatisticResult> calculateStatisticsForLine(String line) {
        return availableStatistics.keySet().stream().collect(Collectors.toMap(Function.identity(),
                k-> availableStatistics.get(k).calculateStatistics(line)));
    }

    public Map<StatisticsType, StatisticResult> calculateStatisticsForLineInParallel(String line) {
        return availableStatistics.keySet().stream().collect(Collectors.toMap(Function.identity(),
                k-> {
                    StatisticsCalculationTask task = new StatisticsCalculationTask(availableStatistics.get(k), line);
                    try {
                        return executorService.submit(task).get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }));
    }

    public Map<StatisticsType, StatisticResult> calculateStatisticsForTextFile(String fileName) {
        try(Stream<String> textStream = Files.lines(Paths.get(fileName))) {
            Map<StatisticsType, List<StatisticResult>> results = textStream.map(this::calculateStatisticsForLineInParallel)
                    .flatMap(e -> e.entrySet().stream())
                    .collect(Collectors.groupingBy(Map.Entry::getKey,
                            Collectors.mapping(Map.Entry::getValue,
                                    Collectors.toList())));
            return results.keySet().stream().collect(Collectors.toMap(Function.identity(), e ->
                    results.get(e).stream().reduce(StatisticResult::sum).get()));
        } catch (IOException e) {
            throw new StatisticsCalculationException(e);
        }
    }

    public void shutDown(){
        executorService.shutdown();
    }



}
