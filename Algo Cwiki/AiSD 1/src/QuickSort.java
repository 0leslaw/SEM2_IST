import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuickSort {
    public static void QuickSort(ArrayList<Integer> list,int left,int right){
        int start_left = left--;
        int pivot = right;
        int start_right = right;
        if(start_left<right) {
            left = partition(list,left,right);
            Collections.swap(list,list.size()-1,left);
            QuickSort(list, start_left, left-1);
            QuickSort(list, left+1, start_right);
            }
        }

    public static int partition(ArrayList<Integer> list,int left,int right){
        int pivot = right;
        while (left<right){
            while (list.get(++left) < list.get(pivot));
            while (left < right && list.get(--right) >= list.get(pivot));
            Collections.swap(list,right,left);
        }
        return left;
    }
}
