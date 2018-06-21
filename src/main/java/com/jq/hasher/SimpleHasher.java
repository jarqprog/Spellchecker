package com.jq.hasher;

public class SimpleHasher implements Hasher {

    @Override
    public int hash(String toHash) {

        int result = 1;

        for (char c : toHash.toCharArray() ) {
            result += c * 37;
        }
        return result;
    }
}
