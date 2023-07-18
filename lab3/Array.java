/**
 * This is a generic array class
 * @param <T>
 */
class Array<T> {
    private T[] array;
    private int size;

    /**
     * Constructor for the array
     *
     * @param size the number of items in the array
     */
    public Array(int size) {
        @SuppressWarnings("unchecked")
        T[] a = (T[]) new Object[size];
        this.array = a;
        this.size = size;
    }

    /**
     * Set the value for the item at a particular index
     * @param index of the item in the array
     * @param item value of the item
     */
    public void set(int index, T item) {
        this.array[index] = item;
    }

    /**
     * The item at a particular index
     * @param index of the item
     * @return value of the item at the given index
     */
    public T get(int index) {
        return this.array[index];
    }

    /**
     * Get the items in the array
     * @return an array of items in the array
     */
    public T[] getArray() {
        return this.array;
    }

    public int len() {
        return this.size;
    }

    /**
     * Get the minimum item in the array
     * @param size number of items in the array
     * @return the value of the minimum item in the array
     */
    public <T extends Comparable<T>> T min(int size) {
        T minValue = (T) this.array[0];
        for(int i = 1; i < size; i++) {
            if( minValue.compareTo((T) this.array[i]) > 0) { //compareTo returns an int
                minValue = (T) this.array[i];
            }
        }
        return minValue; // returns the minimum after checking all the array
    }
}

