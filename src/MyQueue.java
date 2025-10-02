import java.util.Stack;

public class MyQueue {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.push(3); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.push(4); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(myQueue.pop()); // return 1, queue is [2]
        myQueue.push(5); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(myQueue.pop()); // return 1, queue is [2]
        System.out.println(myQueue.pop()); // return 2, queue is [2]
        System.out.println(myQueue.pop()); // return 1, queue is [2]
        System.out.println(myQueue.pop()); // return 1, queue is [2]
        System.out.println(myQueue.empty()); // return false

    }
    private final Stack<Integer> stackIn;
    private final Stack<Integer> stackOut;
    private int topIn = -1;
    private int topOut = -1;
    public MyQueue() {
        this.stackIn = new Stack<>();
        this.stackOut = new Stack<>();
    }

    public void push(int x) {
        while (topOut >= 0) {
            stackIn.push(stackOut.pop());
            topIn++;
            topOut--;
        }
        stackIn.push(x);
        topIn++;


    }

    public int pop() {
        while (topIn >= 0) {
            stackOut.push(stackIn.pop());
            topIn--;
            topOut++;
        }
        topOut--;
        return stackOut.pop();
    }

    public int peek() {
        if (topIn >= 0) {
        return stackIn.getFirst();
        } else if (topOut >= 0) {
            return stackOut.getLast();
        }
        return 0;
    }

    public boolean empty() {
        return topIn < 0 && topOut < 0;
    }
}
