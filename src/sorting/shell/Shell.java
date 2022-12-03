package sorting.shell;

// lots of shift operations
// enhanced insertion sort
// unstable - changes the relative order of elements with equal value
// adaptive algorithm
public class Shell {
    private int[] nums;

    public Shell(int[] nums) {
        this.nums = nums;
    }

    public void sort() {

        // usually the gap = number of items / 2
        // when the gap=1 this is the standard insertion sort
        for (int gap = nums.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < nums.length; i++) {
                int j = i;
                while (j >= gap && nums[j - gap] > nums[j]) {
                    swap(j, j - gap);
                    j -= gap;
                }
            }
        }

        showSortedArray();
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
