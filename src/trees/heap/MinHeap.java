package trees.heap;

public class MinHeap<T extends Comparable<T>> extends AbstractHeap<T> {
    public T min() {
        return items[0];
    }

    @Override
    protected void bubbleDown() {
        int index = 0;

        while (!isValidParent(index) && index < size) {
            int smallestChildIndex = getSmallestChildIndex(index);
            swap(index, smallestChildIndex);
            index = smallestChildIndex;
        }
    }

    @Override
    protected void bubbleUp() {
        int lastChildIndex = size;

        while (isSmaller(items[lastChildIndex], parent(lastChildIndex)) && lastChildIndex > 0) {
            int parentIndex = parentIndex(lastChildIndex);
            swap(parentIndex, lastChildIndex);
            lastChildIndex = parentIndex;
        }
    }

    private boolean isValidParent(int index) {
        if (!hasLeftChild(index)) {
            return true;
        } else if (!hasRightChild(index)) {
            return isSmaller(parent(index), leftChild(index));
        }

        return isSmaller(parent(index), rightChild(index)) && isSmaller(parent(index), leftChild(index));
    }

    public boolean isMinHeap(int[] heap) {
        for (int i = 0; i < (heap.length - 2) / 2; i++) {
            if (heap[i] > heap[2 * i + 1] || heap[i] > heap[2 * i + 2]) {
                return false;
            }
        }

        return true;
    }
}
