package sorting.insertion;

// быстрый с маленькими массивами (10-20 элементов)
// медленный с большими массивами O(n^2)
// adaptive algorithm - speeds up when array is already substantially sorted
// stable - preserves the order of the items with equal keys
// in-place algorithm
// online algorithm - it can sort an array as it receives the items for example downloading data from web
// делает больше записей чем selection sort
// более эффективен чем selection sort
public class Insertion {

    int[] nums;

    public Insertion(int[] nums) {
        this.nums = nums;
    }

    public void sort() {

        // проходимся по всем элементам массива
        for (int i = 1; i < nums.length; i++) {
            // проверяем, если правый сосед больше
            if (nums[i] < nums[i - 1]) {
                // идем влево, переставляя элементы
                for (int j = i; j > 0; j--) {
                    if (nums[j] < nums[j - 1]) {
                        swap(j, j - 1);
                    }
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
