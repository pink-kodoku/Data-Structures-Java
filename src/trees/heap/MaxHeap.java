package trees.heap;

public class MaxHeap<T extends Comparable<T>> extends AbstractHeap<T> {
    protected void bubbleUp() {
        int lastChildIndex = size;

        while (isLarger(items[lastChildIndex], parent(lastChildIndex)) && lastChildIndex > 0) {
            int parentIndex = parentIndex(lastChildIndex);
            swap(parentIndex, lastChildIndex);
            lastChildIndex = parentIndex;
        }
    }

    public T max() {
        return items[0];
    }

    protected void bubbleDown() {
        int index = 0;

        while (!isValidParent(index) && index < size) {
            int largerItemIndex = getLargerItemIndex(index);
            swap(index, largerItemIndex);
            index = largerItemIndex;
        }
    }

    private boolean isValidParent(int index) {
        if (!hasLeftChild(index)) {
            return true;
        } else if (!hasRightChild(index)) {
            return isLarger(parent(index), leftChild(index));
        }

        return isLarger(parent(index), rightChild(index)) && isLarger(parent(index), leftChild(index));
    }
}

