public class SelectionSorter implements ISorter {
    private final IChecker checker;

    public SelectionSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        // TODO
        // Pamiętaj o wywołaniu checker.check(values); po kazdym wywołaniu zewnętrznej petli


        for(int i = 0; i< values.length;i++){
            int index = i;
            int index_of_min = i;
            while(index<values.length){
                if(values[index_of_min]>values[index])
                    index_of_min = index;
                index++;
            }
            Swapper.swap(i,index_of_min,values);
            checker.check(values);
        }
    }
}
