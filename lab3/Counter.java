/**
 * This class is the counter class
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY20/21 ST1
 */
class Counter {
    /**
     * The id of the counter associated with this event.
     */
    private final int counterId;

    /** This indicates whether the service counter
     * is available. Available is true if and only if
     * the service counter is available to serve
     */
    private boolean available;

    private final Queue<Customer> queueCounter;

    /**
     *
     * @param counterId: ID of the corresponding counter
     * @param available: Indicator of whether the service
     *                 counter is available.
     */
    public Counter(int counterId, boolean available, Queue queueCounter) {
        this.counterId = counterId;
        this.available = available;
        this.queueCounter = queueCounter;
    }

    /**
     * Returing the counter id
     * @return An integer of the counter id
     */
    public int getId() {
        return this.counterId;
    }

    /**
     * Check whether the counter is available
     * @return A boolean value representing whehter the
     *         counter is available
     */
    public boolean getAvailability() {
        return this.available;
    }

    /**
     * Set the counter's availablity
     * @param a as true if the counter is available,
     *          as false if the counter is not available/being served
     */
    public void setAvailable(boolean a) {
        this.available = a;
    }

    /** Return the queue at this counter. */
    public Queue<Customer> getQueueCounter() {
        return this.queueCounter;
    }

    /** Return the length of the queue at this counter. */
    public int getQueueCounterLength() {
        return this.queueCounter.length();
    }

    /**Return the maxSize of the queue counter, */
    public int getQueueCounterSize() {
        return this.queueCounter.size();
    }

    /** Return true if the queue at this coutner is empty. */
    public boolean queueCounterEmpty() {
        return this.queueCounter.isEmpty();
    }

    /** Dequeue the queue at this coutner. */
    public Customer deqQueueCounter() {
        return this.queueCounter.deq();
    }


}

