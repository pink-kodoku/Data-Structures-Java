package linearStructures.queue;

public class Test {
    public static void main(String[] args) {
//        QueueWithStacks<Integer> queueWithStacks = new QueueWithStacks<>();
//
//        queueWithStacks.enqueue(10);
//        queueWithStacks.enqueue(20);
//        queueWithStacks.enqueue(30);
//        queueWithStacks.enqueue(40);
//        queueWithStacks.enqueue(50);
//
//        System.out.println(queueWithStacks.dequeue());
//        System.out.println(queueWithStacks.dequeue());
//
//        queueWithStacks.enqueue(55);
//        System.out.println(queueWithStacks.dequeue());
//        System.out.println(queueWithStacks.dequeue());
//        System.out.println(queueWithStacks.dequeue());
//        System.out.println(queueWithStacks.dequeue());

        QueueWithStacks<Integer> queueWithStacks = new QueueWithStacks<>();

        queueWithStacks.enqueue(10);
        queueWithStacks.enqueue(20);
        queueWithStacks.enqueue(30);

        System.out.println(queueWithStacks.recursiveDequeue());
        System.out.println(queueWithStacks.recursiveDequeue());
    }
}
