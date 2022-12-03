package sorting.quicksort;

// divide and conquer algorithm - divides the problem into smaller and smaller subproblems
// O(nlogN)
// in-place algorithm
// not stable
// comparison based sorting algorithm
// used with primitive types (ints, floats)
// for reference types usually merge sort uses
// python relies heavily on timsort (insertion sort + merged sort)
// worst case scenario is O(n^2), when we pick the largest item in every iteration to be the pivot
public class Quick {
    private int[] nums;

    public Quick(int[] nums) {
        this.nums = nums;
    }


    // partition phase
    // generates a pivot item and partition the array. The pivot is the item that separates the array
    // smaller items are on the left side of the pivot
    // larger items are on the right side of the pivot

    // how to generate a pivot?
    // 1) we can use the middle item of the array as the pivot
    // 2) we can generate a random item

    // recursion phase
    // we found the left and right subarrays during partition.
    // We call the quicksort method recursively on both subarrays
    public void sort() {
        sort(0, nums.length - 1);
        showSortedArray();
    }

    private void sort(int low, int high) {
        if (high < low) return;

        int pivot = partition(low, high);
        sort(low, pivot - 1);
        sort(pivot + 1, high);
    }

    private int partition(int low, int high) {
        int middleIndex = (low + high) / 2;
        swap(middleIndex, high);

        int i = low;

        for (int j = low; j < high; j++) {
            if (nums[j] <= nums[high]) {
                swap(i, j);
                i++;
            }
        }
        swap(i, high);

        return i;
    }


    private void showSortedArray() {
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    private void swap(int firstIndex, int secondIndex) {
        int temp = nums[firstIndex];
        nums[firstIndex] = nums[secondIndex];
        nums[secondIndex] = temp;
    }
}
