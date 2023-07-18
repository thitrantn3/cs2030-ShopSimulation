/**
 * This class implements the event
 * when the service ends
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY20/21 ST1
 */
class ServiceEnd extends Event{

    /**
     * The  customer associated with this event.
     */
    private final Customer customer;

    /**
     * The counter that the event takes place,
     * which is the counter that serves
     * the corresponding customer
     */
    private final int counterId;

    /**
     * The shop
     */
    private final Shop shop;

    /**
     * Constructor for a serice end event.
     *
     * @param time      The time this event occurs.
     * @param customer  The customer associated with this
     *                   event.
     * @param counterId The counter where this event occurs
     * @param shop      The shop where this event occurs
     */
    public ServiceEnd(double time, Customer customer, int counterId, Shop shop) {
      super(time);
      this.customer = customer;
      this.counterId= counterId;
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
      str = String.format(": %s service done (by S%d)",
          this.customer.toString(), this.counterId);
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
      // The current event is a service-end event.
      // Mark the counter is available, then schedule
      // a departure event at the current time.
      this.shop.setCounterAvailable(true,counterId);
      return new Event[]{
        new DepartureEvent(this.getTime(), this.customer, this.counterId, this.shop)
      };
    }
}
