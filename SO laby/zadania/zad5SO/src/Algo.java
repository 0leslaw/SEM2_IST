import java.util.ArrayList;
import java.util.Random;

public class Algo {
    public static Zbior symulacjaZraz(int maxpojemnosc, int p ,int z, ArrayList<Integer> kolejkaZadan, int N,int gornybounrandoma){
        int coIleZbieramInfo = 15;
        int maxPojemnosc = maxpojemnosc;
        int dlugoscpoczkol = kolejkaZadan.size();

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
        int licznikKwantow = 0;
        int gorny_bound_randoma = gornybounrandoma;
        while (kolejkaZadan.size()>N){
//            if (dlugoscpoczkol/2 >kolejkaZadan.size())
//                gorny_bound_randoma = 40;
            for (Procesor procesor:listaProcesorow){
                int randomtemp = random.nextInt(0,gorny_bound_randoma);
                if(randomtemp == 2) {
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
                            break;
                        }
                    }
                    if (!czyPrzejeto && procesor.getObciazenie() + zwolniona_wartosc < maxPojemnosc) {
                        procesor.dodajDoListyProcesow(zwolniona_wartosc);
                        kolejkaZadan.remove(0);
                    }
                }

                if(!procesor.getListaProcesow().isEmpty() &&
                        procesor.getStopien_wykonania_1_zad() >= procesor.getListaProcesow().get(0)) {
                    procesor.zwolnijProces();
                    procesor.setStopien_wykonania_1_zad(0);
                }else if(!procesor.getListaProcesow().isEmpty()) procesor.inkrementujStopien_wykonania_1_zad();
                //overflow sterty
                if(licznikKwantow % coIleZbieramInfo ==0)
                    procesor.getObciazeniaWczasie().add(procesor.getObciazenie());
                srednieObciazenie+= procesor.getObciazenie();
            }
            srednieObciazenie = srednieObciazenie/N;
            if(licznikKwantow % coIleZbieramInfo ==0) {
                listaSrednichObciazenWCzasie.add(srednieObciazenie);
                licznikKwantow = 0;
            }

            licznikKwantow++;
        }
