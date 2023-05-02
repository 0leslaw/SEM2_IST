public class InvertedSelectionSorter implements ISorter {
    private final IChecker checker;

    public InvertedSelectionSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        // TODO
        // Zaimplementuj algorytm SelectionSort "w drugą stronę" (czyli przechodząc od drugiej strony tablicy)
        // Pamiętaj o wywołaniu checker.check(values); po kazdym wywołaniu zewnętrznej petli
        for(int i= values.length-1;i>0;i--) {
            int j = i-1;
            int temp = i;
            while (j>-1){
                if(values[j]>values[temp]) temp = j;
                j--;
            }
            Swapper.swap(temp,i,values);
            checker.check(values);
        }
    }
}
