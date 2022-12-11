package algorithms;

import java.util.Arrays;

public class ReverseArray {
    public static void main(String[] args) {
        int[] arr = {5, -3, 1, 99, 3, 5, 11};
        reverse(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void reverse(int[] arr) {
        int lowIndex = 0;
        int highIndex = arr.length - 1;

        while (lowIndex < highIndex) {
            swap(lowIndex, highIndex, arr);
            highIndex--;
            lowIndex++;
        }
    }

    private static void swap(int index1, int index2, int[] arr) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
