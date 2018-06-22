package com.jq.system.spellChecker.algorithms;

import com.jq.system.spellChecker.checker.SimpleChecker;

import java.util.HashSet;
import java.util.Set;

public class LetterEraser extends AbsCheckerAlgorithm
        implements CheckerRunnableAlgorithm, CheckerAlgorithm {

    private String word;
    private SimpleChecker spellChecker;
    private Set<String> suggestions;

    public static CheckerAlgorithm create() {
        return new LetterEraser();
    }

    public static CheckerRunnableAlgorithm createRunnable(String word, SimpleChecker spellChecker) {
        return new LetterEraser(word, spellChecker);
    }

    private LetterEraser() {}

    private LetterEraser(String word, SimpleChecker spellChecker) {
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

        for (int i=0; i<len; i++) {

            suggestion = new char[len-1];

            int originIdx = 0;
            for (int j=0; j<len-1; j++) {
                if (j != i) {
                    suggestion[j] = origin[originIdx];
                } else {
                    originIdx++;  // ignore letter
                    suggestion[j] = origin[originIdx];
                }
                originIdx++;
            }

            suggestedWord = gatherWord(suggestion);
            if (checker.isCorrect(suggestedWord)) {
                suggestions.add(suggestedWord);
            }
        }
        return suggestions;
    }

}
