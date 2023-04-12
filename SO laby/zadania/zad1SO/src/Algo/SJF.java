package Algo;

import Procesy.Grupa_procesow;
import Procesy.Proces;

public class SJF implements Algo_sposob_odbycia{
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

            grupa_procesow.getLista_procesow().get(0).pomniejszCzasOnjednostekczasu(1);
            if(grupa_procesow.getLista_procesow().get(0).isCzydokonany()){
                grupa_procesow.getLista_procesow().remove(0);
                grupa_procesow.incIlosc_przeszlych_procesow();
            }

        }
    }

}
