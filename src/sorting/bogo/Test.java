package sorting.bogo;

public class Test {
    public static void main(String[] args) {
        int[] arr = {5, 2, 5, 1, 4, 0, 11, 8, 15, 55, -3, 2};
        Bogo bogo = new Bogo(arr);
        bogo.sort();
    }
}
