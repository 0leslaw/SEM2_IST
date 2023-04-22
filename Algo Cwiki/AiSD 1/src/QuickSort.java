import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.swap;

public class QuickSort {
    public static void QuickSort(ArrayList<Integer> list,int left,int right){
        int start_left = left--;
        int pivot = right;
        int start_right = right;
        if(start_left<right) {
            pivot = partition(list,left,right);

            QuickSort(list, start_left, pivot-1);
            QuickSort(list, pivot+1, start_right);
            }
        }

    public static int partition(ArrayList<Integer> list,int left,int right){
        int pivot = right;
        while (left<right){
            while (list.get(++left) < list.get(pivot));
            while (left < right && list.get(--right) >= list.get(pivot));
            swap(list,right,left);
        }
        swap(list,pivot,left);
        return left;
    }
}
