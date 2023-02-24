package com.szura.interview.service.calculations;

import com.szura.interview.model.PalindromeCountResult;

import java.util.Optional;
import java.util.stream.IntStream;

public class PalindromeStatistics implements Statistics<PalindromeCountResult> {

    @Override
    public boolean matchRequirements(String word) {
        return Optional.ofNullable(word).map(s->s.replaceAll("\\s+",""))
                .filter(s->!s.isEmpty()).map(String::toLowerCase).map(this::checkPalindrome).orElse(false);
    }

    private boolean checkPalindrome(String wordToCheck){
        return IntStream.range(0, wordToCheck.length() /2).noneMatch(
                i -> wordToCheck.charAt(i) != wordToCheck.charAt(wordToCheck.length()-i-1));
    }

    @Override
    public PalindromeCountResult createResult(long count) {
        return new PalindromeCountResult(count);
    }
}
