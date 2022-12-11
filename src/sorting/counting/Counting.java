package sorting.counting;

import java.util.Arrays;

public class Counting {
    public void sort(int[] arr, int max) {
        int[] counts = new int[max + 1];
        for (int n : arr) {
            counts[n]++;
        }

        int k = 0;
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                arr[k++] = i;
            }
        }
    }
}
