package sorting.bucket;

import java.util.Collections;
import java.util.List;

public class Bucket {
    public void sort(int[] arr, int bucketsSize) {
        List<Integer>[] buckets = new List[bucketsSize];
        for (int i = 0; i < arr.length; i++) {
            int indexToInsert = arr[i] / bucketsSize;
            buckets[indexToInsert].add(arr[i]);
        }

        for (var bucket : buckets) {
            Collections.sort(bucket);
        }

        for (var bucket : buckets) {
            
        }
    }
}
