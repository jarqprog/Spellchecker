package com.jq;

import com.jq.simpleClient.controller.Controller;
import com.jq.simpleClient.controller.SimpleController;
import com.jq.simpleClient.view.SimpleView;
import com.jq.simpleClient.view.View;
import com.jq.system.customHashSet.CustomHashSet;
import com.jq.system.customHashSet.MyHashSet;
import com.jq.system.hasher.SimpleHasher;
import com.jq.system.reader.CustomReader;
import com.jq.system.reader.MyFileReader;
import com.jq.system.spellChecker.SpellChecker;
import com.jq.system.spellChecker.checker.MultiThreadedChecker;

import java.io.IOException;
import java.util.List;

public class Root {

    public static Root getInstance() {
        return new Root();
    }


    private Root() {}

    public void run() {
        final String FILE_PATH = "src/main/resources/wordlist.txt";
        createSimpleController(FILE_PATH).start();

    }


    private Controller createSimpleController(String wordListPath) {

        CustomReader reader = createReader(wordListPath);
        CustomHashSet knownWords = createWordsSet(reader);
        SpellChecker spellChecker = createSpellChecker(knownWords);
        View view = new SimpleView();
        return new SimpleController(view, spellChecker);
    }


    private CustomReader createReader(String wordListPath) {
        return new MyFileReader(wordListPath);
    }

    private CustomHashSet createWordsSet(CustomReader reader) {

        CustomHashSet set = null;
        try {
            List<String> words = reader.read();
            int hashSetSize = words.size();
            set = new MyHashSet(new SimpleHasher(), hashSetSize);
            words.forEach(set::add);

        } catch (IOException e) {
            System.out.println("Problem with loading wordlist occurred! Exiting program..");
            System.exit(-1);
        }
        return set;
    }

    private SpellChecker createSpellChecker(CustomHashSet knownWords) {
        return new MultiThreadedChecker(knownWords);
    }
}
