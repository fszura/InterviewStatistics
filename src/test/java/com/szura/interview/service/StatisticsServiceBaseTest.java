package com.szura.interview.service;

import com.mifmif.common.regex.Generex;
import com.szura.interview.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;

public class StatisticsServiceBaseTest {


    protected Map<StatisticsType, StatisticResult> generateExpectedStatistics(Long palindromeCount, Long phoneNumberCount, Long sentenceCount){
        Map<StatisticsType, StatisticResult> expectedMap = new HashMap<>();
        Optional.ofNullable(palindromeCount).ifPresent(c-> expectedMap.put(StatisticsType.PALINDROME, new PalindromeCountResult(c)));
        Optional.ofNullable(phoneNumberCount).ifPresent(c-> expectedMap.put(StatisticsType.PHONE_NUMBER, new PhoneNumberStatisticsResult(c)));
        Optional.ofNullable(sentenceCount).ifPresent(c-> expectedMap.put(StatisticsType.SENTENCE, new SentenceCountResult(c)));
        return expectedMap;
    }

    protected String generateSentence(long noPalindromes, long noPhoneNumbers){
        StringBuffer buffer = new StringBuffer();
        LongStream.range(0, noPalindromes).forEachOrdered(i-> buffer.append(getPalindrome()).append(" "));
        LongStream.range(0, noPhoneNumbers).forEachOrdered(i-> buffer.append(generatePhoneNumber()).append(" "));
        return buffer.toString();
    }

    private String generatePhoneNumber(){
        Generex generex = new Generex("[3-9][0-9]{7}[0-2]");
        return generex.random();
    }

    private String getPalindrome(){
        String[] palindromes = {"aba", "oppo", "teset", "adda"};
        return palindromes[ThreadLocalRandom.current().nextInt(0, palindromes.length-1)];
    }
}
