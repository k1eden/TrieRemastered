package com.k1;

import java.util.*;

public final class TrieRemastered {
    static class TrieN {
        Map<Character, TrieN> children = new TreeMap<Character, TrieN>();
        boolean ind;

        Map<Character, TrieN> getChildren() {
            return children;
        }
        // For put, remove, find.
        boolean isEndOfWord() {
            return ind;
        }

        void setEndOfWord(boolean endOfWord) {
            this.ind = endOfWord;
        }
    }

    static class Node {
        Map<Character, Node> nextN;
        char symbol;
        StringBuilder pref;
        boolean isEnd;
        Node prev;
        Node(char symbol, Node previous) {
            this.symbol = symbol;                                             // For find by prefix
            nextN = new HashMap<>();
            this.prev = previous;
            pref = new StringBuilder();
            if (previous != null) {
                pref.append(previous.pref);
                pref.append(symbol);
            }
        }
    }

    TrieN root = new TrieN();
    private Node root1;

    public void Trie() {
        root1 = new Node('\0', null);
    }


    public boolean find(String s) {
        TrieN ex = root;
        for (char ch : s.toLowerCase().toCharArray()) {
            if (!ex.children.containsKey(ch)) {
                return false;
            } else {
                ex = ex.children.get(ch);
            }
        }
        return true;
    }


    public void put(String s) {
        TrieN ex = root;
        for (char ch : s.toLowerCase().toCharArray()) {
            if (!ex.children.containsKey(ch)) {
                ex.children.put(ch, new TrieN());
            }
            ex = ex.children.get(ch);
        }
        ex.ind = true;
    }


    static Map<Integer, String> levelSpacesMap = new HashMap<Integer, String>();

    static String getSpace(int level) {
        String result = levelSpacesMap.get(level);
        if (result == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < level; i++) {
                sb.append(" ");
            }
            result = sb.toString();
            levelSpacesMap.put(level, result);
        }
        return result;
    }

    public static void printSorted(TrieN node) {
        printSorted2(node, 0);
    }

    private static void printSorted2(TrieN node, int level) {
        for (Character ch : node.children.keySet()) {
            System.out.println(getSpace(level) + ch);
            printSorted2(node.children.get(ch), level + 1);
        }
        if (node.ind) {
            System.out.println();
        }
    }


    public void remove(String word) {
        remove(root, word, 0);
    }

    private boolean remove(TrieN current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord()) {
                return false;
            }
            current.setEndOfWord(false);
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        TrieN testNode = current.getChildren().get(ch);
        if (testNode == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = remove(testNode, word, index + 1) && !testNode.isEndOfWord();

        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }
        return false;
    }
    //------------------------------------------------------------------------------------- For find by prefix
    private Node findNode(String word) {
        Node testNode = root1;
        for (char ch : word.toLowerCase().toCharArray()) {
            if (!testNode.nextN.containsKey(ch)) {
                return null;
            }
            testNode = testNode.nextN.get(ch);
        }
        return testNode;
    }

    List<String> util = new ArrayList<>();
    List<String> wordsList = new ArrayList<>();

    private void depthOfSearch(Node node) {
        util.add(node.pref.toString());
        if (node.isEnd) {
            wordsList.add(node.pref.toString());
        }
        for (Node next : node.nextN.values()) {
            if (!util.contains(next.pref.toString())) {
                depthOfSearch(next);
            }
        }
    }

    public List<String> findByPrefix(String word) {
        Node testNode = findNode(word);
        if (testNode != null) {
            util.clear();
            wordsList.clear();
            depthOfSearch(testNode);
            return wordsList;
        }
        return Collections.emptyList();
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrieRemastered trie = (TrieRemastered) o;
        return this.toString().equals(trie.toString());
    }

    @Override
    public String toString() {
        util.clear();
        wordsList.clear();
        depthOfSearch(root1);
        return wordsList.toString();
    }
}
