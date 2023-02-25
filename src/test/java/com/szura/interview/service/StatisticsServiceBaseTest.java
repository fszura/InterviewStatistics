package com.szura.interview.service;

import com.mifmif.common.regex.Generex;
import com.szura.interview.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;

public class StatisticsServiceBaseTest {


    protected Map<StatisticsType, StatisticResult> generateExpectedStatistics(Long palindromeCount, Long phoneNumberCount, Long idCount,  Long sentenceCount){
        return generateExpectedStatistics(palindromeCount, phoneNumberCount, idCount, 0L, sentenceCount);
    }
    protected Map<StatisticsType, StatisticResult> generateExpectedStatistics(Long palindromeCount, Long phoneNumberCount, Long idCount, Long passportCount,  Long sentenceCount){
        Map<StatisticsType, StatisticResult> expectedMap = new HashMap<>();
        expectedMap.put(StatisticsType.PALINDROME, new PalindromeCountResult(palindromeCount));
        expectedMap.put(StatisticsType.PHONE_NUMBER, new NumberStatisticsResult(generateMap(phoneNumberCount, idCount, passportCount)));
        expectedMap.put(StatisticsType.SENTENCE, new SentenceCountResult(sentenceCount));
        return expectedMap;
    }

    private Map<NumberResultType, Long> generateMap(Long phoneNumbers, Long ids, Long passports) {
        return Map.of(
                NumberResultType.PHONE_NUMBER, phoneNumbers,
                NumberResultType.ID, ids,
                NumberResultType.PASSPORT, passports
                );
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
        List<String> palindromes = List.of("aba", "oppo", "teset", "adda");
        return palindromes.get(ThreadLocalRandom.current().nextInt(0, palindromes.size()-1));
    }
}
