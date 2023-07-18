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
  private Queue queue;
  
  /**
   * The customer associated with this event.
   */
  private final Customer customer;

  public JoinQueue(double time, Customer customer, Queue queue ) {
    super(time);
    this.queue = queue;
    this.customer = customer;
  }

  /**
   * Returns the string representation of the event,
   * depending on the type of the event.
   *
   * @return A string representation of the event.
   */
  @Override
  public String toString() {
    String str = "";
    String q = queue.toString();
    str = String.format(": %s joined queue ", this.customer.toString());
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
    queue.enq(customer.toString());
    return new Event[] {};
  }
}

