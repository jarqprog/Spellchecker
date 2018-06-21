package com.jq.customHashSet;

import com.jq.hasher.Hasher;

public class MyHashSet implements CustomHashSet {

    private final Hasher hasher;
    private final Node[] warehouse;
    private final int warehouseSize;
    private int storedElements;

    public MyHashSet(Hasher hasher) {
        this.hasher = hasher;
        this.warehouseSize = 100;
        this.warehouse = new Node[warehouseSize];
    }

    public MyHashSet(Hasher hasher, int size) {
        if (size < 10) {
            throw new IllegalArgumentException("Size should be greater than 9!");
        }
        this.hasher = hasher;
        this.warehouseSize = size;
        this.warehouse = new Node[warehouseSize];
    }

    @Override
    public boolean add(String element) {
        if ( contains(element) ) {
            return false;
        }

        int index = getHash(element);
        if (warehouse[index] == null) {
            warehouse[index] = new Node(null, null, element);
        } else {
            Node first = warehouse[index];
            Node newNode = new Node(null, first, element);
            first.prev = newNode;
            warehouse[index] = newNode;
        }
        storedElements++;
        return true;
    }

    @Override
    public boolean contains(String element) {
        if (storedElements == 0) {
            return false;
        }

        int index = getHash(element);
        if (warehouse[index] == null) {
            return false;
        }

        Node node = warehouse[index];

        while (node != null) {
            if (node.data.equals(element)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean remove(String element) {
        if (storedElements == 0) {
            return false;
        }

        int index = getHash(element);

        Node node = warehouse[index];

        while (node != null) {
            if (node.data.equals(element)) {

                Node prev = node.prev;
                Node next = node.next;

                if (prev == null && next == null) {
                    warehouse[index] = null;
                }
                else if (prev == null) {
                    warehouse[index] = next;
                    next.prev = null;
                } else {
                    prev.next = null;
                }
                storedElements--;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public int size() {
        return storedElements;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (Node node : warehouse) {
            if (node != null) {
                toReturn.append("-");
                while (node != null) {
                    toReturn.append(node.data);
                    toReturn.append(", ");
                    node = node.next;
                }
                toReturn.append("\n");
            }
        }
        return toReturn.toString();
    }

    private int getHash(String element) {
        return hasher.hash(element) % warehouseSize;
    }

    /**
     * auxiliary class
     */
    private class Node {
        private Node prev;
        private Node next;
        private final String data;

        private Node(Node prev, Node next, String data) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }
    }
}
