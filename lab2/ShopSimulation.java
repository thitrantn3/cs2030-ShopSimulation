import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY20/21 ST1
 */
class ShopSimulation extends Simulation {
  /**
   * The list of customer arrival events to population
   * the simulation with.
   */
  private Event[] initEvents;

  /**
   * The array of counters that
   * the shop has
   */
  private Counter[] counters;

  /**
   * The array of customers that
   * enter the shop
   */
  private Customer[] customers;

  /**
   * The queue at the shop
   */
  private Queue queueEntrance;

  /**
   * The shop
   */
  private Shop shop;

  /**
   * Constructor for a shop simulation.
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a
   *           sequence of (arrival time, service time) pair, each
   *           pair represents a customer.
   */
  public ShopSimulation(Scanner sc) {
    int numOfCustomers = sc.nextInt(); //number of customers entering the shop
    initEvents = new Event[numOfCustomers];
    int numOfCounters = sc.nextInt(); //total number of counters in the shop
    int m = sc.nextInt(); //maximum allowed length of the entrance queue.

    //creating the counters array
    //and initializing each counter
    counters = new Counter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      counters[i] = new Counter(i, true);
    }

    //creating the customers array
    //and initializing each customer
    customers = new Customer[numOfCustomers];
    for (int i = 0; i < numOfCustomers; i++) {
      customers[i] = new Customer(i,0.0,0.0);
    }

    //creating a queue
    //initialize the queue when the queue is empty
    //there is no items in the queue, maxlen = m
    queueEntrance = new Queue(m);

    //initializing the shop
    Shop shop = new Shop(counters,customers,queueEntrance,numOfCounters,0);

    //for each customer
    //read in the arrival time and service time
    //update the statistics in the shop
    //and start the arrival event
    for (int i = 0; i < customers.length; i++) {
      customers[i].setArrivalTime(sc.nextDouble());
      customers[i].setServiceTime(sc.nextDouble());
      shop.addCustomer(customers[i],i);
      initEvents[i] = new ArrivalEvent(this.customers[i].getArrivalTime(),
          this.customers[i], shop);
    }
  }

  /**
   * Retrieve an array of events to populate the
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}

