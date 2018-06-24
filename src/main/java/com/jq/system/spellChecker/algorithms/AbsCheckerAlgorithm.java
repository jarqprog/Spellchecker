package com.jq.system.spellChecker.algorithms;

public abstract class AbsCheckerAlgorithm {

    public AbsCheckerAlgorithm () {}

    protected String gatherWord(char[] letters) {
        return String.valueOf(letters);
    }
}
