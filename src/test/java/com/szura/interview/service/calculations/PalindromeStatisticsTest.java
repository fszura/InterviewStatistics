package com.szura.interview.service.calculations;

import com.szura.interview.model.PalindromeCountResult;
import com.szura.interview.model.StatisticResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PalindromeStatisticsTest {

    Statistics palindromeStatistics;

    @Before
    public void init(){
        palindromeStatistics = new PalindromeStatistics();
    }

    @Test
    public void  countNumberOfPalindromesInEmptyLine_expectNone(){
        String line = "";
        StatisticResult result = palindromeStatistics.calculateStatistics(line);
        PalindromeCountResult expected = new PalindromeCountResult(0);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void  countNumberOfPalindromesInLineWithOnlySpaces_expectNone(){
        String line = "       ";
        StatisticResult result = palindromeStatistics.calculateStatistics(line);
        PalindromeCountResult expected = new PalindromeCountResult(0);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void  countNumberOfPalindromesInLineWithOnePalindrome_expectNone(){
        String line = "tttooottt";
        StatisticResult result = palindromeStatistics.calculateStatistics(line);
        PalindromeCountResult expected = new PalindromeCountResult(1);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void  countNumberOfPalindromesInLine_expectOne(){
        String line = " this is line with palindrome tet";
        StatisticResult result = palindromeStatistics.calculateStatistics(line);
        PalindromeCountResult expected = new PalindromeCountResult(1);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void  countNumberOfPalindromesInLine_expectFew(){
        String line = " this is line with palindrome tet pep gtg oooppooo";
        StatisticResult expected = new PalindromeCountResult(4);
        StatisticResult result = palindromeStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void  countNumberOfPalindromesInEmptyLine_expectZero(){
        String line = "";
        StatisticResult expected = new PalindromeCountResult(0);
        StatisticResult result = palindromeStatistics.calculateStatistics(line);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void  countNumberOfPalindromesInLineWithNull_expectZero(){
        StatisticResult expected = new PalindromeCountResult(0);
        StatisticResult result = palindromeStatistics.calculateStatistics(null);
        Assert.assertEquals(expected, result);
    }
}
