package sorting.selection;

// очень быстрый с маленькими массивами (5-10 элементов)
// поэтому он используется когда количество элементов в массиве меньше 10
// он делает меньше записей чем insertion sort
public class Selection {
    private int[] nums;

    public Selection(int[] nums) {
        this.nums = nums;
    }

    public void sort() {
        for (int i = 0; i < nums.length - 1; i++) {
            int index = i;

            // linear search for the min item
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[index]) {
                    index = j;
                }
            }

            // we have to swap min item with the "leftmost" item
            if (index != i) {
                swap(i, index);
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
