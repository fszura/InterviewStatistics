package com.szura.interview.service.calculations;

import com.szura.interview.model.SentenceCountResult;

import java.text.BreakIterator;
import java.util.Locale;
import java.util.Optional;

public class SentenceStatistics implements Statistics<SentenceCountResult, Long> {

    private final Locale currentLocale;

    public SentenceStatistics() {
        currentLocale = new Locale("en", "US");
    }

    @Override
    public SentenceCountResult calculateStatistics(String line) {
        return Optional.ofNullable(line).map(this::markBoundaries).map(this::createResult).orElse(createResult(emptyStatistics()));
    }

    @Override
    public Long sumStatistics(Long s, Long s1) {
        return s+s1;
    }

    @Override
    public Long emptyStatistics() {
        return 0L;
    }

    @Override
    public Long matchRequirements(String word) {
        return Optional.ofNullable(word).map(w->markBoundaries(w)>0).map(b->b?1L:0L).orElse(0L);
    }

    private long markBoundaries(String target) {
        BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(currentLocale);
        int count = 0;
        sentenceIterator.setText(target);
        int boundary = sentenceIterator.first();
        while (boundary != BreakIterator.DONE) {
            ++count;
            boundary = sentenceIterator.next();
        }
        return count - 1;
    }

    @Override
    public SentenceCountResult createResult(Long count) {
        return new SentenceCountResult(count);
    }
}
