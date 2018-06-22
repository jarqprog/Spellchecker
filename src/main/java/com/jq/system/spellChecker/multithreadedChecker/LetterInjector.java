package com.jq.system.spellChecker.multithreadedChecker;

import java.util.HashSet;
import java.util.Set;

class LetterInjector implements CheckerAlgorithm {


    private final String word;
    private final MyChecker spellChecker;

    LetterInjector(String word, MyChecker spellChecker) {
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

        // inject new letters on index 0
        char toInject = 'A';
        while (toInject <= 'z') {
            suggestion = prepareSuggestion(word, 0);
            suggestion[0] = toInject;
            if (spellChecker.hasWord(suggestion)) {
                suggestions.add(String.valueOf(suggestion));
            }

            if (toInject == 'Z') {
                toInject = 'a';
            } else {
                toInject++;
            }
        }

        // inject new letters on other indexes
        for (int i=0; i<len; i++) {
            toInject = 'a';
            while (toInject <= 'z') {
                suggestion = prepareSuggestion(word, i);
                suggestion[i] = toInject;
                if (spellChecker.hasWord(suggestion)) {
                    suggestions.add(String.valueOf(suggestion));
                }
                toInject++;
            }
        }

        if (suggestions.size() > 0) {
            spellChecker.addSuggestions(suggestions);
        }

        System.out.println("Letters injector: ended, suggest: " + suggestions);
    }

    private char[] prepareSuggestion(String word, int injectIndex) {

        char[] suggestion = new char[word.length()+1];
        int idx = 0;
        for (char letter : word.toCharArray()) {
            if (idx != injectIndex) {
                suggestion[idx] = letter;
            }
            idx++;
        }
        return suggestion;
    }
}
