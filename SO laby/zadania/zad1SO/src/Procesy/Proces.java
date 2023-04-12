package Procesy;

import java.io.Serializable;

public class Proces implements Serializable {
    private int czas_pozostaly;
    private boolean czydokonany = false;

    public Proces(int czas_pozostaly) {
        this.czas_pozostaly = czas_pozostaly;
    }
    public void pomniejszCzasOnjednostekczasu(int ilosc_jednostek_czasu) {
        if(ilosc_jednostek_czasu >= czas_pozostaly){
            czas_pozostaly = 0;
            czydokonany = true;
        }else czas_pozostaly--;
    }

    public int getCzas_pozostaly() {
        return czas_pozostaly;
    }

    public void setCzas_pozostaly(int czas_pozostaly) {
        this.czas_pozostaly = czas_pozostaly;
    }

    public boolean isCzydokonany() {
        return czydokonany;
    }

    public void setCzydokonany(boolean czydokonany) {
        this.czydokonany = czydokonany;
    }
    public void decCzas_pozostaly(){
        czas_pozostaly--;
    }
}
