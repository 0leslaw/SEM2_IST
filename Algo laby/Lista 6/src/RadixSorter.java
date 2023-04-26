public class RadixSorter implements ISorter {
    private final IChecker checker;

    public RadixSorter(IChecker checker) {
        this.checker = checker;
    }

    public static int getMax(int[] values){
        int max =0;
        for (Integer val:values) {
            if(val>max)
                max = val;
        }
        return max;
    }
    @Override
    public void sort(int[] values) {

        int max = getMax(values);

        for (int i = 1; (max / i) > 0; i *= 10){
            countSort(values, i);
            checker.check(values);
        }
    }
    public static void countSort(int[] values,int magnitude){
        int length = values.length;
        int[] counter = new int[10];
        int[] output = new int[length];

        for(Integer value:values)
            counter[(value / magnitude) % 10]++;

        for(int iterator = 1;iterator<10;iterator++)
            counter[iterator] += counter[iterator-1];

        for(int iterator = length - 1; iterator >= 0; iterator--){
            output[counter[(values[iterator]/magnitude) % 10] -1] = values[iterator];
            counter[(values[iterator]/magnitude) % 10]--;
        }
        for (int iterator = 0;iterator<length;iterator++)
            values[iterator] = output[iterator];


    }
    public static void swap(int index1,int index2,int Array[]){
        int temp = Array[index1];
        Array[index1] = Array[index2];
        Array[index2] = temp;
    }
}
