package com.company;

public class Main {
    public static void main(String[] args) {
        OneWayLinkedList<Integer> o = new OneWayLinkedList<>();
        OneWayLinkedList<Integer> o1 = new OneWayLinkedList<>();
        o.add(1);
        o.add(2);
        o.add(3);

        o1.add(2);
        o1.add(4);
        o1.add(8);
        System.out.println(Merger.merge(o,o1));

    }
}
