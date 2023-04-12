public class Swapper {
    public static<T> void swap(int index1,int index2,T Array[]){
        T temp = Array[index1];
        Array[index1] = Array[index2];
        Array[index2] = temp;
    }
    public static void swap(int index1,int index2,int Array[]){
        int temp = Array[index1];
        Array[index1] = Array[index2];
        Array[index2] = temp;
    }
}
