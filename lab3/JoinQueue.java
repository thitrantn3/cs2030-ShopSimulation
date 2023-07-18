/**
 * This class implements the event
 * when the customer join the queue
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY20/21 ST1
 */
class JoinQueue extends Event{
    /**
     * The queue at the shop
     */
    private final Queue<Customer> queue;

    /**
     * The queue type
     */
    private final int queueType;

    /**
     * The customer associated with this event.
     */
    private final Customer customer;

    /**
     * The counter where the customer joins the queue
     */
    private Counter counter;

    /** Queue type. */
    private static final int COUNTER = 1;
    private static final int SHOP = 2;

    public JoinQueue(int queueType, double time, Customer customer, Queue<Customer> queue, Counter counter ) {
        super(time);
        this.queueType = queueType;
        this.queue = queue;
        this.customer = customer;
        this.counter = counter;
    }

    @Override
    public String toString() {
        String strShop = "";
        strShop = String.format(": %s joined shop queue ", this.customer.toString());
        String strCounter = "";
        if (this.counter != null) {
            strCounter = String.format(": %s joined counter queue (at S%d ", this.customer.toString(), this.counter.getId());
        }
        String q = queue.toString();
        String str = ")";
        if (queueType == COUNTER) {
            return super.toString() + strCounter + q + str;
        }
            return super.toString() + strShop + q;
    }

    /**
     * The logic that the simulation should follow when simulating
     * this event.
     *
     * @return An array of new events to be simulated.
     */
    @Override
    public Event[] simulate() {
        if(this.queue.size() != 0) {
            queue.enq(customer);
            return new Event[]{};
        } else {
            //do nothing
        }
        return new Event[]{};
    }
}

