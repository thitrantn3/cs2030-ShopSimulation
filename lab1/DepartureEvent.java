/**
 * This class implements the event
 * when the customer departures
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY20/21 ST1
 */
class DepartureEvent extends Event {
    /**
     * A tag to indicate what type of event this is.
     */
    private final int eventType;

    /**
     * The customer associated with this event.
     */
    private final Customer customer;

    /**
     * Constructor for a serice end event.
     *
     * @param eventType The indicator for which type of
     *                  event this is.
     * @param time       The time this event occurs.
     * @param customer The customer associated with this
     *                   event.
     */
    public DepartureEvent(int eventType, double time, Customer customer) {
      super(time);
      this.customer = customer;
      this.eventType = eventType;
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
      if (this.eventType == DepartureEvent.DEPARTURE) {
        str = String.format(": Customer %d departed", this.customer.getCustomerId());
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
      if (this.eventType == DepartureEvent.DEPARTURE) {
        // do nothing
      } else {
          // shouldn't reach here.
      }
      return new Event[] {};
    }

}

