package sorting.merge;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = {3, -1, 5, 15, 2, 8, -5, 55};
        Merge merge = new Merge();
        merge.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
