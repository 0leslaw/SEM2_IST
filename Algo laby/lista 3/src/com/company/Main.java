package com.company;

public class Main {
    public static void main(String[] args) {
        TwoWayLinkedList<Integer> l = new TwoWayLinkedList<>();

        l.add(1);
        l.add(1);
        l.add(2);
        l.add(2);
        l.add(3);
        TwoWayLinkedList<Integer> l2= Distincter.distinct(l);

    }
}
