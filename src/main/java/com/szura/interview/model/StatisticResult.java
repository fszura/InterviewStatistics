package com.szura.interview.model;

public interface StatisticResult<T> {

    T getCount();

    StatisticResult<T> sum(StatisticResult<T> statisticResult);
}
