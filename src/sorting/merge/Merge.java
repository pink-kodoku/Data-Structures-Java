package sorting.merge;


// O(nlogn)
// comparison based algorithm
// stable sorting algorithm
// not in-place
public class Merge {
    public void sort(int[] arr) {
        if (arr.length < 2) {
            return;
        }

        int middleIndex = arr.length / 2;

        int[] left = new int[middleIndex];
        for (int i = 0; i < middleIndex; i++) {
            left[i] = arr[i];
        }

        int[] right = new int[arr.length - middleIndex];
        for (int i = middleIndex; i < arr.length; i++) {
            right[i - middleIndex] = arr[i];
        }

        sort(left);
        sort(right);

        merge(left, right, arr);
    }

    private void merge(int[] left, int[] right, int[] result) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }
    }
}
