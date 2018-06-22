package com.jq.system.spellChecker.multithreadedChecker;

import com.jq.system.spellChecker.SpellChecker;

class LetterEraser implements CheckerAlgorithm {

    private final String word;
    private final SpellChecker spellChecker;

    LetterEraser(String word, SpellChecker spellChecker) {
        this.word = word;
        this.spellChecker = spellChecker;
    }

    @Override
    public void run() {

    }
}
