package com.szura.interview.service.calculations;

import com.szura.interview.model.StatisticResult;

import java.util.Arrays;
import java.util.stream.Stream;

public interface Statistics <S extends StatisticResult, R> {

    default S calculateStatistics(String line){
        if (line == null) return createResult(emptyStatistics());
        return createResult(Arrays.stream(line.split("\\W+"))
                .map(this::matchRequirements).reduce(emptyStatistics(), this::sumStatistics));
    }

    R sumStatistics(R s, R s1);

    R emptyStatistics();

    R matchRequirements(String word);

    S createResult(R count);
}
