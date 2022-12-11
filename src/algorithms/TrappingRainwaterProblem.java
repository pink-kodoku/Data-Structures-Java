package algorithms;

public class TrappingRainwaterProblem {
    public static void main(String[] args) {
        int[] arr = {1, 0, 2, 1, 3, 1, 2, 0, 3};
        System.out.println(getFilledBuckets(arr));
    }

    public static int getFilledBuckets(int[] arr) {
        int leftBorder = 0;
        int rightBorder = 0;
        int result = 0;

        int index = 0;
        while (index < arr.length - 1) {
            if (arr[index] == 0) {
                index++;
                continue;
            }
            leftBorder = index;
            rightBorder = findMax(arr, leftBorder, arr.length - 1);

            int min = Math.min(arr[leftBorder], arr[rightBorder]);
            for (int i = leftBorder + 1; i < rightBorder; i++) {
                int size = min - arr[i];
                result += size;
            }

            index = rightBorder;
        }

        return result;
    }

    private static int findMax(int[] arr, int leftIndex, int rightIndex) {
        int largerIndex = leftIndex;
        for (int i = leftIndex + 1; i < rightIndex; i++) {
            if (arr[i] >= arr[largerIndex]) {
                largerIndex = i;
                break;
            }
        }

        int maxValueIndex = rightIndex;
        if (largerIndex == leftIndex) {
            for (int i = leftIndex + 1; i < rightIndex; i++) {
                if (arr[i] > arr[maxValueIndex]) {
                    maxValueIndex = arr[i];
                }
            }
            return maxValueIndex;
        } else {
            return largerIndex;
        }
    }
}
