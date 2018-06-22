package com.jq.system.spellChecker.multithreadedChecker;

import com.jq.system.customHashSet.CustomHashSet;
import com.jq.system.spellChecker.SpellChecker;

import java.util.HashSet;
import java.util.Set;

public class MyChecker implements SpellChecker {

    private final CustomHashSet knownWords;
    private Set<String> suggestions;

    public MyChecker(CustomHashSet knownWords) {
        this.knownWords = knownWords;
    }

    @Override
    public boolean isCorrect(String word) {
        return knownWords.contains(word);
    }

    @Override
    public Set<String> giveSuggestions(String word) {
        this.suggestions = new HashSet<>();

        Thread[] algorithms = {
                                new Thread(new PairsSwapper(word, this)),
                                new Thread(new LetterInjector(word, this)) };

        for (Thread algorithm : algorithms) {
            algorithm.start();
        }

        System.out.println("CHECKER: " + suggestions);

        return suggestions;
    }

    // used by MyCheckerAlgorithms
    void addSuggestions(Set<String> words) {
        if (suggestions == null) {
            suggestions = new HashSet<>();
        }
        suggestions.addAll(words);
    }

    boolean hasWord(char[] letters) {
        return knownWords.contains(String.valueOf(letters));
    }
}
