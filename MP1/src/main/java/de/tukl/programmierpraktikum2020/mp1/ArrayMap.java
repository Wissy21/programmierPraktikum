package de.tukl.programmierpraktikum2020.mp1;

public class ArrayMap implements Map<String, Integer> {

    ArrayPair arr[] = new ArrayPair[100];

    @Override
    public Integer get(String key) {
        for (int i = 0; i < arr.length; i++) { //wenn key gefunden wird, wird value ausgegeben
            if (arr[i] != null) {
                if (arr[i].getFirst().equals(key)) {
                    int result;
                    result = arr[i].getSecond();
                    return result;
                }
            }
        }
        return null; //wenn nicht vorhanden = null;
    }

    @Override
    public void put(String key, Integer value) {
        boolean eingefuegt = false;
        for (int i = 0; i < arr.length; i++) { //wenn key gefunden, wird value neu gesetzt
            if (arr[i] != null) {
                if (arr[i].getFirst().equals(key)) {
                    remove(key);
                    arr[i] = new ArrayPair(key, value);
                    eingefuegt = true;
                    break;
                }
            }
        }
        if (!eingefuegt) {
            for (int i = 0; i < arr.length; i++) { //wenn key nicht gefunden, aber freie Einträge vorhanden, werden key und value gesetzt
                if (arr[i] == null) {
                    arr[i] = new ArrayPair(key, value);
                    eingefuegt = true;
                    break;
                }
            }
        }
        if (!eingefuegt) {
            //key nicht gefunden, keine freien Einträge -> Array muss neu erstellt und kopiert werden
            ArrayPair oldarr[] = new ArrayPair[arr.length]; //neues "altes" Array wird erstellt
            for (int i = 0; i < arr.length; i++) { //Einträge werden kopiert
                oldarr[i] = arr[i];
            }

            ArrayPair arr[] = new ArrayPair[2 * oldarr.length]; //Array mit doppelter Länge wird erstellt
            for (int i = 0; i < oldarr.length; i++) { //Einträge werden kopiert, damit hat neues Array mit doppelter Länge gleichen Namen wie ursprüngliches Array hat
                arr[i] = oldarr[i];
            }

            for (int i = oldarr.length; i < arr.length; i++) { //beginne bei oldarr.length, da Einträge davor bekanntermaßen NICHT null sind
                if (arr[i] != null) {
                    if (arr[i].getFirst() == null) {
                        arr[i].setFirst(key); //setze key
                        arr[i].setSecond(value); //setze value
                        break;
                    }
                }
            }
        }
        //  this.arr = arr; //damit auch außerhalb dieser Methode geändert
    }


    @Override
    public void remove(String key) {
        for (int i = 0; i < arr.length; i++) { //wenn key gefunden, werden beide Werte auf null gesetzt
            if (arr[i] != null) {
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
            if (arr[i] != null) {
                if (arr[i].getFirst() != null) { //wenn Eintrag nicht null ist, wird Counter erhöht
                    eintraege += 1;
                }
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
                if (arr[i] != null) {
                    //alle Einträge des Arrays, die nicht null sind
                    array[j] = arr[i].getFirst(); //werden in das eingegebene Array geschrieben (ohne Lücken)
                    j += 1;
                }
            }
        }
    }
}

