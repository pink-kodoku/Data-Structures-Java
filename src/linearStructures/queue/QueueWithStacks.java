package linearStructures.queue;

import java.util.Stack;

public class QueueWithStacks<T extends Comparable<T>> {
    private Stack<T> enqueueStack = new Stack<>();
    private Stack<T> dequeueStack = new Stack<>();

    public void enqueue(T value) {
        enqueueStack.push(value);
    }

    public T dequeue() {
        if (enqueueStack.isEmpty() && dequeueStack.isEmpty()) {
            throw new IllegalStateException();
        }

        if (dequeueStack.isEmpty()) {
            while (!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
        }

        return dequeueStack.pop();
    }

    // recursive solution with one stack
    public T recursiveDequeue() {
        if (enqueueStack.size() == 1) {
            return enqueueStack.pop();
        }

        T item = enqueueStack.pop();

        T lastDequeuedItem = recursiveDequeue();

        enqueue(item);

        return lastDequeuedItem;
    }

    public boolean isEmpty() {
        return enqueueStack.isEmpty() && dequeueStack.isEmpty();
    }
}
