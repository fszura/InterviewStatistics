package com.szura.interview.service;

import com.szura.interview.model.StatisticResult;
import com.szura.interview.model.StatisticsType;
import com.szura.interview.service.calculations.PalindromeStatistics;
import com.szura.interview.service.calculations.NumberStatistics;
import com.szura.interview.service.calculations.SentenceStatistics;
import com.szura.interview.service.calculations.Statistics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

public class StatisticsServiceParametrizedTest extends StatisticsServiceBaseTest {


    private StatisticsService statisticsService;
    @BeforeEach
    public void init(){
        Map<StatisticsType, Statistics> availableStatistics = Map.of(
        StatisticsType.PALINDROME, new PalindromeStatistics(),
        StatisticsType.PHONE_NUMBER, new NumberStatistics(),
        StatisticsType.SENTENCE, new SentenceStatistics());
        statisticsService = new StatisticsService(availableStatistics);
    }

    @CsvSource(
            value =  {
                    "1, 1, 0, 1",
                    "2, 5, 0, 1",
                    "50, 70, 0, 1",
                    "0, 1, 0, 1",
                    "0, 0, 0, 0",
                    "1, 0, 0, 1"
            }, nullValues = {"null"}
    )
    @ParameterizedTest
    public void shouldCountStatisticsForNotEmptySentence(long palindromes, long phoneNumbers, long idDocuments, long sentences){
        String line = generateSentence(palindromes, phoneNumbers);
        Map<StatisticsType, StatisticResult> expected = generateExpectedStatistics(palindromes, phoneNumbers, idDocuments, sentences);

        Map<StatisticsType, StatisticResult> result = statisticsService.calculateStatisticsForLine(line);

        Assertions.assertEquals(expected, result);
    }
}
