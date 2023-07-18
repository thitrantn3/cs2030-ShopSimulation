/**
 * This class implements the event
 * when the service begins
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY20/21 ST1
 */
class ServiceBegin extends Event{

    /**
     * The customer associated with this event.
     */
    private final Customer customer;

    /**
     * The counterID of the counter
     * that the event takes place,
     * which is the counter that serves
     * the corresponding customer
     */
    private final Counter counter;

    /**
     * The shop where the event takes place
     */
    private final Shop shop;

    /**
     * Constructor for a service end event.
     *
     * @param time      The time this event occurs.
     * @param customer  The customer associated with this
     *                   event.
     * @param counterId The counter where this event occurs
     * @param shop      The shop where this even occurs
     */
    public ServiceBegin(double time, Customer customer, Counter counterId, Shop shop) {
        super(time);
        this.customer = customer;
        this.counter = counterId;
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
        str = String.format(": %s service begin (by S%d ",
                    this.customer.toString(), this.counter.getId());
        String q = this.counter.getQueueCounter().toString();
        String str2 = ")";
        return super.toString() + str + q + str2;
    }

    /**
     * The logic that the simulation should follow when simulating
     * this event.
     *
     * @return An array of new events to be simulated.
     */
    @Override
    public Event[] simulate() {
        // Mark the counter is unavailable, then schedule
        // a service-end event at the current time + service time.
        shop.setCounterAvailable(false,counter.getId());
        double endTime = this.getTime() + this.customer.getServiceTime();
        return new Event[]{
                new ServiceEnd(endTime, this.customer, this.counter, shop)
        };
    }
}


