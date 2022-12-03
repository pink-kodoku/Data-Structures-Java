package sorting.quicksort;

public class Test {
    public static void main(String[] args) {
        int[] arr = {3, -5, 1, 15, 55, -33, 5, 12, 4, 9, 10, 12};
        Quick quick = new Quick(arr);
        quick.sort();
    }
}
