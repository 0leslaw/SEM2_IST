import java.util.ArrayList;
import java.util.Random;

public class Proces {
    Random random = new Random();
    private ArrayList<Integer> posiadane_strony = new ArrayList<>();
    private int ktory;
    private int RAM_LENGTH;
    private int bledy =0;
    private int ilosc_wspolnych =0 ;
    private ArrayList<Integer> RAM = new ArrayList<>();
    private int ktoraIteracja = 0;
    public void wyzeruj(){
        bledy =0;
        ilosc_wspolnych=0;
        RAM_LENGTH =0;
        RAM.clear();
    }

    public Proces(ArrayList<Integer> posiadane_strony, int ktory,int RAM_LENGTH) {
        this.posiadane_strony = posiadane_strony;
        this.ktory = ktory;
        this.RAM_LENGTH = RAM_LENGTH;
    }
    public Proces(int ktory,int ilestron,int roznorodnosc,int RAM_LENGTH) {
        this.ktory = ktory;
        if(roznorodnosc> Integer.MAX_VALUE)
            throw new ExceptionInInitializerError();

        for(int i = 0;i<ilestron;i++){
            posiadane_strony.add(random.nextInt(1,roznorodnosc));
        }
        this.RAM_LENGTH = RAM_LENGTH;
    }
    public void zmniejszWspolnyRam(){
        RAM.remove(RAM.size()-1);
        ilosc_wspolnych--;
    }
    public void zwiekszWspolnyRam(){
        RAM.add(Integer.MAX_VALUE);
        ilosc_wspolnych++;
    }
    public void ustawRam(int DLUGOSC){
        while (RAM.size()>DLUGOSC)   RAM.remove(RAM.size()-1);

        while (RAM.size()<DLUGOSC)   RAM.add(Integer.MAX_VALUE);
    }

    public ArrayList<Integer> getPosiadane_strony() {
        return posiadane_strony;
    }

    public void setPosiadane_strony(ArrayList<Integer> posiadane_strony) {
        this.posiadane_strony = posiadane_strony;
    }

    public int getKtory() {
        return ktory;
    }

    public void setKtory(int ktory) {
        this.ktory = ktory;
    }

    public int getRAM_LENGTH() {
        return RAM_LENGTH;
    }

    public void setRAM_LENGTH(int RAM_LENGTH) {
        this.RAM_LENGTH = RAM_LENGTH;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getBledy() {
        return bledy;
    }

    public void setBledy(int bledy) {
        this.bledy = bledy;
    }

    public int getIlosc_wspolnych() {
        return ilosc_wspolnych;
    }

    public void setIlosc_wspolnych(int ilosc_wspolnych) {
        this.ilosc_wspolnych = ilosc_wspolnych;
    }

    public ArrayList<Integer> getRAM() {
        return RAM;
    }

    public void setRAM(ArrayList<Integer> RAM) {
        this.RAM = RAM;
    }

    public int getKtoraIteracja() {
        return ktoraIteracja;
    }

    public void setKtoraIteracja(int ktoraIteracja) {
        this.ktoraIteracja = ktoraIteracja;
    }
}
