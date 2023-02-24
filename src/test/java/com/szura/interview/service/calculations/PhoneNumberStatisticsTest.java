package com.szura.interview.service.calculations;

import com.szura.interview.model.PhoneNumberStatisticsResult;
import com.szura.interview.model.StatisticResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PhoneNumberStatisticsTest {

    PhoneNumberStatistics phoneNumberStatistics;

    @Before
    public void init() {
        this.phoneNumberStatistics = new PhoneNumberStatistics();
    }

    @Test
    public void shouldCheckWordWithNullAndReturnsFalse(){
        String word = null;
        boolean result = phoneNumberStatistics.matchRequirements(word);
        Assert.assertFalse(result);
    }

    @Test
    public void shouldCheckEmptyWordAndReturnsFalse(){
        String word = "";
        boolean result = phoneNumberStatistics.matchRequirements(word);
        Assert.assertFalse(result);
    }

    @Test
    public void shouldCheckWordWithWhiteSpaceAndReturnsFalse(){
        String word = "  ";
        boolean result = phoneNumberStatistics.matchRequirements(word);
        Assert.assertFalse(result);
    }

    @Test
    public void shouldCheckWordWithoutPhoneNumberAndReturnsFalse(){
        String word = "word";
        boolean result = phoneNumberStatistics.matchRequirements(word);
        Assert.assertFalse(result);
    }

    @Test
    public void shouldCheckWordWithPhoneNumberAndReturnsTrue(){
        String word = "123456789";
        boolean result = phoneNumberStatistics.matchRequirements(word);
        Assert.assertTrue(result);
    }

    @Test
    public void shouldCheckNullLineAndReturnsZero(){
        String line = null;
        PhoneNumberStatisticsResult expected = new PhoneNumberStatisticsResult(0);
        StatisticResult result = phoneNumberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckEmptyLineAndReturnsZero(){
        String line = "";
        PhoneNumberStatisticsResult expected = new PhoneNumberStatisticsResult(0);
        StatisticResult result = phoneNumberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckLineWithoutPhoneNumberAndReturnsZero(){
        String line = "Sample line without phone number";
        PhoneNumberStatisticsResult expected = new PhoneNumberStatisticsResult(0);
        StatisticResult result = phoneNumberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckLineWithPhoneNumberAndReturnsOne(){
        String line = "Sample line with 123456789 phone number";
        PhoneNumberStatisticsResult expected = new PhoneNumberStatisticsResult(1);
        StatisticResult result = phoneNumberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckLineWithTwoPhoneNumberAndReturnsTwo(){
        String line = "Sample line with 123456789 123456789 phone number";
        PhoneNumberStatisticsResult expected = new PhoneNumberStatisticsResult(2);
        StatisticResult result = phoneNumberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldCheckLineWithNumberAndReturnsZero(){
        String line = "Sample line with 12345678 number";
        PhoneNumberStatisticsResult expected = new PhoneNumberStatisticsResult(0);
        StatisticResult result = phoneNumberStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }


}
