package de.tukl.programmierpraktikum2020.mp1;

import java.util.Comparator;
import java.util.List;

public class BibleAnalyzer {

    public static void countWords(Map<String, Integer> counts) {
        for (String word : Util.getBibleWords()) {
            if (counts.get(word)== null) {
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
        //Map<String, Integer> counts = new ArrayMap();
        //Map<String, Integer> counts = new ListMap<>();
        Map<String, Integer> counts = new TreeMap<>(Comparator.<String>naturalOrder());

        countWords(counts);
        System.out.println(counts.size());

        //Initialisierung eines sortierten Arrays mit den Worten
        String[] words = new String[counts.size()];
        counts.keys(words);
        sort(words,counts);
        System.out.println(counts.size());


        // ausgeben aller Worte
        for (String word : words) {
            System.out.println(counts.get(word) + " " + word);
        }
    }

    public static void sort(String[] words,Map<String, Integer> counts ){
        mergesort(words,0,words.length-1,counts);
    }
    //mergesort aus Algorithmen und Datenstrukturen V8-44, Version 17.Juli 2019
    public static void mergesort (String[] A, int l, int r,Map<String, Integer> counts){
        if (l< r){
            int m = (l+r)/2;
            mergesort(A, l, m,counts);
            mergesort(A, m+1, r,counts);
            merge (A, l, m, r, counts);
        }
    }

    public static void merge (String[] A, int l, int m, int r, Map<String, Integer> counts){
        //temmporärer Speicher zum Mischen
        String[] B = new String[r-l+1];

        //Mischen A[l..m-1], A[m..r-1] -> B[0.. r-l+1]
        int i = l;
        int j = m+1;
        int k = 0;
        while ((i<= m) && (j<= r)) {
            if ((counts.get(A[i]) < counts.get(A[j]))) { B[k] = A[i]; i++;}
            else { B[k] = A[j]; j++;}
            k++;
        }
        if (i>m){
            for(int h = j; h<= r; h++){
                B[k] = A[h]; k++;
            }
        } else{
            for (int h = i; h<=m; h++){
                B[k] = A[h]; k++;
            }
        }
        //gemischte Sequenz zurück kopieren
        for (int x = 0; x< r-l+1; x++){
            A[l+x] = B[x];
        }
    }
}
