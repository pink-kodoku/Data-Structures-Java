package sorting.bubble;

public class Bubble {
    private int[] nums;

    public Bubble(int[] nums) {
        this.nums = nums;
    }

    public void sort() {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(j, j + 1);
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
