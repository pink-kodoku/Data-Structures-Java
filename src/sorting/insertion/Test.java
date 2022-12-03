package sorting.insertion;

public class Test {
    public static void main(String[] args) {
        int[] arr = {3, 1, -5, 4, 15, 55, -4, 23, 11, 5};

        Insertion insertion = new Insertion(arr);
        insertion.sort();
    }
}
