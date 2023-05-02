import java.util.ArrayList;

public class Procesor {
    private ArrayList<Integer> listaProcesow = new ArrayList<>();
    private ArrayList<Integer> obciazeniaWczasie = new ArrayList<>();
    private int obciazenie =0;
    private int stopien_wykonania_1_zad = 0;

    public Procesor(){
    }
    public int zwolnijProces(){
        int temp = listaProcesow.remove(0);
        obciazenie -= temp;
        return temp;
    }

    public void dodajDoObciazenia(int wartosc){
        obciazenie += wartosc;
    }
    public void dodajDoListyProcesow(int wartosc){
        listaProcesow.add(wartosc);
        obciazenie += wartosc;
    }

    public ArrayList<Integer> getListaProcesow() {
        return listaProcesow;
    }

    public void setListaProcesow(ArrayList<Integer> listaProcesow) {
        this.listaProcesow = listaProcesow;
    }

    public int getObciazenie() {
        return obciazenie;
    }

    public void setObciazenie(int obciazenie) {
        this.obciazenie = obciazenie;
    }

    public int getStopien_wykonania_1_zad() {
        return stopien_wykonania_1_zad;
    }
    public void inkrementujStopien_wykonania_1_zad() {
        stopien_wykonania_1_zad++;
    }

    public void setStopien_wykonania_1_zad(int stopien_wykonania_1_zad) {
        this.stopien_wykonania_1_zad = stopien_wykonania_1_zad;
    }

    public ArrayList<Integer> getObciazeniaWczasie() {
        return obciazeniaWczasie;
    }

    public void setObciazeniaWczasie(ArrayList<Integer> obciazeniaWczasie) {
        this.obciazeniaWczasie = obciazeniaWczasie;
    }
}
