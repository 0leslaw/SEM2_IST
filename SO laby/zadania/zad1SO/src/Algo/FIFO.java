package Algo;

import Procesy.Grupa_procesow;

public class FIFO implements Algo_sposob_odbycia{
    @Override
    public void algorytm(int kwant, Grupa_procesow grupa_procesow,int kwant_na_zmiane) {
        grupa_procesow.getLista_procesow().get(0).pomniejszCzasOnjednostekczasu(1);

        if(grupa_procesow.getLista_procesow().get(0).isCzydokonany()) {
            grupa_procesow.getLista_procesow().remove(0);
            grupa_procesow.incIlosc_przeszlych_procesow();
        }
    }
}
