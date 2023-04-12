package Algo;

import Procesy.Grupa_procesow;
import Procesy.Proces;

public class RR implements Algo_sposob_odbycia{
    @Override
    public void algorytm(int kwant, Grupa_procesow grupa_procesow,int kwant_na_zmiane) {
        int kwant_czasu_na_zmiane = kwant_na_zmiane;
        int index = (int) (Math.floor((double) (kwant-1)/(double) kwant_czasu_na_zmiane) % grupa_procesow.getLista_procesow().size());
        grupa_procesow.getLista_procesow().get(index).pomniejszCzasOnjednostekczasu(1);

        if(!grupa_procesow.getSuma_czasow_oczekiwania_zamknietych_operacji().isEmpty()) {


            if(grupa_procesow.getLista_procesow().get(index).isCzydokonany())
                grupa_procesow.getSuma_czasow_oczekiwania_zamknietych_operacji().add((
                        (grupa_procesow.getSuma_czasow_oczekiwania_zamknietych_operacji().get(grupa_procesow.getSuma_czasow_oczekiwania_zamknietych_operacji().size() - 1)
                                * grupa_procesow.getIlosc_przeszlych_procesow() + 1) / (grupa_procesow.getIlosc_przeszlych_procesow() + 1)));
            else grupa_procesow.getSuma_czasow_oczekiwania_zamknietych_operacji().add((
                    (grupa_procesow.getSuma_czasow_oczekiwania_zamknietych_operacji().get(grupa_procesow.getSuma_czasow_oczekiwania_zamknietych_operacji().size() - 1)
                            * grupa_procesow.getIlosc_przeszlych_procesow() + 1) / (grupa_procesow.getIlosc_przeszlych_procesow())));
        }
        if(grupa_procesow.getIlosc_przeszlych_procesow() == 0)
            if(grupa_procesow.getLista_procesow().get(index).isCzydokonany())
                grupa_procesow.getSuma_czasow_oczekiwania_zamknietych_operacji().add((double)kwant);
            else grupa_procesow.getSuma_czasow_oczekiwania_zamknietych_operacji().add((double)0);

        if(grupa_procesow.getLista_procesow().get(index).isCzydokonany()) {
            grupa_procesow.getLista_procesow().remove(index);
            grupa_procesow.incIlosc_przeszlych_procesow();
        }
    }


}
