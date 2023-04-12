package com.company;

import java.util.Iterator;

public class Distincter {
    public static TwoWayLinkedList<Integer> distinct(TwoWayLinkedList<Integer> list)
    {
        // TODO: Zwróć nową listę zawierającą unikalne wartości w liście źródłowej.
        // Możesz założyć, że lista na wejściu jest posortowana.
        // Przykład: [1, 1, 2, 3, 3] -> [1, 2, 3]
        TwoWayLinkedList<Integer> unique_list = new TwoWayLinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        Integer temp;
        Integer previous = null;
        while (iterator.hasNext()) {
            temp = iterator.next();
            if(previous == null || temp != previous){
                unique_list.add(temp);
            }
            previous = temp;
        }
        return unique_list;
    }
}
