package com.szura.interview.model;

import lombok.Data;

@Data
public class PhoneNumberStatisticsResult implements StatisticResult {

    long count;

    public PhoneNumberStatisticsResult(long count) {
        this.count = count;
    }

    @Override
    public StatisticResult sum(StatisticResult statisticResult) {
        return new PhoneNumberStatisticsResult(count + statisticResult.getCount());
    }
}
