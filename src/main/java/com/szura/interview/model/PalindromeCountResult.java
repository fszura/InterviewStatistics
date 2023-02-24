package com.szura.interview.model;

import lombok.Data;

@Data
public class PalindromeCountResult implements StatisticResult {

    private long count;
    public PalindromeCountResult(long count){
        this.count = count;
    }

    @Override
    public StatisticResult sum(StatisticResult statisticResult) {
        return new PalindromeCountResult(count+ statisticResult.getCount());
    }
}
