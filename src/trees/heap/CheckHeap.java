package trees.heap;

public class CheckHeap {
    public boolean isMinHeap(int[] heap) {
        for (int i = 0; i < (heap.length - 2) / 2; i++) {
            if (heap[i] > leftChild(i, heap) || heap[i] > rightChild(i, heap)) {
                return false;
            }
        }
        return true;
    }

    private int rightChild(int index, int[] heap) {
        return heap[rightChildIndex(index)];
    }

    private int leftChild(int index, int[] heap) {
        return heap[leftChildIndex(index)];
    }

    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }
}
