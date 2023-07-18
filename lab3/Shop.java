/**
 * This class is the shop class
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY20/21 ST1
 */
class Shop {
    /**
     * An array storing the counters available at the shop
     */
    private final Counter[] counters;

    /** The array of customers coming into the shop */
    private final Customer[] customers;

    /** The queue at the entrance of the shop */
    private final Queue<Customer> queueEntrance;

    /** Total number of counters in the shop. */
    private final int maxNumCounters;

    /** Number of counters being used in the shop */
    private int numCountersServed;

    /**
     *
     * @param counters          The array of counters in the shop
     * @param customers         The array of customers arriving the shop
     * @param queueEntrance     The queue at the entrance of the shop
     * @param maxNumCounters    The number of counters that the shop has
     * @param numCountersServed The number of counters that are being served at a particular time
     */
    public Shop(Counter[] counters, Customer[] customers, Queue<Customer> queueEntrance, int maxNumCounters, int numCountersServed) {
        this.counters  = counters;
        this.customers = customers;
        this.queueEntrance = queueEntrance;
        this.maxNumCounters = maxNumCounters;
        this.numCountersServed = numCountersServed;
    }

    /**
     * Check if all counters full
     * @return true of all counters full
     */
    public boolean countersFull() {
        return (this.numCountersServed == this.maxNumCounters);
    }

    /**
     * Find the counter that has the smallest ID and is available
     * @param counters the counter array of counters at the shop
     * @return the counter with the smallest ID that is available
     */
    public Counter availableCounter(Counter[] counters) {
        Counter counter = counters[0];
        for (int i = 0; i < counters.length; i += 1) {
            if (counters[i].getAvailability()) {
                counter = counters[i];
                break;
            }
        }
        return counter;
    }

    /**
     * Get the counter array
     * @return the counter array
     */
    public Counter[] getCounters() {
        return this.counters;
    }

    /**
     * Update counter's availability
     * @param a true if available, false if not available
     * @param counterId ID of the counter that has its availability changed
     */
    public void setCounterAvailable(boolean a, int counterId) {
        this.counters[counterId].setAvailable(a);
        if (a == true) {
            numCountersServed--;
        } else if (a == false) {
            numCountersServed++;
        }
    }

    /**
     * Add new customers into the customers array
     * @param customer the customer array
     * @param customerId ID of the customer added
     */
    public void addCustomer(Customer customer, int customerId) {
        this.customers[customerId] = customer;
    }

    /** Get the customer array */
    public Customer[] getCustomers() {
        return this.customers;
    }

    /** Get the queue at the entrance */
    public Queue<Customer> getQueueEntrance() {
        return this.queueEntrance;
    }

    /** Dequeue the queue entrance */
    public Customer deqQueueEntrance() {
        return this.queueEntrance.deq();
    }

    /** Return true if the queue entrance is full */
    public boolean queueEntranceFull() {
        return this.queueEntrance.isFull();
    }

    /**Return true if the queue entrance is empty */
    public boolean queueEntranceEmpty() {return this.queueEntrance.isEmpty();}

    /** Return true if all the queue counters are available */
    public boolean queueCountersAvailable() {
        for(int i = 0; i < maxNumCounters; i++) {
            if (counters[i].getQueueCounter().isFull()) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    /** Get the queue counter with the shortes queue */
    public Counter getShortestQueueCounter() {
        Array<Integer> queueCounterLength = new Array<>(maxNumCounters);
        for (int i = 0; i < maxNumCounters; i++) {
            queueCounterLength.set(i,counters[i].getQueueCounterLength());
        }
        int minCounterLength = queueCounterLength.min(maxNumCounters);
        int counterId = 0;
        for (int i = 0; i < maxNumCounters; i++) {
            if(queueCounterLength.get(i) == minCounterLength) {
                counterId = i;
                break;
            }
        }
        return counters[counterId];
    }


}

