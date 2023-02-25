package com.szura.interview.service.calculations;

import com.szura.interview.model.NumberResultType;
import com.szura.interview.model.NumberStatisticsResult;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NumberStatistics implements Statistics<NumberStatisticsResult, Map<NumberResultType, Long>> {

    private final Map<NumberResultType, Pattern> patterns;

    public NumberStatistics() {
        patterns = Arrays.stream(NumberResultType.values()).collect(Collectors
                .toMap(Function.identity(), e-> Pattern.compile(e.getRegex())));
    }

    @Override
    public Map<NumberResultType, Long> sumStatistics(Map<NumberResultType, Long> s, Map<NumberResultType, Long> s1) {
        return s.keySet().stream().collect(Collectors.toMap(Function.identity(), k-> s.get(k) + s1.get(k)));
    }

    @Override
    public Map<NumberResultType, Long> emptyStatistics() {
        return Arrays.stream(NumberResultType.values()).collect(Collectors.toMap(Function.identity(),  e->0L));
    }

    @Override
    public Map<NumberResultType, Long> matchRequirements(String word) {
        return Optional.ofNullable(word).map(w->
            Arrays.stream(NumberResultType.values()).collect(Collectors.toMap(Function.identity(),
                    t->patterns.get(t).matcher(w).matches() ? 1L : 0L))
        ).orElse(emptyStatistics());
    }

    @Override
    public NumberStatisticsResult createResult(Map<NumberResultType, Long> count) {
        return new NumberStatisticsResult(count);
    }
}
