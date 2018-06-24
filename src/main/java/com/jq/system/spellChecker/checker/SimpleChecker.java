package com.jq.system.spellChecker.checker;

import com.jq.system.customHashSet.CustomHashSet;
import com.jq.system.spellChecker.SpellChecker;
import com.jq.system.spellChecker.algorithms.CheckerAlgorithm;

import java.util.HashSet;
import java.util.Set;

public class SimpleChecker implements SpellChecker {


    private final CustomHashSet knownWords;
    private Set<String> suggestions;

    public SimpleChecker(CustomHashSet knownWords, CheckerAlgorithm... algorithms) {
        this.knownWords = knownWords;
    }


    @Override
    public boolean isCorrect(String word) {
        return false;
    }

    @Override
    public Set<String> giveSuggestions(String word) {
        return null;
    }

    protected CustomHashSet getKnownWords() {
        return knownWords;
    }
}
