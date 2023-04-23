public class RadixSorter implements ISorter {
    private final IChecker checker;

    public RadixSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        // TODO
        // Pamiętaj o wywołaniu checker.check(values); po kazdym wywołaniu zewnętrznej petli

        int magnitude = 10;
        int max = values[0];
        for (Integer val:values) {
            if(val>max)
                max = val;
        }
        while (magnitude/10< max){
            insCounter(values,magnitude);
            magnitude*=10;
            checker.check(values);
        }

    }
    public static void insCounter(int[] values,int magnitude){
        for (int i=1;i< values.length;i++){
            int index = i;
            while (index != 0 && values[index] % magnitude<values[index-1] % magnitude){
                swap(index,index-1,values);
                index--;
            }
        }
    }
    public static void swap(int index1,int index2,int Array[]){
        int temp = Array[index1];
        Array[index1] = Array[index2];
        Array[index2] = temp;
    }
}
