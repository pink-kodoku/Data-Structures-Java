package linearStructures.stack;

public class Main {
    public static void main(String[] args) {
        CustomStack<Integer> customStack = new CustomStack<>();
        Integer[] arr = {1, 5, 2, 7, 2, 3, 10, 5};
        System.out.println(customStack.maxValue(arr));
    }
}
