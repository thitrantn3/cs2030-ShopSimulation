/**
 * This class implements the event
 * when the service ends
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY20/21 ST1
 */
class ServiceEnd extends Event{

    /**
     * A tag to indicate what type of event this is.
     */
    private final int eventType;

    /**
     * The  customer associated with this event.
     */
    private final Customer customer;
    /**
     * The counter that the event takes place,
     * which is the counter that serves
     * the corresponding customer
     */
    private final Counter counter;

    /**
     * Constructor for a serice end event.
     *
     * @param eventType The indicator for which type of
     *                  event this is.
     * @param time       The time this event occurs.
     * @param customer The customer associated with this
     *                   event.
     * @param counter The counter where this event occurs
     */
    public ServiceEnd(int eventType, double time, Customer customer, Counter counter) {
      super(time);
      this.eventType = eventType;
      this.customer = customer;
      this.counter= counter;
    }

    /*
     * Different types of events.
     */
    public static final int ARRIVAL = 0;
    public static final int SERVICE_BEGIN = 1;
    public static final int SERVICE_END = 2;
    public static final int DEPARTURE = 3;

    /**
     * Returns the string representation of the event,
     * depending on the type of event.
     *
     * @return A string representing the event.
     */
    @Override
    public String toString() {
      String str = "";
      if (this.eventType == SERVICE_END) {
        str = String.format(": Customer %d service done (by Counter %d)",
            this.customer.getCustomerId(), this.counter.getId());
      } else {
          //do nothing
      }
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
      if (this.eventType == ServiceEnd.SERVICE_END) {
        // The current event is a service-end event.
        // Mark the counter is available, then schedule
        // a departure event at the current time.
        this.counter.setAvailable(true);
        return new Event[]{
          new DepartureEvent(ServiceEnd.DEPARTURE, this.getTime(), this.customer)
        };
      } else {
          // shouldn't reach here.
      }
      return new Event[] {};
    }
}
