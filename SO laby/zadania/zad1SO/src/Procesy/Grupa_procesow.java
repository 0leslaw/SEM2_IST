package Procesy;

import Algo.Algo_sposob_odbycia;

import java.util.ArrayList;

public class Grupa_procesow {
    private ArrayList<Proces> lista_procesow = new ArrayList<>();
    private Algo_sposob_odbycia algo_sposob_odbycia;
    private boolean czypos = false;
    private int ilosc_przeszlych_procesow=0;
    private ArrayList<Double> srednie_czasy_zamkniecia_operacji = new ArrayList<>();
    private ArrayList<Double> suma_czasow_oczekiwania_zamknietych_operacji = new ArrayList<>();

    public Grupa_procesow(ArrayList<Proces> lista_procesow, Algo_sposob_odbycia algo_sposob_odbycia) {
        this.lista_procesow = lista_procesow;
        this.algo_sposob_odbycia = algo_sposob_odbycia;
        this.suma_czasow_oczekiwania_zamknietych_operacji.add((double)0);
    }

    public void wykonajKrokAlgo(int Kwant,int k_na_z){
        algo_sposob_odbycia.algorytm(Kwant,this,k_na_z);
    }


    public ArrayList<Proces> getLista_procesow() {
        return lista_procesow;
    }

    public void setLista_procesow(ArrayList<Proces> lista_procesow) {
        this.lista_procesow = lista_procesow;
    }

    public Algo_sposob_odbycia getAlgo_sposob_odbycia() {
        return algo_sposob_odbycia;
    }

    public void setAlgo_sposob_odbycia(Algo_sposob_odbycia algo_sposob_odbycia) {
        this.algo_sposob_odbycia = algo_sposob_odbycia;
    }

    public boolean isCzypos() {
        return czypos;
    }

    public void setCzypos(boolean czypos) {
        this.czypos = czypos;
    }

    public int getIlosc_przeszlych_procesow() {
        return ilosc_przeszlych_procesow;
    }

    public void setIlosc_przeszlych_procesow(int ilosc_przeszlych_procesow) {
        this.ilosc_przeszlych_procesow = ilosc_przeszlych_procesow;
    }
    public void incIlosc_przeszlych_procesow() {
        this.ilosc_przeszlych_procesow++;
    }

    public ArrayList<Double> getSrednie_czasy_zamkniecia_operacji() {
        return srednie_czasy_zamkniecia_operacji;
    }

    public void setSrednie_czasy_zamkniecia_operacji(ArrayList<Double> srednie_czasy_zamkniecia_operacji) {
        this.srednie_czasy_zamkniecia_operacji = srednie_czasy_zamkniecia_operacji;
    }

    public ArrayList<Double> getSuma_czasow_oczekiwania_zamknietych_operacji() {
        return suma_czasow_oczekiwania_zamknietych_operacji;
    }

    public void setSuma_czasow_oczekiwania_zamknietych_operacji(ArrayList<Double> suma_czasow_oczekiwania_zamknietych_operacji) {
        this.suma_czasow_oczekiwania_zamknietych_operacji = suma_czasow_oczekiwania_zamknietych_operacji;
    }
}
