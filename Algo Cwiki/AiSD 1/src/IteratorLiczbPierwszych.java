import java.util.Iterator;
import java.util.function.Predicate;

public class IteratorLiczbPierwszych implements Iterator<Integer> {

    private int pozycja;
    private int gran_gora;
    private boolean hasNextTo = true;
    private MPredykat predykat = new MPredykat();

    public IteratorLiczbPierwszych(int pozycja, int gran_gora) {
        if(pozycja<2)
            pozycja = 2;
        this.pozycja = pozycja;
        this.gran_gora = gran_gora;
        if(!predykat.test(pozycja))
            znajdzNastepnaPierwsza();
    }

    @Override
    public boolean hasNext() {
        return hasNextTo;
    }
    public void znajdzNastepnaPierwsza(){
        while (pozycja<=gran_gora) {
            pozycja++;
            if (predykat.test(pozycja)) {
                return;
            }
        }
        hasNextTo = false;
        pozycja = -1;
    }

    @Override
    public Integer next() {
        if(pozycja<=gran_gora) {
            int temp = pozycja;
            znajdzNastepnaPierwsza();
            return temp;
        }else try {
            throw new Exception();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public class MPredykat implements Predicate<Integer>{

        @Override
        public boolean test(Integer integer) {
            boolean czyprzeszlo = true;
            for(int i =2;i<pozycja;i++)
                if (pozycja % i == 0) {
                    czyprzeszlo = false;
                    break;
                }

            return czyprzeszlo;
        }
    }
}
