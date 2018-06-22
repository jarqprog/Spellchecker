package com.jq.system.spellChecker.algorithms;

import com.jq.system.spellChecker.checker.MultiThreadedChecker;
import com.jq.system.spellChecker.checker.SimpleChecker;

import java.util.Set;

public class LetterReplacer extends AbsCheckerAlgorithm
        implements CheckerRunnableAlgorithm, CheckerAlgorithm {

    private String word;
    private MultiThreadedChecker spellChecker;

    public LetterReplacer() {}
    public LetterReplacer(String word, MultiThreadedChecker spellChecker) {
        this.word = word;
        this.spellChecker = spellChecker;
    }

    @Override
    public void run() {

    }

    @Override
    public Set<String> suggest(SimpleChecker checker, String word) {
        return null;
    }

    @Override
    public Set<String> getSuggestions() {
        return null;
    }
}
