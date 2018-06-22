package com.jq.system.spellChecker.algorithms;

import com.jq.system.spellChecker.SpellChecker;
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
        return null;
    }

}
