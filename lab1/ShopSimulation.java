import java.util.Scanner;

/**
 * This class implements a shop simulation.
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY20/21 ST1
 */
class ShopSimulation extends Simulation {
    /**
     * The list of customer arrival events to populate
     * the simulation with.
     */
    public Event[] initEvents;

    /**
     * The array of counters that
     * are available at the shop
     */
    public Counter[] counters;
    
    /**
     * The array of customers that
     * enter the shop
     */
    public Customer[] customers;

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
      int numOfCustomers = sc.nextInt();
      initEvents = new Event[numOfCustomers];
      int numOfCounters = sc.nextInt();

      //creating the counters array
      //and initializing each counter
      //counters = new Counter[numOfCounters];
      for (int i = 0; i < numOfCounters; i++) {
        counters[i] = new Counter(i, true);
      }

      //creating the customers array
      //and initializing each customer
      customers = new Customer[numOfCustomers];
      for (int i = 0; i < numOfCustomers; i++) {
        customers[i] = new Customer(i,0.0,0.0);
      }

      //for each customer
      //read in the arrival time and service time
      //and start the arrival event
      for (int i = 0; i < customers.length; i++) {
        customers[i].setArrivalTime(sc.nextDouble());
        customers[i].setServiceTime(sc.nextDouble());
        initEvents[i] = new ArrivalEvent(ArrivalEvent.ARRIVAL, 
            this.customers[i].getArrivalTime(),
            this.customers[i], counters);
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

