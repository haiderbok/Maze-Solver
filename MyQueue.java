public class MyQueue<E> extends java.lang.Object {
    private Node first;
    private Node last;
    private int count;

    class Node {
        private E e;
        private Node next;
    }

    /**
     * Creates an empty queue and initializes variables.
     */
    public MyQueue(){
        this.first = null;
        this.last = null;
        this.count = 0;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number pf elements in the stack
     */
    public int size(){ return count; }

    /**
     * Returns {@code true} if this queue contains no elements.
     *
     * @return {@code true} if this queue contains no elements; {@code false} otherwise
     */
    public boolean isEmpty() {
        if (count == 0){
            return true;
        }
        return false;
         }

    /**
     * Adds the element to this queue.
     * @param e the element to add
     */
    public void enqueue(E e) {
        // Create a new node and assign it the value to be stored
        Node new_node = new Node();
        new_node.e = e;
        // if the queue is empty
        if (this.isEmpty()){
            //Assign the first node to new formed node
            first = new_node;
            //Assign the last to this new node
            last = new_node;
            // If the queue is not empty
        } else {
            Node temp = last;
            //Assign last to the new node
            last = new_node;
            //point this node to the previously stored one and the the old to the this new one
            last.next = temp;
            temp.next = new_node;
        }
        //Increase the count
        count++;

    }

    /**
     * Removes and returns the element on this queue that was least recently added.
     * @return the element on this queue that was least recently added, or {@code null}
     * if this queue is empty
     */
    public E dequeue() {
        if (this.isEmpty()){
            // return null if the queue is empty.
            return null;
        } else {
            // Store the value of the element to be deleted
            E templast = this.first.e;
            //Make first the next item
            this.first = this.first.next;
            // Decrease the count
            count--;
            // return the value of the element
            return templast;
        }
    }

    /**
     * Returns the item least recently added to this queue.
     * @return the item least recently added to this queue, or {@code null} if
     * this queue is empty
     */
    public E peek() {
        if (this.isEmpty()){
            return null;
        } else {
            return this.first.e;
        }
    }
}
