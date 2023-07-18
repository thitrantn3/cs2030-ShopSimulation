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
    private final Queue queueEntrance;

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
    public Shop(Counter[] counters, Customer[] customers, Queue queueEntrance, int maxNumCounters, int numCountersServed) {
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
     * @return the smallest ID of the counter that is available
     */
    public int availableCounter(Counter[] counters) {
      int counterId = 0;
      for (int i = 0; i < counters.length; i += 1) {
        if (counters[i].getAvailability()) {
          counterId = i;
          break;
        }
      }
      return counterId;
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

    /**
     * Get the customer array
     * @return the customer array
     */
    public Customer[] getCustomers() {
      return this.customers;
    }

    /**
     * Get the queue at the entrance
     * @return the queue at the entrance
     */
    public Queue getQueueEntrance() {
      return this.queueEntrance;
    }
}

