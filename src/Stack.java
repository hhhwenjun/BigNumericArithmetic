
import java.util.EmptyStackException;

/**
 * The Array-based stack implementation that implement stackADT
 * contains codes from my previous assignment in CS2114
 * 
 * @author Wenjun Han(hwenjun)
 * @version 2022.1.29
 *
 * @param <T> The content type of stack
 */
public class Stack<T> implements StackADT<T> {

    private T[] myStack;
    /** the size of stack, the real number of items **/
    private int size;
    /** the size of stack, the maximum number of items it can hold **/
    private int capacity;

    /**
     * constructor for the stack
     * 
     * @param capacity
     *            the stack capacity
     */
    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        this.capacity = capacity;
        size = 0;
        myStack = (T[])new Object[capacity];
    }


    /**
     * constructor for the stack when not initialize the capacity
     */
    public Stack() {
        this(10);
    }


    /**
     * clear the stack
     */
    @Override
    public void clear() {
        size = 0;
        myStack = null;

    }


    /**
     * push element to the stack at the top
     * 
     * @param item
     *            the element we want to put in the stack
     * @return true if we push successfully, false if not
     */
    @Override
    public boolean push(T item) {
        if (size < capacity) {
            myStack[size] = item;
            size++;
        }
        else {
            expandCapacity();
            myStack[size] = item;
            size++;
        }
        return false;
    }


    /**
     * expands the capacity of the stack by doubling its current capacity.
     */
    private void expandCapacity() {
        @SuppressWarnings("unchecked")
        T[] newArray = (T[])new Object[this.capacity * 2];

        for (int i = 0; i < this.capacity; i++) {
            newArray[i] = this.myStack[i];
        }

        this.myStack = newArray;
        this.capacity *= 2;
    }


    /**
     * pop the element on the top of the stack
     * 
     * @throws EmptyStackException
     *             if the stack is empty
     * @return the pop element
     */
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        else {
            T temp = myStack[size - 1];
            myStack[size - 1] = null;
            size--;
            return temp;
        }
    }


    /**
     * peek the element at the top of the stack
     * 
     * @throws EmptyStackException
     *             if the stack is empty
     * @return the peek element
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        else {
            T temp = myStack[size - 1];
            return temp;
        }
    }


    /**
     * return the size of the stack
     * 
     * @return size
     */
    @Override
    public int length() {

        return size;
    }


    /**
     * return if the stack is empty
     * 
     * @return true if empty, false if not
     */
    @Override
    public boolean isEmpty() {

        return size == 0;
    }

}
