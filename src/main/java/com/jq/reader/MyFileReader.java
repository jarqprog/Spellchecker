package com.jq.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFileReader implements CustomReader {

    private final String filepath;

    public MyFileReader(String filepath) {

        File file = new File(filepath);

        if (! file.exists() || ! file.isFile() ) {
            throw new IllegalArgumentException("given filepath is incorrect!");
        }

        this.filepath = filepath;
    }

    @Override
    public List<String> read() throws IOException {
        List<String> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        }
        return result;
    }

    @Override
    public int countLines() throws IOException {
        int counter = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {

            while (br.readLine() != null) {
                counter++;
            }
        }

        return counter;
    }
}
