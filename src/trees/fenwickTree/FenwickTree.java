package trees.fenwickTree;

public class FenwickTree {
    private int[] elements;

    public FenwickTree(int[] nums) {
        this.elements = new int[nums.length + 1];
        construct(nums);
    }

    public int rangeSum(int startIndex, int endIndex) {
        return sum(endIndex) - sum(startIndex - 1);
    }

    private int sum(int index) {
        index++;
        int sum = 0;

        while (index > 0) {
            sum += elements[index];
            index = parent(index);
        }

        return sum;
    }

    private void construct(int[] nums) {
        for (int index = 1; index <= nums.length; index++) {
            update(index, nums[index - 1]);
        }
    }

    private void update(int index, int num) {
        while (index < elements.length) {
            elements[index] += num;
            index = next(index);
        }
    }

    private int next(int index) {
        return index + (index & -index);
    }

    private int parent(int index) {
        return index - (index & -index);
    }
}
