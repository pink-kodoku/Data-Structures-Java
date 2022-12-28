package algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumProblem {
    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int[] arr2 = {3,2,4};
        System.out.println(Arrays.toString(bruteForceTwoSum(arr, 9)));
        System.out.println(Arrays.toString(bruteForceTwoSum(arr2, 6)));
        System.out.println(Arrays.toString(twoSum(arr, 9)));
        System.out.println(Arrays.toString(twoSum(arr2, 6)));
    }

    // O(n^2)
    // in-place
    public static int[] bruteForceTwoSum(int[] arr, int target) {
        int[] result = new int[2];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int sum = arr[i] + arr[j];
                if (sum == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;
    }

    // O(n)
    // not in-place
    public static int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        int[] result = new int[2];

        for (int i = 0; i < arr.length; i++) {
            int diff = target - arr[i];
            if (hashMap.containsKey(diff)) {
                result[0] = hashMap.get(diff);
                result[1] = i;
                return result;
            }
            hashMap.put(arr[i], i);
        }

        return result;
    }

}
