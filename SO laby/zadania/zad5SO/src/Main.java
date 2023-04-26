import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Integer> kolejka = new ArrayList<>();
        int[] listaObciazen = {10,20,30,40,60};
        for(int i = 0;i<2000;i++)
            kolejka.add(listaObciazen[random.nextInt(0,4)]);
        Zbior zbior = Algo.symulacjaZraz(500,5,kolejka,50);
        ArrayList<Zbior> list = new ArrayList<>();
        list.add(zbior);
//        new Start(list);
    }
}
