package sorting.bubble;

public class Test {
    public static void main(String[] args) {
        int[] arr = {7, 0, -3, 2, -55, 3, 12, 9, -5};

        Bubble bubble = new Bubble(arr);
        bubble.sort();
    }
}
