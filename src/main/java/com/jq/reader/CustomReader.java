package com.jq.reader;

import java.io.IOException;
import java.util.List;

public interface CustomReader {

    List<String> read() throws IOException;
    int countLines() throws IOException;
}
