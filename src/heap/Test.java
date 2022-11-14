package heap;

public class Test {
    public static void main(String[] args) {
        AbstractHeap<Integer> minHeap = new MinHeap<>();

        minHeap.insert(15);
        minHeap.insert(3);
        minHeap.insert(10);
        minHeap.insert(1);


        System.out.println(minHeap.size());
    }
}
