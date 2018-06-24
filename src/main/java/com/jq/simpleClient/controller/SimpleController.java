package com.jq.simpleClient.controller;

import com.jq.simpleClient.view.View;
import com.jq.system.spellChecker.SpellChecker;

import java.util.Set;

public class SimpleController implements Controller {

    private final View view;
    private final SpellChecker spellChecker;

    public SimpleController(View view, SpellChecker spellChecker) {
        this.view = view;
        this.spellChecker = spellChecker;
    }

    @Override
    public void start() {

        String userInput = "";

        view.displayMessage("Welcome to word checker demo.");

        while (! userInput.toLowerCase().equals("q")) {
            view.displayMessage("To exit program enter 'q'");
            view.displayMessage("Type some english word (correct or incorrect - I will check it & give " +
                    "suggestions if necessary): ");

            userInput = view.getInput();

            if (userInput.toLowerCase().equals("q")) {
                continue;
            }

            else if (spellChecker.isCorrect(userInput)) {
                view.displayMessage("Given word is correct!");
            } else {
                Set<String> suggestions = spellChecker.giveSuggestions(userInput);
                if (suggestions.size() == 0) {
                    view.displayMessage("I've no suggestions for You.");
                } else {
                    view.displayMessage("I've found some suggestions:");
                    view.displayCollection(suggestions);
                }
            }

            view.displayMessage("\n     *****\n");
        }
    }
}
