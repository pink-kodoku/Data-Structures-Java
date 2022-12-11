package sorting.counting;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4, 1};
        Counting counting = new Counting();

        counting.sort(arr, 4);

        System.out.println(Arrays.toString(arr));
    }
}
