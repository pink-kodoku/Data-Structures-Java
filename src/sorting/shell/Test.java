package sorting.shell;

public class Test {
    public static void main(String[] args) {
        int[] arr = {3, -1, 5, 3, 9, 10, 11, 2, -5, 55};
        Shell shell = new Shell(arr);
        shell.sort();
    }
}
