package de.tukl.programmierpraktikum2020.mp1;

import java.util.Comparator;

public class BibleAnalyzer {
    public static void countWords(Map<String, Integer> counts) {
        for (String word : Util.getBibleWords()) {
            if (counts.get(word) == null) {
                // current word does not exists
                counts.put(word, 1);
            } else {
                // current word already exists
                Integer currValue = counts.get(word);
                counts.put(word, currValue + 1);
            }
        }
    }

    public static void main(String[] args) {
        //Map<String, Integer> bibleTreeMap = new TreeMap<>(Comparator.<String>naturalOrder());
        //countWords(bibleTreeMap);
        Map<String, Integer> ListMap = new ListMap<>();
        countWords(ListMap);

        // Save words without duplicates
        String[] setWords = new String[ListMap.size()];
        ListMap.keys(setWords);

        // Call the method which sorts the array
        sort(setWords, ListMap);

        // Print all words: it costs a lot of time
        for (String word : setWords) {
            System.out.println(ListMap.get(word) + " " + word);
        }
    }

    public static void sort(String[] words, Map<String, Integer> counts) {
        // We are implementing the BubbleSort
        String temp;
        for (int i = 1; i < words.length; i++) {
            for (int j = 0; j < words.length - i; j++) {
                if (counts.get(words[j]) >= counts.get(words[j + 1])) {
                    temp = words[j];
                    words[j] = words[j + 1];
                    words[j + 1] = temp;
                }
            }
        }
    }
}