/**
 * The Queue class implements a simple FIFO data structure
 * with limited capacity that can store any Object instances.
 * Not to be confused with java.util.Queue.
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY20/21 ST1
 */
class Queue<T> {
    /** An array to store the items in the queue. */
    private final Array<T> items;

    /** Index of the first element in the queue. */
    private int first;

    /** Index of the last element in the queue. */
    private int last;

    /** Maximum size of the queue. */
    private int maxSize;

    /** Number of elements in the queue. */
    private int len;

    /**
     * Constructor for a queue.
     *
     * @param size The maximum num of elements we can put in the queue.
     */
    public Queue(int size) {
        this.maxSize = size;
        this.items = new Array<T>(size);
        this.first = -1;
        this.last = -1;
        this.len = 0;
    }

    /**
     * Add the object e into the queue.
     *
     * @param e The item to put in the queue.
     * @return false if the queue is full; true if e is added successfully.
     */
    public boolean enq(T e) {
        if (this.isFull()) {
            return false;
        }
        if (this.isEmpty()) {
            this.first = 0;
            this.last = 0;
        } else {
            this.last = (this.last + 1) % this.maxSize;
        }
        this.items.set(last,e);
        this.len += 1;
        return true;
    }

    /**
     * Remove the object from the queue.
     *
     * @return null if the queue is empty; the object removed from the queue otherwise.
     */
    public T deq() {
        if (this.isEmpty()) {
            return null;
        }
        T item = this.items.get(this.first);
        this.first = (this.first + 1) % this.maxSize;
        this.len -= 1;
        return item;
    }

    /** Return true if the queue is full; false otherwise. */
    boolean isFull() {
        return (this.len == this.maxSize);
    }

    /** Return true if the queue is empty; false otherwise. */
    boolean isEmpty() {
        return (this.len == 0);
    }

    /** Return The number of elements in the queue */
    public int length() {
        return this.len;
    }

    /**Return the maxSize of the queue, */
    public int size() {
        return maxSize;
    }

    /** Return a string consisting of the string representation of
     * every object in the queue.
     */
    @Override
    public String toString() {
        String str = "[ ";
        int i = this.first;
        int count = 0;
        while (count < this.len) {
            str += this.items.get(i) + " ";
            i = (i + 1) % this.maxSize;
            count++;
        }
        return str + "]";
    }
}
