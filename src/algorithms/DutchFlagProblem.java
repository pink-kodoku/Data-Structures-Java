package algorithms;

import java.util.Arrays;

// we have 3 digits (0, 1, 2)
// need to sort the array in-place for O(n)
public class DutchFlagProblem {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 2, 1, 0, 0, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int lowIndex = 0;
        int highIndex = arr.length - 1;

        int index = 0;
        while (index <= highIndex) {
            if (arr[index] == 0) {
                swap(index, lowIndex, arr);
                lowIndex++;
                index++;
            } else if (arr[index] == 2) {
                swap(index, highIndex, arr);
                highIndex--;
            } else {
                index++;
            }
        }
    }

    private static void swap(int index1, int index2, int[] arr) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
