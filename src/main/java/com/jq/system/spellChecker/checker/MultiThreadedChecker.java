package com.jq.system.spellChecker.checker;

import com.jq.system.customHashSet.CustomHashSet;
import com.jq.system.spellChecker.algorithms.*;

import java.util.HashSet;
import java.util.Set;

public class MultiThreadedChecker extends SimpleChecker {

    private Set<String> suggestions;

    public MultiThreadedChecker(CustomHashSet knownWords) {
        super(knownWords);
    }

    @Override
    public boolean isCorrect(String word) {
        return getKnownWords().contains(word);
    }

    @Override
    public Set<String> giveSuggestions(String word) {

        suggestions = new HashSet<>();

        CheckerRunnableAlgorithm[] algorithms = {

                PairsSwapper.createRunnable(word, this),
                LetterInjector.createRunnable(word, this),
                LetterEraser.createRunnable(word, this),
                LetterReplacer.createRunnable(word, this) };

        int len = algorithms.length;
        Thread[] threads = new Thread[len];


        for(int i=0; i<len; i++) {

            threads[i] = new Thread(algorithms[i]);
            threads[i].start();
            try {
                threads[i].join();
            } catch (InterruptedException notUsed) { }
        }

        for (CheckerRunnableAlgorithm algorithm : algorithms) {
            suggestions.addAll(algorithm.getSuggestions());

        }

        return suggestions;
    }
}
