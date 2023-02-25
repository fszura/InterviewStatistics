package com.szura.interview.service.calculations;

import com.szura.interview.model.SentenceCountResult;
import com.szura.interview.model.StatisticResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SentenceStatisticsTest {

    SentenceStatistics sentenceStatistics;

    @Before
    public void init(){
        sentenceStatistics = new SentenceStatistics();
    }

    @Test
    public void matchRequirements_emptyPhrase_expectZero(){
        String phase = "";

        Long expected = 0L;
        Long result = sentenceStatistics.matchRequirements(phase);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void matchRequirements_phraseWithNull_expectZero(){
        String phase = null;

        Long expected = 0L;
        Long result = sentenceStatistics.matchRequirements(phase);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void matchRequirements_phraseWhichIsASentence_expectTrue(){
        String phase = "This is a sentence.";

        Long expected = 1L;
        Long result = sentenceStatistics.matchRequirements(phase);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void matchRequirements_phraseWhichIsAQuestion_expectTrue(){
        String phase = "Is it a sentence?";

        Long expected = 1L;
        Long result = sentenceStatistics.matchRequirements(phase);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void matchRequirements_phraseWhichIsSentence_expectTrue(){
        String phase = "Is it a sentence!";

        Long expected = 1L;
        Long result = sentenceStatistics.matchRequirements(phase);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void calculateSentenceStatistics_emptyLine_expectZero() {
        String line = "";
        SentenceCountResult expected = new SentenceCountResult(0L);

        StatisticResult result = sentenceStatistics.calculateStatistics(line);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void calculateSentenceStatistics_LineWithNull_expectZero() {
        String line = null;
        SentenceCountResult expected = new SentenceCountResult(0L);

        StatisticResult result = sentenceStatistics.calculateStatistics(line);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void calculateSentenceStatistics_lineWithOneSentence_expectOne() {
        String line = "A little test.";
        SentenceCountResult expected = new SentenceCountResult(1L);

        StatisticResult result = sentenceStatistics.calculateStatistics(line);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void calculateSentenceStatistics_lineWithFewSentences_expectThree() {
        String line = "A little test. Small test. Another case?";
        SentenceCountResult expected = new SentenceCountResult(3L);

        StatisticResult result = sentenceStatistics.calculateStatistics(line);

        Assert.assertEquals(expected, result);
    }


    @Test
    public void calculateSentenceStatistics_lineWithOneQuestion_expectOne() {
        String line = "A little test?";
        SentenceCountResult expected = new SentenceCountResult(1L);

        StatisticResult result = sentenceStatistics.calculateStatistics(line);

        Assert.assertEquals(expected, result);
    }
}