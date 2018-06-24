package com.jq.system.spellChecker.algorithms;

import com.jq.system.spellChecker.checker.SimpleChecker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LetterReplacer extends AbsCheckerAlgorithm
        implements CheckerRunnableAlgorithm, CheckerAlgorithm {

    private String word;
    private SimpleChecker spellChecker;
    private Set<String> suggestions;

    public static CheckerAlgorithm create() {
        return new LetterReplacer();
    }

    public static CheckerRunnableAlgorithm createRunnable(String word, SimpleChecker spellChecker) {
        return new LetterReplacer(word, spellChecker);
    }

    private LetterReplacer() {}

    private LetterReplacer(String word, SimpleChecker spellChecker) {
        this.word = word;
        this.spellChecker = spellChecker;
    }

    @Override
    public Set<String> getSuggestions() {
        if (suggestions == null) {
            suggestions = new HashSet<>();
        }
        return suggestions;
    }

    @Override
    public void run() {
        suggestions = suggest(spellChecker, word);
    }

    @Override
    public Set<String> suggest(SimpleChecker checker, String word) {

        suggestions = new HashSet<>();

        char[] origin = word.toCharArray();
        int len = origin.length;
        char[] suggestion;
        String suggestedWord;

        // replace letter on index 0
        char toInject = 'A';
        while (toInject <= 'Z') {

            suggestion = Arrays.copyOf(origin, len);
            suggestion[0] = toInject;
            suggestedWord = gatherWord(suggestion);

            if (spellChecker.isCorrect(suggestedWord)) {
                suggestions.add(String.valueOf(suggestion));
            }

            toInject++;
        }

        // replace letters on other indexes
        for (int i=1; i<len; i++) {
            toInject = 'a';

            while (toInject <= 'z') {

                suggestion = Arrays.copyOf(origin, len);
                suggestion[i] = toInject;
                suggestedWord = gatherWord(suggestion);

                if (spellChecker.isCorrect(suggestedWord)) {
                    suggestions.add(String.valueOf(suggestion));
                }
                toInject++;
            }
        }

        return suggestions;
    }
}
