package com.company;

import java.util.Iterator;

public class Merger {
    public static OneWayLinkedList<Integer> merge(
            OneWayLinkedList<Integer> list1,
            OneWayLinkedList<Integer> list2) {
        // TODO
        OneWayLinkedList<Integer> returned = new OneWayLinkedList<>();
        Iterator<Integer> iterator1 = list1.iterator();
        Iterator<Integer> iterator2 = list2.iterator();

        if(list1 == null && list2 == null){
            return null;
        }else {
            Integer temp1 = iterator1.next();
            Integer temp2 = iterator2.next();
            do {
                if (temp2 != null && (temp1 == null || temp1 > temp2)) {
                    returned.add(temp2);
                    if (iterator2.hasNext())
                        temp2 = iterator2.next();
                    else
                        temp2 = null;
                } else {
                    returned.add(temp1);
                    if (iterator1.hasNext())
                        temp1 = iterator1.next();
                    else
                        temp1 = null;
                }
            } while (temp1 != null || temp2 != null);
            return returned;
        }
    }

}
