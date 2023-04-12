import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int srFCFS=0;
        int srSCAN=0;
        int srCSCAN=0;
        int srEDF=0;
        int srSSTF=0;
        int srFDSCAM=0;
        Pierscienie pierscienie ;
        Pierscienie pierscienie1;
        Pierscienie pierscienie2;
        int ilosc_testow=50;
        final int poczatkowa_pozycja_glowicy=50;
        for(int i =0;i<ilosc_testow;i++){
            System.out.println("test: "+i);
            pierscienie = new Pierscienie(100,7);
            pierscienie1 = new Pierscienie(100,3);
            pierscienie2 = new Pierscienie(100,3);
            klonuj(pierscienie.getLista_pierscieni(),pierscienie1.getLista_pierscieni());
            klonuj(pierscienie.getLista_pierscieni(),pierscienie2.getLista_pierscieni());
            System.out.println(pierscienie);
            srFCFS+=Algorytmy.algorytmFCFS(pierscienie.getLista_pierscieni(),poczatkowa_pozycja_glowicy);
            srSCAN+=Algorytmy.algorytmSCAN(pierscienie,poczatkowa_pozycja_glowicy);
            srCSCAN+=Algorytmy.algorytmCSCAN(pierscienie,poczatkowa_pozycja_glowicy,2);
            srSSTF+=Algorytmy.algorytmSSTF(pierscienie,poczatkowa_pozycja_glowicy);
            srEDF+=Algorytmy.algorytmEDF(pierscienie1,poczatkowa_pozycja_glowicy);
            srFDSCAM+=Algorytmy.algorytmFDSCAN(pierscienie2,poczatkowa_pozycja_glowicy);
        }
        System.out.println("\nSR FCFS: "+srFCFS/ilosc_testow);
        System.out.println("SR SCAN: "+srSCAN/ilosc_testow);
        System.out.println("SR CSCAN: "+srCSCAN/ilosc_testow);
        System.out.println("SR SSTF: "+srSSTF/ilosc_testow);
        System.out.println("SR EDF: "+srEDF/ilosc_testow);
        System.out.println("SR FDSCAN: "+srFDSCAM/ilosc_testow);

        Pierscienie pierscienie3test = new Pierscienie(2,0);
//        pierscienie3test.getLista_pierscieni().add(new Pierscien(10,100));
//        Algorytmy.algorytmFDSCAN(pierscienie3test,50);
//
    }
    public static void klonuj(ArrayList<Pierscien> dosklonowania,ArrayList<Pierscien> klonowana){
        klonowana.removeAll(klonowana);
        for (Pierscien pierscien:dosklonowania){
            klonowana.add(new Pierscien(pierscien.getKtory(), pierscien.getDeadline()));
        }
    }
}
