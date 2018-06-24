package com.jq.system.spellChecker;

import java.util.Set;

public interface SpellChecker {

    boolean isCorrect(String word);
    Set<String> giveSuggestions(String word);
}
