package com.szura.interview.service.calculations;

import com.szura.interview.model.PalindromeCountResult;

import java.util.Optional;
import java.util.stream.IntStream;

public class PalindromeStatistics implements Statistics<PalindromeCountResult, Long> {

    @Override
    public Long sumStatistics(Long s, Long s1) {
        return s + s1;
    }

    @Override
    public Long emptyStatistics() {
        return 0L;
    }

    @Override
    public Long matchRequirements(String word) {
        return Optional.ofNullable(word).map(s->s.replaceAll("\\s+",""))
                .filter(s->!s.isEmpty()).map(String::toLowerCase).map(this::checkPalindrome).map(b->b?1L:0L).orElse(0L);
    }

    private boolean checkPalindrome(String wordToCheck){
        return IntStream.range(0, wordToCheck.length() /2).noneMatch(
                i -> wordToCheck.charAt(i) != wordToCheck.charAt(wordToCheck.length()-i-1));
    }

    @Override
    public PalindromeCountResult createResult(Long count) {
        return new PalindromeCountResult(count);
    }
}
