import java.util.Arrays;
import java.util.BitSet;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) {
        removeKey(7);
    }

    private static void removeKey(int value) {
        int[] arr = {3, 1, 5, 3, 7, 3, 5, 8};

        int removed = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                found = true;
                removed = arr[i];
                System.out.println("found");
            } else if (found) {
                System.out.println("was found");
                arr[i - 1] = arr[i];
            }
        }

        System.out.println("Removed value " + removed);
        System.out.println(Arrays.toString(arr));
    }
}
