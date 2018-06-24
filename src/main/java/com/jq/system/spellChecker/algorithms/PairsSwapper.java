package com.jq.system.spellChecker.algorithms;

import com.jq.system.spellChecker.checker.SimpleChecker;

import java.util.HashSet;
import java.util.Set;

public class PairsSwapper extends AbsCheckerAlgorithm
        implements CheckerRunnableAlgorithm, CheckerAlgorithm {

    private String word;
    private SimpleChecker spellChecker;
    private Set<String> suggestions;

    public static CheckerAlgorithm create() {
        return new PairsSwapper();
    }

    public static CheckerRunnableAlgorithm createRunnable(String word, SimpleChecker spellChecker) {
        return new PairsSwapper(word, spellChecker);
    }

    private PairsSwapper() {}

    private PairsSwapper(String word, SimpleChecker spellChecker) {
        this.word = word;
        this.spellChecker = spellChecker;
    }

    @Override
    public void run() {
        suggestions = suggest(spellChecker, word);
    }

    @Override
    public Set<String> getSuggestions() {
        if (suggestions == null) {
            suggestions = new HashSet<>();
        }
        return suggestions;
    }

    @Override
    public Set<String> suggest(SimpleChecker spellChecker, String word) {

        suggestions = new HashSet<>();

        int len = word.length();

        if (len < 2) {
            return suggestions;
        }

        char[] suggestion;

        for (int i=1; i<len; i++) {
            suggestion = word.toCharArray();
            char aux = suggestion[i-1];
            suggestion[i-1] = suggestion[i];
            suggestion[i] = aux;
            String suggestedWord = gatherWord(suggestion);

            if (spellChecker.isCorrect(suggestedWord)) {
                suggestions.add(suggestedWord);
            }
        }

        return suggestions;
    }
}
