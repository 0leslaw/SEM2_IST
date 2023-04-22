import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class Heap {
    private int size;
    ArrayList<Integer> heapstructure = new ArrayList<>();
    public Heap(){}
    public Heap(ArrayList<Integer> heapstructure){size = heapstructure.size();this.heapstructure = heapstructure;}

    public int leftChildPos(int parentpos){
        if(2 * parentpos + 1 > size - 1)
            throw new NoSuchElementException();
        return 2*parentpos +1;
    }
    public int rightChildPos(int parentpos){
        if(2 * parentpos + 2 > size - 1)
            throw new NoSuchElementException();
        return 2 * parentpos + 2;
    }
    public int parentPos(int childpos){
        if((childpos+1)/2 -1 <0)
            throw new NoSuchElementException();
        return (childpos-1)/2;
    }
    public int leftChildVal(int parentpos){
        return heapstructure.get(leftChildPos(parentpos));
    }
    public int rightChildVal(int parentpos){
        return heapstructure.get(rightChildPos(parentpos));
    }
    public int parentVal(int childpos){
        return heapstructure.get(parentPos(childpos));
    }
    public int peek(){
        if(size == 0)
            throw new NoSuchElementException();
        return heapstructure.get(0);
    }
    public int poll(){
        if(size == 0)
            throw new NoSuchElementException();
        int top = heapstructure.get(0);
        heapstructure.set(0,heapstructure.remove(size-1));
        size--;
        bubbleSetFirstPos();
        return top;
    }
    public void swap(int i,int j){
        Collections.swap(heapstructure,i,j);
    }
    public void bubbleSetFirstPos(){
        int currentindex = 0;
        while ((2 * currentindex + 1 <= size - 1 && heapstructure.get(currentindex) > leftChildVal(currentindex)) ||
                (2 * currentindex + 2 <= size - 1 && heapstructure.get(currentindex) > rightChildVal(currentindex))){
            if(heapstructure.get(currentindex) > leftChildVal(currentindex)){
                swap(currentindex,leftChildPos(currentindex));
                currentindex = leftChildPos(currentindex);
            }else if(heapstructure.get(currentindex) > rightChildVal(currentindex)){
                swap(currentindex,rightChildPos(currentindex));
                currentindex = rightChildPos(currentindex);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Integer> getHeapstructure() {
        return heapstructure;
    }

    public void setHeapstructure(ArrayList<Integer> heapstructure) {
        this.heapstructure = heapstructure;
    }
}
