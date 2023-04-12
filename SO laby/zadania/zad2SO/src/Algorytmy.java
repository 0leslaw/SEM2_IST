import java.util.ArrayList;

public class Algorytmy {
    public static int algorytmFCFS(ArrayList<Pierscien> piersciens,int poczatkowa_pozycja_glowicy){
        int laczna_dlugosc_szukania=0;
        int indeks_przerabiany_w_tablicy = poczatkowa_pozycja_glowicy;
        laczna_dlugosc_szukania += Math.abs(poczatkowa_pozycja_glowicy - piersciens.get(0).getKtory());

        for(Pierscien pierscien:piersciens){
            if(piersciens.lastIndexOf(pierscien)!=0) {
                laczna_dlugosc_szukania += Math.abs(pierscien.getKtory() - piersciens.get(piersciens.lastIndexOf(pierscien)-1).getKtory());
            }
        }
        System.out.println(laczna_dlugosc_szukania);
    return laczna_dlugosc_szukania;
    }
    public static int algorytmSCAN(Pierscienie pierscienie,int poczatkowa_pozycja_glowicy){
        int laczna_dlugosc_szukania=0;
        bubbleSort(pierscienie.getLista_pierscieni());
        laczna_dlugosc_szukania = pierscienie.getDlugosc()-poczatkowa_pozycja_glowicy;
        if(pierscienie.getLista_pierscieni().get(0).getKtory()<poczatkowa_pozycja_glowicy)
            laczna_dlugosc_szukania+= pierscienie.getDlugosc()-pierscienie.getLista_pierscieni().get(0).getKtory();

        System.out.println(laczna_dlugosc_szukania);
        return laczna_dlugosc_szukania;
    }
    public static int algorytmCSCAN(Pierscienie pierscienie,int poczatkowa_pozycja_glowicy,int dlugosc_przejscia_glowicy_na_pocz){
        int laczna_dlugosc_szukania=0;
        int indeks_przerabiany_w_tablicy = poczatkowa_pozycja_glowicy;
        bubbleSort(pierscienie.getLista_pierscieni());
        for(Pierscien pierscien: pierscienie.getLista_pierscieni())
            if(pierscien.getKtory()>= poczatkowa_pozycja_glowicy){
                indeks_przerabiany_w_tablicy = pierscienie.getLista_pierscieni().lastIndexOf(pierscien);
                break;
            }
        laczna_dlugosc_szukania = pierscienie.getDlugosc()-poczatkowa_pozycja_glowicy;
        laczna_dlugosc_szukania += dlugosc_przejscia_glowicy_na_pocz;
        if(indeks_przerabiany_w_tablicy>0)
            laczna_dlugosc_szukania += pierscienie.getLista_pierscieni().get(indeks_przerabiany_w_tablicy-1).getKtory();

        System.out.println(laczna_dlugosc_szukania);
        return laczna_dlugosc_szukania;
    }
    public static int algorytmSSTF(Pierscienie pierscienie,int poczatkowa_pozycja_glowicy){
        int laczna_dlugosc_szukania=0;
        int ostatnia_przerobiona_wartosc = poczatkowa_pozycja_glowicy;
        Pierscien temp = new Pierscien(1000000);
        bubbleSort(pierscienie.getLista_pierscieni());
    while (!pierscienie.getLista_pierscieni().isEmpty()) {
        temp = new Pierscien(1000000);
        for (Pierscien pierscien : pierscienie.getLista_pierscieni()) {
            if (Math.abs(pierscien.getKtory() - ostatnia_przerobiona_wartosc) < Math.abs(temp.getKtory() - ostatnia_przerobiona_wartosc)) {
                temp = pierscien;
            }
        }
        laczna_dlugosc_szukania += Math.abs(temp.getKtory() - ostatnia_przerobiona_wartosc);
        ostatnia_przerobiona_wartosc = temp.getKtory();
        pierscienie.getLista_pierscieni().remove(temp);
    }
        System.out.println(laczna_dlugosc_szukania);
        return laczna_dlugosc_szukania;
    }
    public static int algorytmEDF(Pierscienie pierscienie,int poczatkowa_pozycja_glowicy){
        int laczna_dlugosc_szukania=0;
        int ostatnia_przerobiona_wartosc = poczatkowa_pozycja_glowicy;
        Pierscien temp;
        bubbleSort(pierscienie.getLista_pierscieni());
        while (!pierscienie.getLista_pierscieni().isEmpty()) {
            temp = new Pierscien(2,100000000);
            for (Pierscien pierscien : pierscienie.getLista_pierscieni()) {
                if (pierscien.getDeadline() < temp.getDeadline()) {
                    temp = pierscien;
                }
            }
            laczna_dlugosc_szukania += Math.abs(temp.getKtory() - ostatnia_przerobiona_wartosc);
            ostatnia_przerobiona_wartosc = temp.getKtory();
            pierscienie.getLista_pierscieni().remove(temp);
        }
        System.out.println(laczna_dlugosc_szukania);
        return laczna_dlugosc_szukania;
    }
    public static int algorytmFDSCAN(Pierscienie pierscienie,int poczatkowa_pozycja_glowicy){
        int laczna_dlugosc_szukania=0;
        int ostatnia_przerobiona_wartosc = poczatkowa_pozycja_glowicy;
        Pierscien temp;
        bubbleSort(pierscienie.getLista_pierscieni());
        while (!pierscienie.getLista_pierscieni().isEmpty()) {
            temp = new Pierscien(2,100000000);
            for (Pierscien pierscien : pierscienie.getLista_pierscieni()) {
                if (pierscien.getDeadline() < temp.getDeadline() &&
                        Math.abs(pierscien.getKtory()- ostatnia_przerobiona_wartosc)+laczna_dlugosc_szukania<=pierscien.getDeadline() ) {
                    temp = pierscien;
                }if(!(Math.abs(pierscien.getKtory()- ostatnia_przerobiona_wartosc)+laczna_dlugosc_szukania<=pierscien.getDeadline())){
                    pierscien.setCzyZalatwion(true);
                }
            }
            for (int i= 0;i<pierscienie.getLista_pierscieni().size();i++) {
                if(pierscienie.getLista_pierscieni().get(i).isCzyZalatwion()){
                    pierscienie.getLista_pierscieni().remove(pierscienie.getLista_pierscieni().get(i));
                    i--;
                }
            }
            for (Pierscien pierscien : pierscienie.getLista_pierscieni()){
                if (pierscien.getKtory() > ostatnia_przerobiona_wartosc && pierscien.getKtory() < temp.getKtory() ||
                        pierscien.getKtory() < ostatnia_przerobiona_wartosc && pierscien.getKtory() > temp.getKtory()){
                    laczna_dlugosc_szukania += Math.abs(pierscien.getKtory() - ostatnia_przerobiona_wartosc);
                    ostatnia_przerobiona_wartosc = pierscien.getKtory();
                    pierscien.setCzyZalatwion(true);
                }
            }
            for (int i= 0;i<pierscienie.getLista_pierscieni().size();i++) {
                if(pierscienie.getLista_pierscieni().get(i).isCzyZalatwion()){
                    pierscienie.getLista_pierscieni().remove(pierscienie.getLista_pierscieni().get(i));
                    i--;
                }
            }
            if (pierscienie.getLista_pierscieni().isEmpty()) {
                System.out.println(laczna_dlugosc_szukania);
                return laczna_dlugosc_szukania;
            }
            laczna_dlugosc_szukania += Math.abs(temp.getKtory() - ostatnia_przerobiona_wartosc);
            ostatnia_przerobiona_wartosc = temp.getKtory();
            pierscienie.getLista_pierscieni().remove(temp);

        }
        System.out.println(laczna_dlugosc_szukania);
        return laczna_dlugosc_szukania;
    }
    public static void bubbleSort(ArrayList<Pierscien> to_be_sorted){

        Pierscien temp;

        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for(int index = 0;index<to_be_sorted.size()-1;index++){
                if(to_be_sorted.get(index).getKtory()>to_be_sorted.get(index+1).getKtory()){
                    temp = to_be_sorted.get(index);
                    to_be_sorted.set(index, to_be_sorted.get(index+1));
                    to_be_sorted.set(index+1,temp);
                    sorted = false;
                }
            }
        }
    }
}
