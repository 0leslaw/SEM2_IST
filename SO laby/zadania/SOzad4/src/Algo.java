import java.util.ArrayList;
import java.util.HashSet;

public class Algo {
    public static void wydrukWynikow(ArrayList<Proces> processlist){
        int lacznebledy = 0;

        for(int i = 0;i< processlist.size();i++) {
            System.out.printf("%-30.30s  %-30.30s %-30.30s%n", "Proces " + (i + 1)+" bledy: "+processlist.get(i).getBledy(), " ilosc ramek: "
                    +processlist.get(i).getRAM().size()," ilosc stron: "+processlist.get(i).getPosiadane_strony().size());

            lacznebledy += processlist.get(i).getBledy();
        }
        System.out.println("ŁĄCZNE BŁĘDY: "+lacznebledy);
    }
    public static int znajdzMax(ArrayList<Proces> processlist){
        int max = 0;
        for(Proces proces:processlist)
            if(proces.getPosiadane_strony().size()>max)
                max = proces.getPosiadane_strony().size();
        return max;
    }
    public static void przydzialRowny(int ILOSC_RAMEK, ArrayList<Proces> processlist,int WSPOLNE,double WSPOLCZYNNIK_BLEDOW){
        //wspolczynnik bledow mowi jaką czescia wiekszej ilosci bledow musi byc mniejsza zeby nastapilo oddanie ramki
        System.out.println("\nPRZYDZIAŁ RÓWNY: \n");
        for (Proces proces:
             processlist) {
            proces.setRAM_LENGTH(ILOSC_RAMEK/ processlist.size());
            proces.setIlosc_wspolnych(WSPOLNE/ processlist.size());
            proces.ustawRam(proces.getIlosc_wspolnych()+ proces.getRAM_LENGTH());
        }
        int max = znajdzMax(processlist);
        for(int j = 0;j<max-1;j++){
            //dostosowanie przydzialu ramek
            for (Proces proces:
                    processlist)
                for (Proces proces1:
                    processlist) {
                    if (proces.getBledy() < WSPOLCZYNNIK_BLEDOW * proces1.getBledy() && proces.getIlosc_wspolnych()>1){
                        proces1.zwiekszWspolnyRam();
                        proces.zmniejszWspolnyRam();
                        if(j % 100 == 1){
                            System.out.println("Co 100 iteracji , wystapila zmiana:\n" +
                                    " RAM zwiększonego: "+proces1.getIlosc_wspolnych()+"\n RAM zmniejszonego: "+proces.getIlosc_wspolnych());
                        }
                    }
                }
            //iteracja LRU dla wszystkich procesow
            for (Proces proces:
                    processlist){
                if(iteracjaLRU(proces.getPosiadane_strony(),proces.getRAM(),j))
                    proces.setBledy(proces.getBledy()+1);
            }
        }
        wydrukWynikow(processlist);
    }

    public static void przydzialProporcjonalny(int ILOSC_RAMEK, ArrayList<Proces> processlist,int WSPOLNE,double WSPOLCZYNNIK_BLEDOW){
        //wspolczynnik bledow mowi jaką czescia wiekszej ilosci bledow musi byc mniejsza zeby nastapilo oddanie ramki
        System.out.println("\nPRZYDZIAŁ PROPORCJONALNY: \n");

        int laczna_ilosc_stron=0;
        for (Proces proces:
                processlist)
            laczna_ilosc_stron += proces.getPosiadane_strony().size();
        int ilosc_rozdanych_ramek = 0;
        for (Proces proces:
                processlist) {
            double temp = (double)proces.getPosiadane_strony().size()/(double)laczna_ilosc_stron;
            ilosc_rozdanych_ramek += Math.max(ILOSC_RAMEK * temp,1);
            proces.setRAM_LENGTH((int) Math.max((ILOSC_RAMEK * temp),1));
            proces.setIlosc_wspolnych(WSPOLNE /processlist.size());
            proces.ustawRam(proces.getIlosc_wspolnych()+ proces.getRAM_LENGTH());
        }
        processlist.get(0).setRAM_LENGTH(Math.max(processlist.get(0).getRAM_LENGTH()+ILOSC_RAMEK-ilosc_rozdanych_ramek,1));
        processlist.get(0).ustawRam(Math.max(processlist.get(0).getIlosc_wspolnych()+ processlist.get(0).getRAM_LENGTH(),1));

        int max = znajdzMax(processlist);
        for(int j = 0;j<2*max-1;j++){
            //dostosowanie przydzialu ramek
            for (Proces proces1:
                    processlist)
                for (Proces proces:
                        processlist) {
                    if (proces.getBledy() < WSPOLCZYNNIK_BLEDOW * proces1.getBledy() && proces.getIlosc_wspolnych()>0){
                        proces1.zwiekszWspolnyRam();
                        proces.zmniejszWspolnyRam();
                    }
                }
            //iteracja LRU dla wszystkich procesow
            for (Proces proces:
                    processlist){
                if(iteracjaLRU(proces.getPosiadane_strony(),proces.getRAM(),j))
                    proces.setBledy(proces.getBledy()+1);
            }
        }
        wydrukWynikow(processlist);
    }
    public static void przydzialPriorytetowyCZESTOSCBLEDOW(ArrayList<Proces> processlist,int WSPOLNE,double WSPOLCZYNNIK_BLEDOW){
        //wspolczynnik bledow mowi jaką czescia wiekszej ilosci bledow musi byc mniejsza zeby nastapilo oddanie ramki
        System.out.println("\nPRZYDZIAŁ PRIORYTETOWY (STEROWANIE CZĘSTOŚCIĄ BŁĘDÓW): \n");
        for (Proces proces:
                processlist) {
            proces.setRAM_LENGTH(0);
            proces.setIlosc_wspolnych(WSPOLNE/ processlist.size());
            proces.ustawRam(proces.getIlosc_wspolnych()+ proces.getRAM_LENGTH());
        }
        int max = znajdzMax(processlist);
        for(int j = 0;j<2*max-1;j++){
            //dostosowanie przydzialu ramek
            for (Proces proces:
                    processlist)
                for (Proces proces1:
                        processlist) {
                    if (proces.getBledy() < WSPOLCZYNNIK_BLEDOW * proces1.getBledy() && proces.getIlosc_wspolnych()>10){
                        proces1.zwiekszWspolnyRam();
                        proces.zmniejszWspolnyRam();
                        if(j % 100 == 1){
                            System.out.println("Co 500 iteracji , wystapila zmiana:\n" +
                                    " RAM zwiększonego - "+proces1.getIlosc_wspolnych()+"\n RAM zmniejszonego: "+proces.getIlosc_wspolnych());
                        }
                    }
                }
            //iteracja LRU dla wszystkich procesow
            for (Proces proces:
                    processlist){
                if(iteracjaLRU(proces.getPosiadane_strony(),proces.getRAM(),j))
                    proces.setBledy(proces.getBledy()+1);
            }

        }
        wydrukWynikow(processlist);
    }

