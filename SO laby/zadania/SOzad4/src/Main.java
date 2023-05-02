import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        int ILOSC_RAMEK = 300;
        int ILOSC_PROCESOW = 10;
        int ROZNORODNOSC = 150;
        int SREDNIA_ILOSC_STRON = 2000;
        int WSPOLNE_RAMKI = 200;
        double WSPOLCZYNNIK_BLEDOW = 0.9;
        double WSPOLCZYNNIK_BLEDOW_PRIORYTETU = 0.9;

        ArrayList<Proces> listaprocesow = new ArrayList<>();
        for(int i = 1; i<=ILOSC_PROCESOW;i++){
            listaprocesow.add(new Proces(i, random.nextInt(SREDNIA_ILOSC_STRON/100,SREDNIA_ILOSC_STRON*6), ROZNORODNOSC,-1));
        }
        Algo.przydzialRowny(ILOSC_RAMEK,listaprocesow,WSPOLNE_RAMKI,WSPOLCZYNNIK_BLEDOW);
        for(int i = 0; i<ILOSC_PROCESOW;i++){
            listaprocesow.get(i).wyzeruj();
        }
        Algo.przydzialProporcjonalny(ILOSC_RAMEK,listaprocesow,WSPOLNE_RAMKI,WSPOLCZYNNIK_BLEDOW);
        for(int i = 0; i<ILOSC_PROCESOW;i++){
            listaprocesow.get(i).wyzeruj();
        }
        Algo.przydzialPriorytetowyCZESTOSCBLEDOW(listaprocesow,WSPOLNE_RAMKI+ILOSC_RAMEK,WSPOLCZYNNIK_BLEDOW_PRIORYTETU);
        for(int i = 0; i<ILOSC_PROCESOW;i++){
            listaprocesow.get(i).wyzeruj();
        }
        Algo.przydzialStrefowy(listaprocesow,ILOSC_RAMEK,100);
    }
}
