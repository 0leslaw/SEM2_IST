package Algo;

import Procesy.Grupa_procesow;
import Procesy.Proces;

public class wSJF implements Algo_sposob_odbycia{
    @Override
    public void algorytm(int kwant, Grupa_procesow grupa_procesow,int kwant_na_zmiane) {
        Proces temp = null;
        if (!grupa_procesow.isCzypos()) {
            grupa_procesow.setCzypos(true);
            for (int j = 1; j < grupa_procesow.getLista_procesow().size(); j++) {
                if (grupa_procesow.getLista_procesow().get(j - 1).getCzas_pozostaly() > grupa_procesow.getLista_procesow().get(j).getCzas_pozostaly()) {
                    //swap elements
                    temp = grupa_procesow.getLista_procesow().get(j - 1);
                    grupa_procesow.getLista_procesow().set(j - 1, grupa_procesow.getLista_procesow().get(j));
                    grupa_procesow.getLista_procesow().set(j, temp);
                    grupa_procesow.setCzypos(false);
                }

            }
        }else{
            int kwant_czasu_na_zmiane = kwant_na_zmiane;

            int index = (int) (Math.floor(((kwant-1)-grupa_procesow.getIlosc_przeszlych_procesow())/kwant_czasu_na_zmiane) % grupa_procesow.getLista_procesow().size());
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
}
