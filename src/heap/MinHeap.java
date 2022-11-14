package heap;

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

    private int getSmallestChildIndex(int index) {
        return isSmaller(leftChild(index), rightChild(index)) ? leftChildIndex(index) : rightChildIndex(index);
    }

    private boolean isValidParent(int index) {
        if (!hasLeftChild(index)) {
            return true;
        } else if (!hasRightChild(index)) {
            return isSmaller(parent(index), leftChild(index));
        }

        return isSmaller(parent(index), rightChild(index)) && isSmaller(parent(index), leftChild(index));
    }
}
