package com.szura.interview.model;

public interface StatisticResult {

    long getCount();

    StatisticResult sum(StatisticResult statisticResult);
}
