package com.szura.interview.service.calculations;

import com.szura.interview.model.NumberResultType;
import com.szura.interview.model.NumberStatisticsResult;
import com.szura.interview.model.StatisticResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class NumberStatisticsTest {

    NumberStatistics numberStatistics;

    @Before
    public void init() {
        this.numberStatistics = new NumberStatistics();
    }

    @Test
    public void shouldCheckWordWithNullAndReturnsFalse(){
        String word = null;
        Map<NumberResultType, Long> expected = createExpectedNumberCheckResult(0, 0, 0);
        Map<NumberResultType, Long> result = numberStatistics.matchRequirements(word);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckEmptyWordAndReturnsFalse(){
        String word = "";
        Map<NumberResultType, Long> expected = createExpectedNumberCheckResult(0, 0, 0);
        Map<NumberResultType, Long> result = numberStatistics.matchRequirements(word);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckWordWithWhiteSpaceAndReturnsFalse(){
        String word = "  ";
        Map<NumberResultType, Long> expected = createExpectedNumberCheckResult(0, 0, 0);
        Map<NumberResultType, Long> result = numberStatistics.matchRequirements(word);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckWordWithoutPhoneNumberAndReturnsFalse(){
        String word = "word";
        Map<NumberResultType, Long> expected = createExpectedNumberCheckResult(0, 0,0);
        Map<NumberResultType, Long> result = numberStatistics.matchRequirements(word);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckWordWithPhoneNumberAndReturnsTrue(){
        String word = "123456789";
        Map<NumberResultType, Long> expected = createExpectedNumberCheckResult(1, 0, 0);
        Map<NumberResultType, Long> result = numberStatistics.matchRequirements(word);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckNullLineAndReturnsZero(){
        String line = null;
        NumberStatisticsResult expected = new NumberStatisticsResult(createExpectedNumberCheckResult(0, 0, 0));
        StatisticResult result = numberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckEmptyLineAndReturnsZero(){
        String line = "";
        NumberStatisticsResult expected = new NumberStatisticsResult(createExpectedNumberCheckResult(0, 0, 0));
        StatisticResult result = numberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckLineWithoutPhoneNumberAndReturnsZero(){
        String line = "Sample line without phone number";
        NumberStatisticsResult expected = new NumberStatisticsResult(createExpectedNumberCheckResult(0, 0, 0));
        StatisticResult result = numberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckLineWithPhoneNumberAndReturnsOne(){
        String line = "Sample line with 123456789 phone number";
        NumberStatisticsResult expected = new NumberStatisticsResult(createExpectedNumberCheckResult(1, 0, 0));
        StatisticResult result = numberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckLineWithTwoPhoneNumberAndReturnsTwo(){
        String line = "Sample line with 123456789 123456789 phone number";
        NumberStatisticsResult expected = new NumberStatisticsResult(createExpectedNumberCheckResult(2, 0, 0));
        StatisticResult result = numberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckLineWithNumberAndReturnsZero(){
        String line = "Sample line with 12345678 number";
        NumberStatisticsResult expected = new NumberStatisticsResult(createExpectedNumberCheckResult(0, 0, 0));
        StatisticResult result = numberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckLineWithPassportAndReturnStatisticsWithOnlyOnePassport(){
        String line = "Sample line with M55210284 passport number";
        NumberStatisticsResult expected = new NumberStatisticsResult(createExpectedNumberCheckResult(0, 0, 1));
        StatisticResult result = numberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckLineWithIdAndReturnsCountOfOneId(){
        String line = "Sample line with AV23456 GB456789 AA897654 phone number";
        NumberStatisticsResult expected = new NumberStatisticsResult(createExpectedNumberCheckResult(0, 1, 0));
        StatisticResult result = numberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    private Map<NumberResultType, Long> createExpectedNumberCheckResult(long phoneNumbers, long id, long passport){
        return Map.of(
                NumberResultType.PHONE_NUMBER, phoneNumbers,
                NumberResultType.ID, id,
                NumberResultType.PASSPORT, passport
                );
    }

}
