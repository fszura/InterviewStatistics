package com.szura.interview.model;

import lombok.Data;

@Data
public class PalindromeCountResult implements StatisticResult<Long> {

    private Long count;
    public PalindromeCountResult(long count){
        this.count = count;
    }

    @Override
    public StatisticResult<Long> sum(StatisticResult<Long> statisticResult) {
        return new PalindromeCountResult(count+ statisticResult.getCount());
    }
}