//        obliczenie odchylenia
        double odchylenieStandardowe = 0;
        for(int i = 0;i<listaProcesorow.get(0).getObciazeniaWczasie().size();i++){
            for(Procesor procesor:listaProcesorow){
                odchylenieStandardowe+=Math.pow(procesor.getObciazeniaWczasie().get(i)-listaSrednichObciazenWCzasie.get(i),2);
            }
            odchylenieStandardowe/=(N-1);
            odchylenieStandardowe = Math.sqrt(odchylenieStandardowe);
            listaOdchylenSrednichObciazenWCzasie.add((int)odchylenieStandardowe);
        }
        return new Zbior(listaSrednichObciazenWCzasie,listaOdchylenSrednichObciazenWCzasie,iloscPrzemieszczen,iloscZapytanOObciazenie);
    }
    public static Zbior symulacjaObciazonyProbujeRozdac(int maxpojemnosc,int p ,ArrayList<Integer> kolejkaZadan, int N,int gornybounrandoma){
        int coIleZbieramInfo = 15;
        int maxPojemnosc = maxpojemnosc;

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
        int licznikKwantow = 0;
        int gorny_bound_randoma = gornybounrandoma;
        while (kolejkaZadan.size()>N) {

            for (Procesor procesor : listaProcesorow) {
                int randomtemp = random.nextInt(0, gorny_bound_randoma);
                if (randomtemp == 2) {
                    zwolniona_wartosc = kolejkaZadan.get(0);

                    int temp;
                    boolean czyPrzejeto = false;
                    if(procesor.getObciazenie() > p) {
                        for (int n = 0; n < N; n++) {
                            if (listaProcesorow.get(n).getObciazenie() <= p) {
                                listaProcesorow.get(n).dodajDoListyProcesow(zwolniona_wartosc);
                                kolejkaZadan.remove(0);
                                iloscZapytanOObciazenie += n + 1;
                                iloscPrzemieszczen++;
                                czyPrzejeto = true;
                                break;
                            }
                        }
                    }
                    if (!czyPrzejeto && procesor.getObciazenie() + zwolniona_wartosc < maxPojemnosc) {
                        procesor.dodajDoListyProcesow(zwolniona_wartosc);
                        kolejkaZadan.remove(0);
                    }
                }

                if (!procesor.getListaProcesow().isEmpty() &&
                        procesor.getStopien_wykonania_1_zad() >= procesor.getListaProcesow().get(0)) {
                    procesor.zwolnijProces();
                    procesor.setStopien_wykonania_1_zad(0);
                } else if (!procesor.getListaProcesow().isEmpty()) procesor.inkrementujStopien_wykonania_1_zad();
                //overflow sterty
                if(licznikKwantow % coIleZbieramInfo ==0)
                    procesor.getObciazeniaWczasie().add(procesor.getObciazenie());
                srednieObciazenie += procesor.getObciazenie();
            }
            srednieObciazenie = srednieObciazenie / N;
            if (licznikKwantow % coIleZbieramInfo == 0) {
                listaSrednichObciazenWCzasie.add(srednieObciazenie);
                licznikKwantow = 0;
            }

            licznikKwantow++;
        }
        double odchylenieStandardowe = 0;
        for(int i = 0;i<listaProcesorow.get(0).getObciazeniaWczasie().size();i++){
            for(Procesor procesor:listaProcesorow){
                odchylenieStandardowe+=Math.pow(procesor.getObciazeniaWczasie().get(i)-listaSrednichObciazenWCzasie.get(i),2);
            }
            odchylenieStandardowe/=(N-1);
            odchylenieStandardowe = Math.sqrt(odchylenieStandardowe);
            listaOdchylenSrednichObciazenWCzasie.add((int)odchylenieStandardowe);
        }
        return new Zbior(listaSrednichObciazenWCzasie,listaOdchylenSrednichObciazenWCzasie,iloscPrzemieszczen,iloscZapytanOObciazenie);
    }
    public static Zbior symulacjaZrazZModyfikacja(int maxpojemnosc, int p ,int z,int r,int czescprocesowzabier, ArrayList<Integer> kolejkaZadan, int N, int gornybounrandoma){
        int coIleZbieramInfo = 15;
        int maxPojemnosc = maxpojemnosc;
        int dlugoscpoczkol = kolejkaZadan.size();

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
        int licznikKwantow = 0;
        int gorny_bound_randoma = gornybounrandoma;
        int temp_random;
        while (kolejkaZadan.size()>N){
//            if (dlugoscpoczkol/2 >kolejkaZadan.size())
//                gorny_bound_randoma = 40;
            for (Procesor procesor:listaProcesorow){
                if(procesor.getObciazenie() < r)
                    for(int n = 0;n<z;n++){
                        temp_random = random.nextInt(0,N);
                        if(listaProcesorow.get(temp_random).getObciazenie() > p) {
                            if(procesor.getObciazenie()+listaProcesorow.get(temp_random).getListaProcesow().get(0) < maxPojemnosc){
                                procesor.dodajDoListyProcesow(listaProcesorow.get(temp_random).zwolnijProces()-listaProcesorow.get(temp_random).getStopien_wykonania_1_zad());
                                listaProcesorow.get(temp_random).setStopien_wykonania_1_zad(0);
                            }

                            for(int i = 1;i<czescprocesowzabier && !listaProcesorow.get(temp_random).getListaProcesow().isEmpty() &&
                                    procesor.getObciazenie()+listaProcesorow.get(temp_random).getListaProcesow().get(
                                            0) < maxPojemnosc;i++) {
                                procesor.dodajDoListyProcesow(listaProcesorow.get(temp_random).zwolnijProces());
                            }
                            iloscZapytanOObciazenie+=n+1;
                            iloscPrzemieszczen+=czescprocesowzabier;
                            break;
                        }
                    }
                int randomtemp = random.nextInt(0,gorny_bound_randoma);
                if(randomtemp == 2) {
                    zwolniona_wartosc = kolejkaZadan.get(0);

                    boolean czyPrzejeto = false;
                    for(int n = 0;n<z;n++){
                        temp_random = random.nextInt(0,N);
                        if(listaProcesorow.get(temp_random).getObciazenie() <= p) {
                            listaProcesorow.get(temp_random).dodajDoListyProcesow(zwolniona_wartosc);
                            kolejkaZadan.remove(0);
                            iloscZapytanOObciazenie+=n+1;
                            iloscPrzemieszczen++;
                            czyPrzejeto = true;
                            break;
                        }
                    }
                    if (!czyPrzejeto && procesor.getObciazenie() + zwolniona_wartosc < maxPojemnosc) {
                        procesor.dodajDoListyProcesow(zwolniona_wartosc);
                        kolejkaZadan.remove(0);
                    }
                }

                if(!procesor.getListaProcesow().isEmpty() &&
                        procesor.getStopien_wykonania_1_zad() >= procesor.getListaProcesow().get(0)) {
                    procesor.zwolnijProces();
                    procesor.setStopien_wykonania_1_zad(0);
                }else if(!procesor.getListaProcesow().isEmpty()) procesor.inkrementujStopien_wykonania_1_zad();
                //overflow sterty
                if(licznikKwantow % coIleZbieramInfo ==0)
                    procesor.getObciazeniaWczasie().add(procesor.getObciazenie());
                srednieObciazenie+= procesor.getObciazenie();
            }
            srednieObciazenie = srednieObciazenie/N;
            if(licznikKwantow % coIleZbieramInfo ==0) {
                listaSrednichObciazenWCzasie.add(srednieObciazenie);
                licznikKwantow = 0;
            }

            licznikKwantow++;
        }
//        obliczenie odchylenia
        double odchylenieStandardowe = 0;
        for(int i = 0;i<listaProcesorow.get(0).getObciazeniaWczasie().size();i++){
            for(Procesor procesor:listaProcesorow){
                odchylenieStandardowe+=Math.pow(procesor.getObciazeniaWczasie().get(i)-listaSrednichObciazenWCzasie.get(i),2);
            }
            odchylenieStandardowe/=(N-1);
            odchylenieStandardowe = Math.sqrt(odchylenieStandardowe);
            listaOdchylenSrednichObciazenWCzasie.add((int)odchylenieStandardowe);
        }
        return new Zbior(listaSrednichObciazenWCzasie,listaOdchylenSrednichObciazenWCzasie,iloscPrzemieszczen,iloscZapytanOObciazenie);
    }
}
