package heap;

public abstract class AbstractHeap<T extends Comparable<T>> {
    protected T items[] = (T[]) new Comparable[100];
    protected int size = -1;

    protected abstract void bubbleDown();
    protected abstract void bubbleUp();

    public void insert(T value) {
        items[++size] = value;
        bubbleUp();
    }

    public T delete() {
        T deletedValue = items[0];
        swap(0, size);
        size--;
        bubbleDown();
        return deletedValue;
    }

    protected boolean isLarger(T first, T second) {
        return first.compareTo(second) > 0;
    }

    protected boolean isSmaller(T first, T second) {
        return first.compareTo(second) < 0;
    }

    public int size() {
        return size + 1;
    }

    protected void swap(int firstIndex, int secondIndex) {
        T temp = items[firstIndex];
        items[firstIndex] = items[secondIndex];
        items[secondIndex] = temp;
    }

    protected boolean hasLeftChild(int index) {
        return leftChildIndex(index) <= size;
    }

    protected boolean hasRightChild(int index) {
        return rightChildIndex(index) <= size;
    }

    protected T leftChild(int index) {
        return items[leftChildIndex(index)];
    }

    protected T rightChild(int index) {
        return items[rightChildIndex(index)];
    }

    protected T parent(int index) {
        return items[parentIndex(index)];
    }

    protected int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    protected int rightChildIndex(int index) {
        return 2 * index + 2;
    }

    protected int parentIndex(int index) {
        return (index - 1) / 2;
    }
}
