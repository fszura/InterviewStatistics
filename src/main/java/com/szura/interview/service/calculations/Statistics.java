package com.szura.interview.service.calculations;

import com.szura.interview.model.StatisticResult;

import java.util.Arrays;

public interface Statistics <S extends StatisticResult> {

    default S calculateStatistics(String line){
        if (line == null) return createResult(0);
        long palindromesCount = Arrays.stream(line.split("\\W+")).filter(this::matchRequirements).count();
        return createResult(palindromesCount);
    }

    boolean matchRequirements(String word);

    S createResult(long count);
}
