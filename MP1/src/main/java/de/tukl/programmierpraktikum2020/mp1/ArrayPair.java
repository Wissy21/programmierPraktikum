package de.tukl.programmierpraktikum2020.mp1;

public class ArrayPair {
    private String first;
    private Integer second;



    ArrayPair(String key, Integer value) {
        this.first = key;
        this.second = value;
    }


    public String getFirst() {
        return first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setFirst(String key) { first = key; }

    public void setSecond(Integer value) { second =value; }


}
