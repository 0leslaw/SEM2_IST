import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        int MAX_POJEMNOSC = 1000;
        int PUNKT_P = 700;
        int PUNKT_R = 200;
        int ZAKRES_POSZUKIWAN = 5;
        int ILOSC_PROCESOROW = 50;
        int CZESC_PROCESOW_ZABIERANYCH = 3;
        int CZESTOSC_NOWYCH_PROCESOW = 80;


        Random random = new Random();
        ArrayList<Integer> kolejka = new ArrayList<>();
        ArrayList<Integer> kolejka2 = new ArrayList<>();
        int[] listaObciazen = {10,10,100,200,400};
//        int[] listaObciazen = {10,10,10,400,400};
        int temp;
        for(int i = 0;i<4000;i++) {
            temp = listaObciazen[random.nextInt(0, 4)];
            kolejka.add(temp);
            kolejka2.add(temp);
        }

        Zbior zbior = Algo.symulacjaZraz(MAX_POJEMNOSC,PUNKT_P,ZAKRES_POSZUKIWAN,kolejka,ILOSC_PROCESOROW,CZESTOSC_NOWYCH_PROCESOW);

        while (!kolejka.isEmpty())
            kolejka.remove(0);
        for (Integer integer:kolejka2)
            kolejka.add(integer);

        Zbior zbior1 = Algo.symulacjaObciazonyProbujeRozdac(MAX_POJEMNOSC,PUNKT_P,kolejka,ILOSC_PROCESOROW,CZESTOSC_NOWYCH_PROCESOW);

        while (!kolejka.isEmpty())
            kolejka.remove(0);
        for (Integer integer:kolejka2)
            kolejka.add(integer);

        Zbior zbior2 = Algo.symulacjaZrazZModyfikacja(
            MAX_POJEMNOSC,PUNKT_P,ZAKRES_POSZUKIWAN,PUNKT_R,CZESC_PROCESOW_ZABIERANYCH,kolejka2,ILOSC_PROCESOROW,CZESTOSC_NOWYCH_PROCESOW
        );
        ArrayList<Zbior> list = new ArrayList<>();
        list.add(zbior);
        list.add(zbior1);
        list.add(zbior2);
        new Start(list,MAX_POJEMNOSC);
    }
}
