public class BubbleSorter implements ISorter {
    private final IChecker checker;

    public BubbleSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        // TODO
        // Pamiętaj o wywołaniu checker.check(values); po kazdym wywołaniu zewnętrznej petli

        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < values.length - 1; i++)
                if (values[i] > values[i + 1]) {
                    Swapper.swap(i,i+1,values);
                    sorted = false;
                }
            checker.check(values);
        }
    }
}
