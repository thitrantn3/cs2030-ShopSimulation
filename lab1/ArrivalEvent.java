/**
 * This class implements the event
 * when the customer arrives
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY20/21 ST1
 */
class ArrivalEvent extends Event{

    /**
     * A tag to indicate what type of event this is.
     */
    private final int eventType;

    /**
     * The customer associated with this event.
     */
    private final Customer customer;

    /**
     * An array that stores all the counter
     * that the store has
     */
    private final Counter[] counter;

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
    public ArrivalEvent(int eventType, double time, Customer customer, Counter[] counter) {
      super(time);
      this.eventType = eventType;
      this.customer = customer;
      this.counter = counter;
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
      if (this.eventType == ARRIVAL) {
        str = String.format(": Customer %d arrives",
            this.customer.getCustomerId());
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
      if (this.eventType == ArrivalEvent.ARRIVAL) {
        // The current event is an arrival event.
        // Find the first available counter.
        int counterId = -1;
        for (int i = 0; i < counter.length; i += 1) {
          if (counter[i].getAvailability()) {
            counterId = i;
            break;
          }
        }
          if (counterId == -1) {
            // If no such counter can be found, the customer
            // should depart.
            return new Event[]{
              new DepartureEvent(ArrivalEvent.DEPARTURE, this.getTime(),
                  this.customer) 
            };
          } else {
              // Else, the customer should go the the first
              // available counter and get served.
              return new Event[]{
                new ServiceBegin(ArrivalEvent.SERVICE_BEGIN, this.getTime(),
                    this.customer, counter[counterId])
              };
          }
      } else {
          // shouldn't reach here.
          }
      return new Event[] {};
    }
}

