package trees.heap;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        CheckHeap checkHeap = new CheckHeap();

        int[] arr = {11, 2, 3, 4, 5, 6, 7};

        System.out.println(checkHeap.isMinHeap(arr));

        int[] maxHeap = {210, 100, 23, 2, 5};
        MaxToMinHeap maxToMinHeap = new MaxToMinHeap(maxHeap);
        int[] minHeap = maxToMinHeap.transform();

        System.out.println(Arrays.toString(minHeap));
    }
}
