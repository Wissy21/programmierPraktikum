package de.tukl.programmierpraktikum2020.mp1;

public class ArrayMap implements Map<String, Integer> {

    ArrayPair[] arr= new ArrayPair[100]; //erstelle Array mit Länge 100

    @Override
    public Integer get(String key) {
        for (int i = 0; i < arr.length; i++) { //wenn key gefunden wird, wird value ausgegeben
            if (arr[i] != null) { //bei Einträgen, die nicht null sind
                if (arr[i].getFirst().equals(key)) {
                    return arr[i].getSecond();
                }
            }
        }
        return null; //wenn nicht vorhanden = null;
    }

    @Override
    public void put(String key, Integer value) {
        boolean eingefuegt = false;
        for (int i = 0; i < arr.length; i++) { //wenn key gefunden, wird value neu gesetzt
            if (arr[i] != null) {  //bei Einträgen, die nicht null sind
                if (arr[i].getFirst().equals(key)) {
                    remove(key);
                    arr[i] = new ArrayPair(key, value);
                    eingefuegt = true;
                    break;
                }
            }
        }
        if (!eingefuegt) {
            for (int i = 0; i < arr.length; i++) { //wenn key nicht gefunden, aber freie Einträge vorhanden, wird neues ArrayPair-Element mit key und value erzeugt
                if (arr[i] == null) {
                    arr[i] = new ArrayPair(key, value);
                    eingefuegt = true;
                    break;
                }
            }
        }
        if (!eingefuegt) { //key nicht gefunden, keine freien Einträge -> Array muss neu erstellt und kopiert werden
            ArrayPair[] oldarr = new ArrayPair[arr.length]; //neues "altes" Array wird erstellt
            for (int i = 0; i < arr.length; i++) { //Einträge werden kopiert
                oldarr[i] = arr[i];
            }

            //ArrayPair[] arr = new ArrayPair[2 * oldarr.length]; //Array mit doppelter Länge wird erstellt
            this.arr = new ArrayPair[2 * oldarr.length]; //Array mit doppelter Länge wird erstellt
            for (int i = 0; i < oldarr.length; i++) { //Einträge werden kopiert, damit hat neues Array mit doppelter Länge gleichen Namen wie ursprüngliches Array hat
                arr[i] = oldarr[i];
            }

            arr[oldarr.length] = new ArrayPair(key, value); //bei erstem Eintrag, der null ist (d.h. hinter "altem" Array), wird neues ArrayPair mit key und value erzeugt
        }
    }


    @Override
    public void remove(String key) {
        for (int i = 0; i < arr.length; i++) { //wenn key gefunden, wird Eintrag null gesetzt
            if (arr[i] != null) {  //bei Einträgen, die nicht null sind
                if (arr[i].getFirst().equals(key)) {
                    arr[i] = null;
                    break;
                }
            }
        }
    }

    @Override
    public int size() {
        int eintraege = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {//wenn Eintrag nicht null ist, wird Counter erhöht
                eintraege += 1;
            }
        }
        return eintraege; //gibt  Anzahl der Einträge zurück
    }

    @Override
    public void keys(String[] array) throws IllegalArgumentException {
        if (array == null) {
            throw new IllegalArgumentException("Bitte geben Sie ein passendes Array ein!");
        }
        if (array.length < size()) { //wenn mehr Einträge vorhanden als das gegebene Array lang ist oder Array=null ist
            throw new IllegalArgumentException("Bitte geben Sie ein passendes Array ein!");
        } else {
            int j = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != null) {  //alle Einträge des Arrays, die nicht null sind
                    array[j] = arr[i].getFirst(); //werden in das eingegebene Array geschrieben (ohne Lücken)
                    j += 1;
                }
            }
        }
    }
}

