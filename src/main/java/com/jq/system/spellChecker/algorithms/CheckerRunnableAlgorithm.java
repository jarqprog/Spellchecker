package com.jq.system.spellChecker.algorithms;

import java.util.Set;

public interface CheckerRunnableAlgorithm extends Runnable {

    Set<String> getSuggestions();

}
