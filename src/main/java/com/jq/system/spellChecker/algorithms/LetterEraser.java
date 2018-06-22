package com.jq.system.spellChecker.algorithms;

import com.jq.system.spellChecker.SpellChecker;
import com.jq.system.spellChecker.checker.SimpleChecker;

import java.util.Set;

public class LetterEraser extends AbsCheckerAlgorithm
        implements CheckerRunnableAlgorithm, CheckerAlgorithm {

    private String word;
    private SpellChecker spellChecker;

    public LetterEraser() {}

    public LetterEraser(String word, SpellChecker spellChecker) {
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
