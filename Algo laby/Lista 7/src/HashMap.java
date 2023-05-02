import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class HashMap<TKey, TValue> {
    private LinkedList<Node>[] table;
    private Function<TKey, Integer> hashFunction;
    private int cellsTaken = 0;
    private double loadFactor;

    public class Node{
        TKey key;
        TValue value;
        public Node(TKey key, TValue value){
            this.key = key;
            this.value = value;
        }
    }
    public HashMap(int initialSize, double loadFactor, Function<TKey, Integer> hashFunction) {
        // TODO: Zainicjuj nową instancję klasy HashMap według podanych parametrów.
        //    InitialSize - początkowy rozmiar HashMap
        //    LoadFactor - stosunek elementów do rozmiaru HashMap po przekroczeniu którego należy podwoić rozmiar HashMap.
        //    HashFunction - funkcja, według której liczony jest hash klucza.
        //       Przykład użycia:   int hash = hashFunction.apply(key);
        table = new LinkedList[initialSize];
        this.loadFactor = loadFactor;
        this.hashFunction = hashFunction;
        for(int i=0;i<initialSize;i++){
            table[i] = new LinkedList<>();
        }

    }

    public void add(TKey key, TValue value) throws DuplicateKeyException {
        // TODO: Dodaj nową parę klucz-wartość. Rzuć wyjątek DuplicateKeyException, jeżeli dany klucz już istnieje w HashMap.
        if(containsKey(key))
            throw new DuplicateKeyException();

        int hashCode = hashFunction.apply(key);

        incrementCellsTakenOnCondition(hashCode % table.length);
        table[hashCode % table.length].add(new Node(key,value));
    }

    public void clear() {
        table = new LinkedList[table.length];
        for(int i=0;i<table.length;i++){
            table[i] = new LinkedList<>();
        }
        cellsTaken = 0;
        // TODO: Wyczyść zawartość HashMap.
    }

    public boolean containsKey(TKey key) {
        // TODO: Sprawdź, czy HashMap zawiera już dany klucz.
        int hashCode = hashFunction.apply(key);
        boolean isTaken = false;
        for(Node node:table[hashCode % table.length])
        {
            if(node.key.equals(key))
            {
                isTaken = true;
                break;
            }
        }
        return isTaken;
    }

    public boolean containsValue(TValue value) {
        // TODO: Sprawdź, czy HashMap zawiera już daną wartość.
        for(LinkedList<Node> linkedList: table)
            for (Node node: linkedList)
                if(node.value.equals(value))
                    return true;

        return false;
    }

    public int elements() {
        // TODO: Zwróć liczbę par klucz-wartość przechowywaną w HashMap.
        int numberOfElements = 0;
        for(LinkedList<Node> linkedList: table){
            if(linkedList != null)
                numberOfElements += linkedList.size();
        }
        return numberOfElements;
    }

    public TValue get(TKey key) throws NoSuchElementException {
        // TODO: Pobierz wartość powiązaną z danym kluczem. Rzuć wyjątek NoSuchElementException, jeżeli dany klucz nie istnieje.
        int hashCode = hashFunction.apply(key);
        for(Node node :table[hashCode % table.length])
            if(node.key.equals(key))
                return node.value;

        throw new NoSuchElementException();
    }

    public void put(TKey key, TValue value) {
        // TODO: Przypisz daną wartość do danego klucza.
        //   Jeżeli dany klucz już istnieje, nadpisz przypisaną do niego wartość.
        //   Jeżeli dany klucz nie istnieje, dodaj nową parę klucz-wartość.
        int hashCode = hashFunction.apply(key);
        for(Node node :table[hashCode % table.length])
            if(node.key.equals(key)) {
                node.value = value;
                return;
            }
        incrementCellsTakenOnCondition(hashCode % table.length);
        table[hashCode % table.length].add(new Node(key,value));
    }
    public void extendTableOnCondition(){
        if(cellsTaken/(double)table.length < loadFactor)
            return;
        table = Arrays.copyOf(table,table.length*2);
    }
    public void incrementCellsTakenOnCondition(int indexToBeCheckedForInc){
        if(table[indexToBeCheckedForInc].isEmpty()){
            cellsTaken++;
            extendTableOnCondition();
        }
    }

    public TValue remove(TKey key) {
        // TODO: Usuń parę klucz-wartość, której klucz jest równy podanej wartości.
        int hashCode = hashFunction.apply(key);
        for (Node node : table[hashCode % table.length])
            if(node.key.equals(key)){
                TValue temp = node.value;
                table[hashCode % table.length].remove(node);
                return temp;
            }
        return null;
    }

    public int size() {
        // TODO: Zwróć obecny rozmiar HashMap.
        return table.length;
    }
}
