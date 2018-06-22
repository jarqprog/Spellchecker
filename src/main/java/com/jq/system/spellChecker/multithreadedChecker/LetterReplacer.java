package com.jq.system.spellChecker.multithreadedChecker;

class LetterReplacer implements CheckerAlgorithm {

    private final String word;
    private final MyChecker spellChecker;

    LetterReplacer(String word, MyChecker spellChecker) {
        this.word = word;
        this.spellChecker = spellChecker;
    }

    @Override
    public void run() {




    }
}
