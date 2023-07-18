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
  private final int counterId;

  /**
   * The shop where this event occurs
   */
  private final Shop shop;

  /**
   * Constructor for a service end event.
   *
   * @param time       The time this event occurs.
   * @param customer The customer associated with this
   *                   event.
   * @param counterId The ID of the counter where
   *                  this event occurs
   * @param shop      The shop where this event occurs
   */
  public DepartureEvent(double time, Customer customer, int counterId, Shop shop) {
    super(time);
    this.customer = customer;
    this.counterId = counterId;
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
      if(!this.shop.getQueueEntrance().isEmpty()) {
        String newCustomerId = (String) this.shop.getQueueEntrance().deq();
        int newCustomer = 0;
        for (int i = 0; i < this.shop.getCustomers().length; i++) {
           if (this.shop.getCustomers()[i].toString().equals(newCustomerId)) {
             newCustomer = i;
           }
        }
        return new Event[]{
          new ServiceBegin(this.getTime(),this.shop.getCustomers()[newCustomer], 
              this.counterId, this.shop)
        };
      } else { //if all the counters are being served
          //do nothing
      }
    } else if (this.shop.getQueueEntrance().isEmpty()){ //if the entrance queue is empty
        // do nothing
    }
    return new Event[] {};
  }
}
