/**
 * This class implements the event
 * when the customer departures
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY20/21 ST1
 */
class DepartureEvent extends Event {
    /**
     * The customer associated with this event.
     */
    private final Customer customer;

    /**
     * Counter that the customer leaves from
     */
    private final Counter counter;

    /**
     * The shop where this event occurs
     */
    private final Shop shop;

    /** Queue type. */
    private static final int COUNTER = 1;
    private static final int SHOP = 2;

    /**
     * Constructor for a service end event.
     *
     * @param time     The time this event occurs.
     * @param customer The customer associated with this
     *                 event.
     * @param counter  The ID of the counter where
     *                 this event occurs
     * @param shop     The shop where this event occurs
     */
    public DepartureEvent(double time, Customer customer, Counter counter, Shop shop) {
        super(time);
        this.customer = customer;
        this.counter = counter;
        this.shop = shop;
    }

    /**
     * Returns the string representation of the event,
     * depending on the type of event.
     *
     * @return A string representing the event.
     */
    @Override
    public String toString() {
        String str = "";
        str = String.format(": %s departed", this.customer.toString());
        return super.toString() + str;
    }

    /**
     * The logic that the simulation should follow when simulating
     * this event.
     *
     * @return An array of new events to be simulated.
     */
    @Override
    public Event[] simulate() {
        //only execute this if not all the counters are being served
        // then check if the entrance queue is empty
        //if it is not empty
        if(!shop.countersFull()) {
            if (!this.counter.queueCounterEmpty() && (this.counter.getQueueCounterSize() != 0)) {
                Customer newCustomer = this.counter.deqQueueCounter(); //the next customer being served
                int newCustomerId = 0;
                for (int i = 0; i < this.shop.getCustomers().length; i++) {
                    if (i == newCustomer.getCustomerId()) {
                        newCustomerId = i;
                    }
                }
                if (this.shop.queueEntranceEmpty()) { //if the there is no customer at the queue entrance
                    return new Event[]{
                            new ServiceBegin(this.getTime(),
                                    this.shop.getCustomers()[newCustomerId], this.counter, this.shop)
                    };
                } else {//if there are customers waiting at the queue entrance
                    //the customer at queue entrance joins the counter's queue
                    Customer c = this.shop.deqQueueEntrance();
                    return new Event[]{
                            new JoinQueue(COUNTER, this.getTime(), c, this.counter.getQueueCounter(), this.counter),
                            new ServiceBegin(this.getTime(), this.shop.getCustomers()[newCustomerId], this.counter, this.shop)
                    };
                }
            } else if (this.counter.queueCounterEmpty() && !this.shop.getQueueEntrance().isEmpty()) {
                Customer c = this.shop.deqQueueEntrance();
                int newCustomerId = 0;
                for (int i = 0; i < this.shop.getCustomers().length; i++) {
                    if (i == c.getCustomerId()) {
                        newCustomerId = i;
                    }
                }
                return new Event[]{
                        new ServiceBegin(this.getTime(), this.shop.getCustomers()[newCustomerId], this.counter, this.shop)
                };
            }
        } else {
            //do nothing
        }
        return new Event[] {};
    }

}

