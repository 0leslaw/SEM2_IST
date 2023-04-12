import java.util.Iterator;
import java.util.function.Predicate;



public class FilterIteratorodnrindeksu implements Iterator<Student> {

    private Iterator<Student> iterator_wewnetrzny;
    private Predicate<Student> predykat = new PredykatnrIndeksu();
    private Student tymczasowa_zwrocona_wartosc;
    private boolean hasNext_mojego_iteratora = true;

    public FilterIteratorodnrindeksu(Iterator<Student> iterator_wewnetrzny) {
        super();
        this.iterator_wewnetrzny = iterator_wewnetrzny;
        znajdzNastepnaZgodnaWartosc();
    }

    private void znajdzNastepnaZgodnaWartosc() {
        while (iterator_wewnetrzny.hasNext()) {
            tymczasowa_zwrocona_wartosc = iterator_wewnetrzny.next();
            if (predykat.test(tymczasowa_zwrocona_wartosc)) {
                return;
            }
        }
        hasNext_mojego_iteratora=false;
        tymczasowa_zwrocona_wartosc=null;
    }


    @Override
    public boolean hasNext() {
        return hasNext_mojego_iteratora;
    }

    @Override
    public Student next() {
        Student temp = tymczasowa_zwrocona_wartosc;
        znajdzNastepnaZgodnaWartosc();
        return temp;
    }
    class PredykatnrIndeksu implements Predicate{

        @Override
        public boolean test(Object o) {
            if (!(o instanceof Student))
                return false;

            Student student = (Student) o;
            return student.getNr_indeksu() == 20 || student.getNr_indeksu() == 250;
        }
    }


}
