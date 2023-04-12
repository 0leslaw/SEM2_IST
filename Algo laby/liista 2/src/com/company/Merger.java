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
//        while (iterator1.hasNext()) {
//            Integer temp = iterator1.next();
//            returned.add(temp);
//        }
//        while (iterator2.hasNext()) {
//            Integer temp = iterator2.next();
//            returned.add(temp);
//        }
//        bubbleSort(returned);
//        return returned;
            //jak wezmie wszystkie z jednej to bierze dodaje reszte z drugiej
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
//    public static OneWayLinkedList<Integer> bubbleSort(OneWayLinkedList<Integer> to_be_sorted){
//        Iterator<Integer> iterator = to_be_sorted.iterator();
//        Integer temp;
//
//        boolean sorted = false;
//        while (!sorted) {
//          sorted = true;
//            for(int index = 0;index<to_be_sorted.size()-1;index++){
//                if(to_be_sorted.get(index)>to_be_sorted.get(index+1)){
//                    temp = to_be_sorted.get(index);
//                    to_be_sorted.set(index, to_be_sorted.get(index+1));
//                    to_be_sorted.set(index+1,temp);
//                    sorted = false;
//                }
//            }
//        }
//        return to_be_sorted;
//    }
}
