package com.szura.interview.service;

import com.szura.interview.exception.StatisticsCalculationException;
import com.szura.interview.model.*;
import com.szura.interview.service.calculations.PalindromeStatistics;
import com.szura.interview.service.calculations.NumberStatistics;
import com.szura.interview.service.calculations.SentenceStatistics;
import com.szura.interview.service.calculations.Statistics;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class StatisticsServiceTest extends StatisticsServiceBaseTest{

    private StatisticsService statisticsService;
    @Before
    public void init() {
        Map<StatisticsType, Statistics> availableStatistics = new HashMap<>();
        availableStatistics.put(StatisticsType.PALINDROME, new PalindromeStatistics());
        availableStatistics.put(StatisticsType.PHONE_NUMBER, new NumberStatistics());
        availableStatistics.put(StatisticsType.SENTENCE, new SentenceStatistics());
        statisticsService = new StatisticsService(availableStatistics);
    }

    @After
    public void tearDown() {
        statisticsService.shutDown();
    }

    @Test
    public void shouldCountStatisticsForEmptyLine_expectZeros(){
        String line = "";
        Map<StatisticsType, StatisticResult> expected = generateExpectedStatistics(0L,0L, 0L, 0L);

        Map<StatisticsType, StatisticResult> result = statisticsService.calculateStatisticsForLine(line);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCountStatisticsForLineWithNull_expectZeros(){
        String line = null;
        Map<StatisticsType, StatisticResult> expected = generateExpectedStatistics(0L,0L, 0L,0L);

        Map<StatisticsType, StatisticResult> result = statisticsService.calculateStatisticsForLine(line);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCountStatisticsForNotEmptyLine_expectZeros(){
        String line = "This is line without palindrome and phone number";
        Map<StatisticsType, StatisticResult> expected = generateExpectedStatistics(0L,0L, 0L, 1L);

        Map<StatisticsType, StatisticResult> result = statisticsService.calculateStatisticsForLine(line);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCountStatisticsForNotEmptyLineWithPhoneNumber_expectOnePhoneNumberAndSentence(){
        String line = "This is line without palindrome and with 123456789 phone number";
        Map<StatisticsType, StatisticResult> expected = generateExpectedStatistics(0L,1L,0L, 1L);

        Map<StatisticsType, StatisticResult> result = statisticsService.calculateStatisticsForLine(line);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCountStatisticsForNotEmptyMultiLineWithPhoneNumber_expectOnePhoneNumberAndSentence(){
        String line = "This is line without palindrome \n and with 123456789 phone number";
        Map<StatisticsType, StatisticResult> expected = generateExpectedStatistics(0L,1L, 0L,1L);

        Map<StatisticsType, StatisticResult> result = statisticsService.calculateStatisticsForLine(line);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCountStatisticsForNotEmptyLineWithPhoneNumber_expectOneEach(){
        String line = "This is line with level palindrome and with 123456789 phone number";
        Map<StatisticsType, StatisticResult> expected = generateExpectedStatistics(1L,1L,0L, 1L);

        Map<StatisticsType, StatisticResult> result = statisticsService.calculateStatisticsForLine(line);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCountStatisticsForNotEmptyLineWithPhoneNumberInParallel_expectNonZero(){
        String line = "This is line without palindrome and with 123456789 phone number";

        Map<StatisticsType, StatisticResult> expected = generateExpectedStatistics(0L,1L,0L, 1L);

        Map<StatisticsType, StatisticResult> result = statisticsService.calculateStatisticsForLineInParallel(line);

        Assert.assertEquals(expected, result);
    }

    @Test(expected = StatisticsCalculationException.class)
    public void shouldReadTestFile_expectException(){
        String fileName = "notExistingFile.txt";

        Map<StatisticsType, StatisticResult> expected = new HashMap<>();

        Map<StatisticsType, StatisticResult> result = statisticsService.calculateStatisticsForTextFile(fileName);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldReadTestFile_expectEmptyMap(){
        String fileName = "emptyFile.txt";

        Map<StatisticsType, StatisticResult> expected = new HashMap<>();

        Map<StatisticsType, StatisticResult> result = statisticsService.calculateStatisticsForTextFile(getAbsoluteFileName(fileName));

        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldReadTestFile_expectNotZeroStatisticsForAllExceptId(){
        String fileName = "fileWithTestSentences.txt";

        Map<StatisticsType, StatisticResult> expected = generateExpectedStatistics(100L,100L, 0L, 50L);

        Map<StatisticsType, StatisticResult> result = statisticsService.calculateStatisticsForTextFile(getAbsoluteFileName(fileName));

        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldReadTestFile_expectNotZeroStatistics(){
        String fileName = "fileWithIdDocuments.txt";

        Map<StatisticsType, StatisticResult> expected = generateExpectedStatistics(30L,30L, 15L, 15L);

        Map<StatisticsType, StatisticResult> result = statisticsService.calculateStatisticsForTextFile(getAbsoluteFileName(fileName));

        Assert.assertEquals(expected, result);
    }

    private String getAbsoluteFileName(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file.getAbsolutePath();
    }

}