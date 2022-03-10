
/**
 * Stack ADT reference from CS5040 class note
 * 
 * @author Wenjun Han(hwenjun)
 * @version 2022.1.29
 * @param <E> element type
 *
 */
public interface StackADT<E> {

    /**
     * Reinitialize the stack.
     */
    public void clear();


    /**
     * Push item onto the top of the stack
     * 
     * @param item
     *            The element that we want to push
     * @return true if push successfully, false if not
     */
    public boolean push(E item);


    /**
     * Remove and return the element at the top of the stack
     * 
     * @return the removed element at the top
     */
    public E pop();


    /**
     * Return a copy of the top element
     * 
     * @return the top element(but not removed)
     */
    public E peek();


    /**
     * Return the number of elements in the stack
     * 
     * @return number of elements in stack
     */
    public int length();


    /**
     * Return true if the stack is empty
     * 
     * @return true if stack is empty, false if not
     */
    public boolean isEmpty();
}
