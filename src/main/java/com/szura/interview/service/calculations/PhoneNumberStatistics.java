package com.szura.interview.service.calculations;

import com.szura.interview.model.PhoneNumberStatisticsResult;

import java.util.Optional;
import java.util.regex.Pattern;

public class PhoneNumberStatistics implements Statistics<PhoneNumberStatisticsResult> {

    private static final String REGEX = "^\\d{9}$";
    private final Pattern pattern = Pattern.compile(REGEX);

    @Override
    public boolean matchRequirements(String word) {
        return Optional.ofNullable(word).map(w-> pattern.matcher(w).matches()).orElse(false);
    }

    @Override
    public PhoneNumberStatisticsResult createResult(long count) {
        return new PhoneNumberStatisticsResult(count);
    }
}
