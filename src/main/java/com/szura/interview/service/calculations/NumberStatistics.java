package com.szura.interview.service.calculations;

import com.szura.interview.model.NumberStatisticsResult;

import java.util.Optional;
import java.util.regex.Pattern;

public class NumberStatistics implements Statistics<NumberStatisticsResult> {

    private static final String REGEX = "^\\d{9}$";
    private final Pattern pattern = Pattern.compile(REGEX);

    @Override
    public boolean matchRequirements(String word) {
        return Optional.ofNullable(word).map(w-> pattern.matcher(w).matches()).orElse(false);
    }

    @Override
    public NumberStatisticsResult createResult(long count) {
        return new NumberStatisticsResult(count);
    }
}
