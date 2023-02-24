package com.szura.interview.service;

import com.szura.interview.model.StatisticResult;
import com.szura.interview.model.StatisticsType;
import com.szura.interview.service.calculations.PalindromeStatistics;
import com.szura.interview.service.calculations.PhoneNumberStatistics;
import com.szura.interview.service.calculations.SentenceStatistics;
import com.szura.interview.service.calculations.Statistics;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

public class StatisticsServiceParametrizedTest extends StatisticsServiceBaseTest {


    private StatisticsService statisticsService;
    @BeforeEach
    public void init(){
        Map<StatisticsType, Statistics> availableStatistics = new HashMap<>();
        availableStatistics.put(StatisticsType.PALINDROME, new PalindromeStatistics());
        availableStatistics.put(StatisticsType.PHONE_NUMBER, new PhoneNumberStatistics());
        availableStatistics.put(StatisticsType.SENTENCE, new SentenceStatistics());
        statisticsService = new StatisticsService(availableStatistics);
    }

    @CsvSource(
            value =  {
                    "1, 1, 1",
                    "2, 5, 1",
                    "50, 70, 1",
                    "0, 1, 1",
                    "0, 0, 0",
                    "1, 0, 1"
            }, nullValues = {"null"}
    )
    @ParameterizedTest
    public void shouldCountStatisticsForNotEmptySentence(long palindromes, long phoneNumbers, long sentences){
        String line = generateSentence(palindromes, phoneNumbers);
        Map<StatisticsType, StatisticResult> expected = generateExpectedStatistics(palindromes,phoneNumbers,sentences);

        Map<StatisticsType, StatisticResult> result = statisticsService.calculateStatisticsForLine(line);

        Assert.assertEquals(expected, result);
    }
}
