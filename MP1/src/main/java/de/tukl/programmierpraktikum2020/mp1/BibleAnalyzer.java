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
        }
    }


    //bubble sort = wir vertauschen wiederholt benachbarte Elemente, die falsch herum sortiert sind
    //siehe Algodat V8-16 ff. Version 17.Juli 2019
    public static void sort(String[] words, Map<String, Integer> counts) {
        boolean done = false;
        while (!done){
            done = true;
            for (int i = 0; i < words.length-1; i++){
                if (counts.get(words[i]) > counts.get(words[i+1])){
                    String zwischenspeicher = words[i];
                    words[i] = words[i+1];
                    words[i+1] = zwischenspeicher;
                    done = false;
                }
            }
        }
    }
}
