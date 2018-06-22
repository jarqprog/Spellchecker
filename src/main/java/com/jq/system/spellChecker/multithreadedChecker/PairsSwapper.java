package com.jq.system.spellChecker.multithreadedChecker;

import java.util.HashSet;
import java.util.Set;

class PairsSwapper implements CheckerAlgorithm {

    private final String word;
    private final MyChecker spellChecker;

    PairsSwapper(String word, MyChecker spellChecker) {
        this.word = word;
        this.spellChecker = spellChecker;
    }

    @Override
    public void run() {
        int len = word.length();

        if (len < 2) {
            return;
        }

        Set<String> suggestions = new HashSet<>();

        char[] suggestion;

        for (int i=1; i<len; i++) {
            suggestion = word.toCharArray();
            char aux = suggestion[i-1];
            suggestion[i-1] = suggestion[i];
            suggestion[i] = aux;
            if (spellChecker.hasWord(suggestion)) {
                suggestions.add(String.valueOf(suggestion));
            }
        }

        if (suggestions.size() > 0) {
            spellChecker.addSuggestions(suggestions);
        }

        System.out.println("PairsSwapper: ended, suggest: " + suggestions);
    }
}
