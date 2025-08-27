import java.util.ArrayList;
import java.util.List;

public class StackFromArray<T> {
    private int top = -1;
    private List<T> stack;

    public StackFromArray() {
        this.stack = new ArrayList<>();
        // LinkedList<>() лучше по скорости удаления, но проигрывает по памяти
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T item = stack.get(top);
        stack.remove(top);
        top--;
        return  item;
    }

    public void push(T item) {
        stack.add(item);
        top++;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return stack.get(top);
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {
        StackFromArray<Integer> stack = new StackFromArray<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
