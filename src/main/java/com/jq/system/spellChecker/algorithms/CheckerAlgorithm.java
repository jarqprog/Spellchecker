package com.jq.system.spellChecker.algorithms;


import com.jq.system.spellChecker.checker.SimpleChecker;

import java.util.Set;

public interface CheckerAlgorithm {

    Set<String> suggest(SimpleChecker checker, String word);
}
