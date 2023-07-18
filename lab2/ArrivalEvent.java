/**
 * This class implements the event
 * when the customer arrives
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY20/21 ST1
 */
class ArrivalEvent extends Event{

  /**
   * The customer associated with this event.
   */
  private final Customer customer;

  /**
   * The shop where this event occurs
   */
  private final Shop shop;

  /**
   * Constructor for a serice end event.
   *
   * @param time     The time this event occurs.
   * @param customer The customer associated with this
   *                   event.
   * @param shop     The shop where this event occurs
   */
  public ArrivalEvent(double time, Customer customer, Shop shop) {
    super(time);
    this.customer = customer;
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
    Queue queueEntrance = shop.getQueueEntrance();
    String q = queueEntrance.toString();
    str = String.format(": %s arrived ",this.customer.toString());
    return super.toString() + str + q;
  }

  /**
   * The logic that the simulation should follow when simulating
   * this event.
   *
   * @return An array of new events to be simulated.
   */
  @Override
  public Event[] simulate() {
    // The current event is an arrival event.
    // Find the first available counter.
    int counterId = -1;
    if (!this.shop.countersFull()) { //if not all counters are being served
      //find the counter with lowest ID that is available
      counterId = this.shop.availableCounter(this.shop.getCounters());

      // the customer should go the the first
      // available counter and get served.
      return new Event[]{
        new ServiceBegin(this.getTime(), this.customer, counterId, this.shop)
      };
    } else if (shop.countersFull()) { //if there is no available counter
      // check if the entrance queue is full or not
      if (this.shop.getQueueEntrance().isFull()) {
        // if the queue is full, the customer departures
        return new Event[]{
          new DepartureEvent(this.getTime(), this.customer, 0, this.shop)
        };
      } else if (!this.shop.getQueueEntrance().isFull()) {
        // if the queue is not full, the customer joins the queue
        return new Event[]{
          new JoinQueue(this.getTime(), this.customer, this.shop.getQueueEntrance())
        };
      }
    }
    return new Event[] {};
  }
}
