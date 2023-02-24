package com.szura.interview.service.calculations;

import com.szura.interview.model.SentenceCountResult;

import java.text.BreakIterator;
import java.util.Locale;
import java.util.Optional;

public class SentenceStatistics implements Statistics<SentenceCountResult> {

    private Locale currentLocale;

    public SentenceStatistics() {
        currentLocale = new Locale("en", "US");
    }

    @Override
    public SentenceCountResult calculateStatistics(String line) {
        return Optional.ofNullable(line).map(l -> markBoundaries(l)).map(this::createResult).orElse(createResult(0));
    }

    @Override
    public boolean matchRequirements(String word) {
        return Optional.ofNullable(word).map(w->markBoundaries(w)>0).orElse(false);
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
    public SentenceCountResult createResult(long count) {
        return new SentenceCountResult(count);
    }
}
