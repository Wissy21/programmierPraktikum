package de.tukl.programmierpraktikum2020.mp1;

import java.util.Comparator;
import java.util.List;

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
        //Initialisierung einer Map mit den Worten aus der Bibel
        Map<String, Integer> counts = new ArrayMap();
        //Map<String, Integer> counts = new ListMap<>();
        //Map<String, Integer> counts = new TreeMap<>(Comparator.<String>naturalOrder());
        countWords(counts);

        //Initialisierung eines sortierten Arrays mit den Worten
        String[] words = new String[counts.size()];
        counts.keys(words);
        sort(words, counts);


        // ausgeben aller Worte
        for (String word : words) {
            System.out.println(counts.get(word) + " " + word);
        /*for (int i = 0; i < 5; i++){
            System.out.println(counts.get(words[i]) + " " + words[i]);*/
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
