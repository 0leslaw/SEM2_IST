import java.util.ArrayList;
import java.util.Random;

public class Algo {
    public static Zbior symulacjaZraz(int p ,int z, ArrayList<Integer> kolejkaZadan, int N){
        int coIleZbieramInfo = 10;
        int maxPojemnosc = 500;

        ArrayList<Integer> listaOdchylenSrednichObciazenWCzasie = new ArrayList<>();
        ArrayList<Integer> listaSrednichObciazenWCzasie = new ArrayList<>();
        ArrayList<Procesor> listaProcesorow = new ArrayList<>();
        for(int n = 0;n<N;n++)
            listaProcesorow.add(new Procesor());

        int iloscPrzemieszczen = 0;
        int iloscZapytanOObciazenie = 0;
        int srednieObciazenie = 0;

        Random random = new Random();
        int zwolniona_wartosc=0;
        for (int licznikKwantow = 0;kolejkaZadan.size()>N;licznikKwantow++){

            for (Procesor procesor:listaProcesorow){
                if(random.nextInt(0,10) == 0) {
                    zwolniona_wartosc = kolejkaZadan.get(0);

                    int temp;
                    boolean czyPrzejeto = false;
                    for(int n = 0;n<z;n++){
                        temp = random.nextInt(0,N);
                        if(listaProcesorow.get(temp).getObciazenie() <= p) {
                            listaProcesorow.get(temp).dodajDoListyProcesow(zwolniona_wartosc);
                            kolejkaZadan.remove(0);
                            iloscZapytanOObciazenie+=n+1;
                            iloscPrzemieszczen++;
                            czyPrzejeto = true;
                        }
                    }
                    if (!czyPrzejeto && procesor.getObciazenie() + zwolniona_wartosc < maxPojemnosc) {
                        procesor.dodajDoListyProcesow(zwolniona_wartosc);
                        kolejkaZadan.remove(0);
                    }
                }

                if(!procesor.getListaProcesow().isEmpty() &&
                        procesor.getStopien_wykonania_1_zad() >= procesor.getListaProcesow().get(0)) {
                    procesor.getListaProcesow().remove(0);
                    procesor.setStopien_wykonania_1_zad(0);
                }else procesor.inkrementujStopien_wykonania_1_zad();
                //overflow sterty
                /*if(licznikKwantow % coIleZbieramInfo ==0)
                    procesor.getObciazeniaWczasie().add(procesor.getObciazenie());*/
                srednieObciazenie+= procesor.getObciazenie();
            }
            srednieObciazenie = srednieObciazenie/N;
            if(licznikKwantow % coIleZbieramInfo ==0)
                listaSrednichObciazenWCzasie.add(srednieObciazenie);
        }
        //obliczenie odchylenia
//        double odchylenieStandardowe = 0;
//        for(int i = 0;i<listaProcesorow.get(0).getObciazeniaWczasie().size();i++){
//            for(Procesor procesor:listaProcesorow){
//                odchylenieStandardowe+=Math.pow(procesor.getObciazeniaWczasie().get(i)-listaSrednichObciazenWCzasie.get(i),2);
//            }
//            odchylenieStandardowe/=(N-1);
//            odchylenieStandardowe = Math.sqrt(odchylenieStandardowe);
//            listaOdchylenSrednichObciazenWCzasie.add((int)odchylenieStandardowe);
//        }
        return new Zbior(listaSrednichObciazenWCzasie,listaOdchylenSrednichObciazenWCzasie,iloscPrzemieszczen,iloscZapytanOObciazenie);
    }
}
