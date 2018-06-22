package com.jq.system.spellChecker.algorithms;

import com.jq.system.spellChecker.checker.SimpleChecker;

import java.util.HashSet;
import java.util.Set;

public class LetterInjector extends AbsCheckerAlgorithm
        implements CheckerRunnableAlgorithm, CheckerAlgorithm {


    private String word;
    private SimpleChecker spellChecker;
    private Set<String> suggestions;

    public static CheckerAlgorithm create() {
        return new LetterInjector();
    }

    public static CheckerRunnableAlgorithm createRunnable(String word, SimpleChecker spellChecker) {
        return new LetterInjector(word, spellChecker);
    }

    private LetterInjector() {}

    private LetterInjector(String word, SimpleChecker spellChecker) {
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
        int len = word.length();

        if (len < 2) {
            return suggestions;
        }

        char[] suggestion;
        String suggestedWord;

        // inject new letters on index 0
        char toInject = 'A';
        while (toInject <= 'z') {

            suggestion = prepareSuggestion(word, 0);
            suggestion[0] = toInject;
            suggestedWord = gatherWord(suggestion);

            if (spellChecker.isCorrect(suggestedWord)) {
                suggestions.add(String.valueOf(suggestion));
            }

            if (toInject == 'Z') {
                toInject = 'a';
            } else {
                toInject++;
            }
        }

        // inject new letters on other indexes
        for (int i=0; i<=len; i++) {
            toInject = 'a';

            while (toInject <= 'z') {

                suggestion = prepareSuggestion(word, i);
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

    private char[] prepareSuggestion(String word, int injectIndex) {

        char[] origin = word.toCharArray();
        int len = origin.length;
        char[] suggestion = new char[len+1];
        int idx = 0;
        for (int i=0; i<len; i++) {

            if (idx == injectIndex) {
                idx++;
            }  // skip index

            suggestion[idx] = origin[i];
            idx++;
        }
        return suggestion;
    }
}
