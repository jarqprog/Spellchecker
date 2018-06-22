package com.jq.system.spellChecker.multithreadedChecker;

import com.jq.system.spellChecker.SpellChecker;

class WordSplitter implements CheckerAlgorithm {

    private final String word;
    private final SpellChecker spellChecker;

    WordSplitter(String word, SpellChecker spellChecker) {
        this.word = word;
        this.spellChecker = spellChecker;
    }

    @Override
    public void run() {

    }
}
