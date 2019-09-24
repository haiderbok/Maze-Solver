public class MyStack<E> extends java.lang.Object {
    public static final int DEFAULT_SIZE = 32;

    private E[] stack;
    private int count;

    /**
     * Creates an empty Stack with initial capacity of {@code DEFAULT_SIZE}.
     */
    public MyStack(){
        stack = (E[]) new Object[DEFAULT_SIZE];
        count = 0;
    }

    /**
     * Creates an empty Stack with initial capacity.
     * @param initialCapacity the initial capacity.
     */
    public MyStack(int initialCapacity) {
        stack = (E[]) new Object[initialCapacity];
        count = 0;
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    public int size() { return this.count; }

    /**
     * Returns {@code true} if this stack contains no elements.
     *
     * @return {@code true} if this stack contains no elements
     */
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }
        return false;
    }

    /* resize the underlying array holding the elements */
    /* private void resize(int capacity) { } */

    /**
     * Adds the element to this stack.
     * @param e the element to add
     */
    public void push(E e) {

        // check if size needs to be updated
            resize(this.stack.length);

        // Adding the element at the available index
        this.stack[count] = e;
        //System.out.println("stack push" + stack[count]);
        // Increase the size by one
        this.count++;
    }

    /**
     * Removes and returns the element most recently added to this stack.
     * @return the element most recently added, or {@code null} if
     * this stack is empty
     *
     */
    public E pop() {
        // if the stack is empty return null
        if (this.isEmpty()){
            return null;
        } else {
            //Defining an temp to save the element being removed
            E temp = stack[count - 1];
            // Decreasing the size and so that index can be used again(overwrite).
            count--;
            //System.out.println(count);
            //System.out.println("stack pop" + stack[count]);

            // check if the array used is less than 25 percent
            int resize = (count * 100 / stack.length) ;
            if (resize <= 25 && count > 1){
                //Creating new array half the size of that og
                E[] newarr =(E[]) new Object[stack.length / 2];
                //Copying the elements of the stack to the new array
                for (int i = 0; i < newarr.length ; i++) {
                    newarr[i] = stack[i];
                }
                // Referencing stack to the new arr
                this.stack = newarr;
            }

            // returning the value
            return temp;
        }
    }

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     * @return the item most recently added to this stack, or {@code null} if
     * this stack is empty
     */
    public E peek() {
        if (this.isEmpty()){
            return null;
        } else {
            //Defining an temp to save the element being removed
            E temp = stack[count - 1];
            // returning the value
            return temp;
        }
    }

    private void resize (int capacity) {

        //If the size and the array length are the same double the size
        if (capacity == count) {
            // Defining an array double the size of the present stack
            E[] newarr = (E[]) new Object[2 * capacity];
            // Copying the elements of the array
            for (int i = 0; i < capacity; i++) {
                newarr[i] = this.stack[i];
            }
            // Referencing stack to the new arr
            this.stack = newarr;
        }


    }


}
