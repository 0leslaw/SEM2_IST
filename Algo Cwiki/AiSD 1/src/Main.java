import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
//        Student [] Tab = new Student[9];
//        int i = 0;
//        Tab[i] = new Student("olo","wosz",10,4);
//        i++;
//        Tab[i] = new Student("ole","woxz",30,4);
//        i++;
//        Tab[i] = new Student("ola","woz",20,3);
//        i++;
//        Tab[i] = new Student("oldo","wgoz",90,2);
//        i++;
//        Tab[i] = new Student("olgo","wooz",220,4);
//        i++;
//        Tab[i] = new Student("olfo","womz",230,2);
//        i++;
//        Tab[i] = new Student("olgo","wpoz",250,4);
//        i++;
//        Tab[i] = new Student("olbo","worz",205,5);
//        i++;
//        Tab[i] = new Student("olzo","woqz",210,4);
//
//        System.out.println("\nzad 1 a \n\n");
//        //a
//        Iterator<Student> inter0= new TablicyIterator<>(Tab);
//        while (inter0.hasNext()) {
//            Student temporary = inter0.next();
//            System.out.println(temporary);
//        }
//        System.out.println("\nzad 1 b \n\n");
//        // b
//        Iterator<Student> inter1= new TablicyIterator<>(Tab);
//
//        FilterIteratorodnrindeksu filterIteratorodnrindeksu = new FilterIteratorodnrindeksu(inter1);
//        while(filterIteratorodnrindeksu.hasNext()){
//            Student temp =filterIteratorodnrindeksu.next();
//            temp.setOcena(5);
//            System.out.println(temp);
//        }
//        System.out.println("\nzad 1 c \n\n");
//        // c
//        Iterator<Student> iter2= new TablicyIterator<>(Tab);
//        FIlterIteratorodOcenDobrych fIlterIteratorodOcenDobrych = new FIlterIteratorodOcenDobrych(iter2);
//
//        int suma_ocen=0;
//        int ilosc_ocen=0;
//        while (fIlterIteratorodOcenDobrych.hasNext()){
//            Student temp = fIlterIteratorodOcenDobrych.next();
//            suma_ocen += temp.getOcena();
//            ilosc_ocen++;
//        }
//        if(ilosc_ocen>0) {
//            float sr = (float) suma_ocen / (float)ilosc_ocen;
//            System.out.println(sr);
//        }
//        else System.out.println("Brak ocen pozytywnych");
//
//        System.out.println("\nzad 1 d \n\n");
//        //d
//        Iterator<Student> inter3 = new TablicyIterator<>(Tab);
//
//        FilterIteratorodNiezdajacych filterIteratorodNiezdajacych = new FilterIteratorodNiezdajacych(inter3);
//        while (filterIteratorodNiezdajacych.hasNext()){
//            Student temp = filterIteratorodNiezdajacych.next();
//            System.out.println(temp);
//        }
//
//        System.out.println("\nzad 2 \n\n");
//        //2
//        Iterator<Integer> integerIterator =new IteratorLiczbPierwszych(5,43);
//        while (integerIterator.hasNext()){
//            int temp = integerIterator.next();
//            System.out.println(temp);
//        }
//        Student temp = new Student("dobrze","woxz",30,4);
//        Student temp1 = new Student("zle","woxz",30,4);
//        Student pierwszy = temp;
//        pierwszy = temp1;
//        System.out.println(temp);
        ArrayList<Integer> sortowac = new ArrayList<>();
        sortowac.add(7);
        sortowac.add(3);
        sortowac.add(8);
        sortowac.add(0);
        sortowac.add(2);
        sortowac.add(1);
        sortowac.add(9);
        sortowac.add(3);
        QuickSort.QuickSort(sortowac,0,sortowac.size()-1);
        System.out.println(sortowac);
        Heap heap = new Heap(sortowac);
        System.out.println(heap.parentPos(1));
        System.out.println(heap.poll());
        System.out.println(heap.getHeapstructure());

    }

}
