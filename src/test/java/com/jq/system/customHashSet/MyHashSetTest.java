package com.jq.system.customHashSet;

import com.jq.system.hasher.SimpleHasher;
import com.jq.system.reader.CustomReader;
import com.jq.system.reader.MyFileReader;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class MyHashSetTest {

    private CustomHashSet set = new MyHashSet(new SimpleHasher());

    @Test
    public void size_if_empty() {
        assertEquals(0, set.size());
    }

    @Test
    public void contains_if_empty() {
        assertFalse(set.contains("John"));
    }

    @Test
    public void add_one_element() {

        String element = "Tom";

        assertTrue(set.add(element));
        assertEquals(1, set.size());
        assertTrue(set.contains(element));
    }

    @Test
    public void add_twice_same_element() {

        String element = "Tom";

        assertTrue(set.add(element));
        assertFalse(set.add(element));
        assertEquals(1, set.size());
        assertTrue(set.contains(element));
    }

    @Test
    public void add_elements() {

        String[] elements = getElements();

        String notAdded = "123456789";

        for (String element : elements) {
            assertTrue(set.add(element));
        }

        assertEquals(elements.length, set.size());

        for (String element : elements) {
            assertTrue(set.contains(element));
        }

        assertFalse(set.contains(notAdded));
    }


    @Test
    public void add_huge_number_of_elements() throws IOException {

        String filepath = "src/main/resources/wordlist.txt";
        int setSize = 5000;

        CustomHashSet hugeSet = new MyHashSet(new SimpleHasher(), setSize);

        CustomReader reader = new MyFileReader(filepath);

        List<String> hugeWordList = reader.read();

        for (String element : hugeWordList) {
            assertTrue(hugeSet.add(element));
        }

        assertEquals(hugeWordList.size(), hugeSet.size());

        for (String element : hugeWordList) {
            assertTrue(hugeSet.contains(element));
            assertTrue(hugeSet.remove(element));
        }

        assertEquals(0, hugeSet.size());
    }

    @Test
    public void remove_if_empty() {

        String toRemove = "John";

        assertFalse(set.remove(toRemove));

    }

    @Test
    public void remove_with_one_element() {

        String toRemove = "Maria";

        set.add(toRemove);

        assertTrue(set.remove(toRemove));
        assertEquals(0, set.size());
    }


    @Test
    public void remove_many_element() {

        String[] elements = getElements();
        String maria = "Maria";
        String notAdded = "monitor";

        for (String element : elements) {
            set.add(element);
        }

        set.add(maria);

        for (String element : elements) {
            assertTrue(set.remove(element));
        }

        for (String element : elements) {
            assertFalse(set.contains(element));
        }

        assertFalse(set.remove(notAdded));

        assertEquals(1, set.size());

        assertTrue(set.remove(maria));

        assertEquals(0, set.size());
    }

    private String[] getElements() {
        return new String[] {"journey", "Constantin", "Paris", "Enigma", "Code", "Java", "Little John",
                "Valencia", "Python", "Keyboard", "Magazine", "Vocalist"};
    }
}