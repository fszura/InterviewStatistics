package com.szura.interview.service.calculations;

import com.szura.interview.model.NumberStatisticsResult;
import com.szura.interview.model.StatisticResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NumberStatisticsTest {

    NumberStatistics numberStatistics;

    @Before
    public void init() {
        this.numberStatistics = new NumberStatistics();
    }

    @Test
    public void shouldCheckWordWithNullAndReturnsFalse(){
        String word = null;
        boolean result = numberStatistics.matchRequirements(word);
        Assert.assertFalse(result);
    }

    @Test
    public void shouldCheckEmptyWordAndReturnsFalse(){
        String word = "";
        boolean result = numberStatistics.matchRequirements(word);
        Assert.assertFalse(result);
    }

    @Test
    public void shouldCheckWordWithWhiteSpaceAndReturnsFalse(){
        String word = "  ";
        boolean result = numberStatistics.matchRequirements(word);
        Assert.assertFalse(result);
    }

    @Test
    public void shouldCheckWordWithoutPhoneNumberAndReturnsFalse(){
        String word = "word";
        boolean result = numberStatistics.matchRequirements(word);
        Assert.assertFalse(result);
    }

    @Test
    public void shouldCheckWordWithPhoneNumberAndReturnsTrue(){
        String word = "123456789";
        boolean result = numberStatistics.matchRequirements(word);
        Assert.assertTrue(result);
    }

    @Test
    public void shouldCheckNullLineAndReturnsZero(){
        String line = null;
        NumberStatisticsResult expected = new NumberStatisticsResult(0);
        StatisticResult result = numberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckEmptyLineAndReturnsZero(){
        String line = "";
        NumberStatisticsResult expected = new NumberStatisticsResult(0);
        StatisticResult result = numberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckLineWithoutPhoneNumberAndReturnsZero(){
        String line = "Sample line without phone number";
        NumberStatisticsResult expected = new NumberStatisticsResult(0);
        StatisticResult result = numberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckLineWithPhoneNumberAndReturnsOne(){
        String line = "Sample line with 123456789 phone number";
        NumberStatisticsResult expected = new NumberStatisticsResult(1);
        StatisticResult result = numberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckLineWithTwoPhoneNumberAndReturnsTwo(){
        String line = "Sample line with 123456789 123456789 phone number";
        NumberStatisticsResult expected = new NumberStatisticsResult(2);
        StatisticResult result = numberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckLineWithNumberAndReturnsZero(){
        String line = "Sample line with 12345678 number";
        NumberStatisticsResult expected = new NumberStatisticsResult(0);
        StatisticResult result = numberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }


}
