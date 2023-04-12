import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Pierscienie {
    private int dlugosc;
    private ArrayList<Pierscien> lista_pierscieni = new ArrayList<>();

    public Pierscienie(int dlugosc,int ile_do_wykonania) {
        this.dlugosc = dlugosc;
        Random random = new Random();
        boolean czyinna = false;
        int temp = random.nextInt(1, dlugosc + 1);
//        lista_pierscieni.add(new Pierscien(temp));
        for(int i = 0;i<ile_do_wykonania;i++) {
            czyinna = false;
            while (!czyinna) {
                czyinna = true;
                temp = random.nextInt(1, dlugosc + 1);
                for (Pierscien pierscien : lista_pierscieni) {
                    if (pierscien.getKtory() == temp)
                        czyinna = false;
                }
            }lista_pierscieni.add(new Pierscien(temp));
        }
    }

    public ArrayList<Pierscien> getLista_pierscieni() {
        return lista_pierscieni;
    }

    public void setLista_pierscieni(ArrayList<Pierscien> lista_pierscieni) {
        this.lista_pierscieni = lista_pierscieni;
    }
    public void dodajDoListy(Pierscien pierscien){
        lista_pierscieni.add(pierscien);
    }

    public int getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(int dlugosc) {
        this.dlugosc = dlugosc;
    }

    @Override
    public String toString() {
        return "Pierscienie{" +
                "lista_pierscieni=" + lista_pierscieni +
                '}';
    }

}
