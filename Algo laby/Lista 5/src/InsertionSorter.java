public class InsertionSorter implements ISorter {
    private final IChecker checker;

    public InsertionSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        // TODO
        // Pamiętaj o wywołaniu checker.check(values); po kazdym wywołaniu zewnętrznej petli

        for (int i=1;i< values.length;i++){
            int index = i;
            while (index != 0 && values[index]<values[index-1]){
                Swapper.swap(index,index-1,values);
                index--;
            }
            checker.check(values);
        }
    }
}
