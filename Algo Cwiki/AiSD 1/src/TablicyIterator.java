import java.util.Iterator;

public class TablicyIterator<T> implements Iterator<T> {
    private T [] tab;
    private int pozycja = 0;

    public TablicyIterator(T[] tab) {
        this.tab = tab;
    }

    @Override
    public boolean hasNext() {
        return pozycja<tab.length;
    }

    @Override
    public T next() {
        if(hasNext()) {
            T temp = tab[pozycja];
            pozycja++;
            return temp;
        }else throw new IndexOutOfBoundsException();
    }
}
