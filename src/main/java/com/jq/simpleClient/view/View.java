package com.jq.simpleClient.view;

import java.util.Collection;

public interface View {

    void displayMessage(String message);
    void displayCollection(Collection<String> collection);
    String getInput();
}
