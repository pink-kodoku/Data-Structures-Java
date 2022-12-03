package sorting.bogo;

// O(n!)
// this is efficient of quantum computers
public class Bogo {
    private int[] nums;
    private int counter;

    public Bogo(int[] nums) {
        this.nums = nums;
    }

    public void sort() {
        while (!isSorted()) {
            counter++;
            shuffle();
        }
        showSortedArray();
    }

    private void showSortedArray() {

        System.out.println("The number of iterations: " + counter);

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    // Fisher-Yates algorithm
    private void shuffle() {
        // consider items in reverse order
        for (int i = nums.length - 1; i >= 0; i--) {
            // generate random index
            int randomIndex = (int) (Math.random() * i);
            // swap two items
            swap(i, randomIndex);
        }
    }

    private void swap(int firstIndex, int secondIndex) {
        int temp = nums[firstIndex];
        nums[firstIndex] = nums[secondIndex];
        nums[secondIndex] = temp;
    }

    private boolean isSorted() {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] < nums[i]) {
                return false;
            }
        }
        return true;
    }
}
