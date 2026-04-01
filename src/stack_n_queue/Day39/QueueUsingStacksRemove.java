package stack_n_queue.Day39;

import java.util.Stack;

//Remove Efficient
public class QueueUsingStacksRemove<T> {
    private Stack<T> first;
    private Stack<T> second;

    public QueueUsingStacksRemove() {
        this.first = new Stack<>();
        this.second = new Stack<>();
    }

    public void add(T val) throws Exception{
        while (!first.isEmpty()){
            second.push(first.pop());
        }
        first.push(val);
        while(!second.isEmpty()){
            first.push(second.pop());
        }
    }

    public T remove() throws Exception{
        return (T) first.pop();
    }

    public T peek() throws Exception{
        return (T) first.peek();
    }

    public boolean isEmpty(){
        return first.isEmpty();
    }

}
