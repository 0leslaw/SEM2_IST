import Procesy.Proces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
    Serializacja.zapisDowolnejArrayListy(zapelnicListe(30,30),"PlikListy.ser");
        new Start();
    }
    public static ArrayList<Proces> zapelnicListe(int ile, int bound_do_generatora){
        Random random = new Random();
        ArrayList<Proces> list = new ArrayList<>();
        while(ile > 0){
            list.add(new Proces(random.nextInt(1,bound_do_generatora)));
            ile--;
        }
        return list;
    }

}
