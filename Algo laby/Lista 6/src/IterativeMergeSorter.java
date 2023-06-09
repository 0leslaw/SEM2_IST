public class IterativeMergeSorter implements ISorter {
    private final IChecker checker;

    public IterativeMergeSorter(IChecker checker) {
        this.checker = checker;
    }

    @Override
    public void sort(int[] values) {
        // Pamiętaj o wywołaniu checker.check(values); po kazdym wywołaniu zewnętrznej petli
        int next_subArr_size = 2;

        while (next_subArr_size/2 < values.length){
            for(int num_of_sub_array = 0;next_subArr_size/2*(2*num_of_sub_array+1)< values.length;num_of_sub_array++) {

                if((num_of_sub_array+1)*next_subArr_size-1< values.length)
                    merge(values, num_of_sub_array * next_subArr_size,next_subArr_size/2*(2*num_of_sub_array+1), (num_of_sub_array + 1) * next_subArr_size-1);
                else
                    merge(values, num_of_sub_array * next_subArr_size,next_subArr_size/2*(2*num_of_sub_array+1), values.length-1);

            }
            next_subArr_size*=2;
            checker.check(values);
        }
    }

    public void merge(int[] arr,int left,int mid,int right){
        int[] aux_array = new int[right-left+1];
        int m_start = mid;
        int l_start = left;
        int array_counter = 0;
        while(left < m_start && mid <= right){
            if(arr[left] < arr[mid])
                aux_array[array_counter] = arr[left++];
            else
                aux_array[array_counter] = arr[mid++];

           array_counter++;
        }
        while (left < m_start){
            aux_array[array_counter] = arr[left++];
            array_counter++;
        }
        while (mid <= right){
            aux_array[array_counter] = arr[mid++];
            array_counter++;
        }
        array_counter = 0;
        for(;l_start<=right;l_start++){
            arr[l_start] = aux_array[array_counter];
            array_counter++;
        }
    }
}