    public static void przydzialStrefowy(ArrayList<Proces> processlist,int ILOSC_RAMEK,int ILOSC_KWANTOW_CZASU){
        //nie da sie przy kazdym odwolaniu do pamieci wyznaczac na nowo zbioru roboczego

        System.out.println("\nPRZYDZIAŁ STREFOWY: \n");
        for (Proces proces:
                processlist) {
            proces.setRAM_LENGTH(ILOSC_RAMEK/ processlist.size());
            proces.setIlosc_wspolnych(0);
            proces.ustawRam(proces.getIlosc_wspolnych()+ proces.getRAM_LENGTH());
        }
        int max =znajdzMax(processlist);
        int ilosc_zajetych_ramek =0;
        HashSet<Integer> hashSetUZYWANYCHSTRON = new HashSet<>();
        int max_dlugosc_razy_ilosc_procesow = processlist.size()*max-1;
        int ilosc_wylaczonych_procesow=0;

        for(int licznik_kwantow = 0;licznik_kwantow< max_dlugosc_razy_ilosc_procesow;licznik_kwantow++) {
            //wyznaczenie zbiorow roboczych

            if(licznik_kwantow % ILOSC_KWANTOW_CZASU == 0 && licznik_kwantow- ILOSC_KWANTOW_CZASU >= 0)
                for (Proces proces :
                        processlist)
                {

                        if (proces.getKtoraIteracja() < proces.getPosiadane_strony().size())
                        {
                            for (int i = proces.getKtoraIteracja() - ILOSC_KWANTOW_CZASU; i < proces.getKtoraIteracja(); i++)
                            {
                                hashSetUZYWANYCHSTRON.add(proces.getPosiadane_strony().get(i));
                            }
                            ilosc_zajetych_ramek += hashSetUZYWANYCHSTRON.size();
                            if (ILOSC_RAMEK - ilosc_zajetych_ramek >= 0)
                            {
                                proces.ustawRam(hashSetUZYWANYCHSTRON.size());
                            }
                            else
                            {
                                proces.ustawRam(0);
                                proces.setRAM_LENGTH(-1);
                                ilosc_wylaczonych_procesow++;
                            }
                        }
                        else
                        {
                            proces.ustawRam(0);
                            proces.setRAM_LENGTH(-1);
                        }
                        hashSetUZYWANYCHSTRON.clear();

                }
            ilosc_zajetych_ramek = 0;

            if(licznik_kwantow % ILOSC_KWANTOW_CZASU == 0 && licznik_kwantow<=1000)
            {
                System.out.println("Co JEDNĄ ILOŚĆ KWANTOW podaje ilość wyłączonych procesów: "+ilosc_wylaczonych_procesow);
            }
            ilosc_wylaczonych_procesow = 0;

            //iteracja LRU dla wszystkich procesow
            for (Proces proces :
                    processlist) {
                if(proces.getRAM_LENGTH() != -1) {
                    if (iteracjaLRU(proces.getPosiadane_strony(), proces.getRAM(), proces.getKtoraIteracja())) {
                        proces.setBledy(proces.getBledy() + 1);
                    }
                    //przez to ze ta linijka byla w gornym ifie nie dzialal program
                    proces.setKtoraIteracja(proces.getKtoraIteracja()+1);
                }
            }
        }
        wydrukWynikow(processlist);
    }
    public static boolean iteracjaLRU(ArrayList<Integer> pagelist, ArrayList<Integer> RAM, int licznik_kwantow){
        boolean isfault = false;
        if(licznik_kwantow<pagelist.size()) {
            isfault = true;
            for (int i = 0; i < RAM.size(); i++) {
                if (pagelist.get(licznik_kwantow) == RAM.get(i)) {
                    isfault = false;
                }
            }
            if (isfault)
            {
                    ArrayList<Integer> temp = new ArrayList<>();
                    for (Integer ram : RAM)
                        temp.add(ram);
                    for (int k = licznik_kwantow - 1; k > -1; k--) {
                        for (int z = 0; z < temp.size(); z++)
                            if (temp.get(z) == pagelist.get(k)) {
                                temp.remove(z);
                                break;
                            }
                        if (temp.size() == 1)
                            break;
                    }
                    RAM.set(RAM.lastIndexOf(temp.get(0)), pagelist.get(licznik_kwantow));
            }
        }
        return isfault;
    }
}
