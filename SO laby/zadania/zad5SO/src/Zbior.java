import java.util.ArrayList;

public class Zbior {
    private ArrayList<Integer> zbiorSrednichWCzasie;
    private ArrayList<Integer> zbiorSrednichOdchylenWCzasie;
    private int iloscZapytan;
    private int iloscMigracji;
    public Zbior(ArrayList<Integer> zbiorSrednichWCzasie,ArrayList<Integer> zbiorSrednichOdchylenWCzasie,int iloscMigracji,int iloscZapytan){
        this.zbiorSrednichWCzasie = zbiorSrednichWCzasie;
        this.zbiorSrednichOdchylenWCzasie = zbiorSrednichOdchylenWCzasie;
        this.iloscMigracji = iloscMigracji;
        this.iloscZapytan = iloscZapytan;
    }


    public ArrayList<Integer> getZbiorSrednichWCzasie() {
        return zbiorSrednichWCzasie;
    }

    public void setZbiorSrednichWCzasie(ArrayList<Integer> zbiorSrednichWCzasie) {
        this.zbiorSrednichWCzasie = zbiorSrednichWCzasie;
    }

    public ArrayList<Integer> getZbiorSrednichOdchylenWCzasie() {
        return zbiorSrednichOdchylenWCzasie;
    }

    public void setZbiorSrednichOdchylenWCzasie(ArrayList<Integer> zbiorSrednichOdchylenWCzasie) {
        this.zbiorSrednichOdchylenWCzasie = zbiorSrednichOdchylenWCzasie;
    }

    public int getIloscZapytan() {
        return iloscZapytan;
    }

    public void setIloscZapytan(int iloscZapytan) {
        this.iloscZapytan = iloscZapytan;
    }

    public int getIloscMigracji() {
        return iloscMigracji;
    }

    public void setIloscMigracji(int iloscMigracji) {
        this.iloscMigracji = iloscMigracji;
    }
}
