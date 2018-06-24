package com.jq.simpleClient.view;

import java.util.Collection;
import java.util.Scanner;

public class SimpleView implements View {


    @Override
    public void displayMessage(String message) {

        System.out.println(message);

    }

    @Override
    public void displayCollection(Collection<String> collection) {
        collection.forEach(System.out::println);
    }

    @Override
    public String getInput() {

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (input.length() == 0) {
            input = scanner.nextLine();
        }
        return input;
    }
}
