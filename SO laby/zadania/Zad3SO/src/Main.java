import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> pagelist = new ArrayList<>();
        pagelist.add(7);
        pagelist.add(0);
        pagelist.add(1);
        pagelist.add(2);
        pagelist.add(0);
        pagelist.add(3);
        pagelist.add(0);
        pagelist.add(4);
        pagelist.add(2);
        pagelist.add(3);
        pagelist.add(0);
        pagelist.add(3);
        pagelist.add(2);
        pagelist.add(1);
        pagelist.add(2);


        Random random = new Random();
        for(int i=0;i<10;i++){
            pagelist.add(random.nextInt(1,20));
        }
        Algo.FIFO(pagelist,3);
        Algo.OPT(pagelist,3);
        Algo.LRU(pagelist,3);
        Algo.RAND(pagelist,3);
    }

}
