/**
 * This class is the customer class
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY20/21 ST1
 */
class Customer {
    /**
     * The id of a customer associated with this event.
     * First customer has id 0. Next is 1, 2, etc.
     * */
   private final int customerId;

    /**
     * The arrival time of the customer associated
     * this event.
     */
   private double arrivalTime;

    /**
     * The service time of the customer associated
     * this event.
     */
   private double serviceTime;

    /**
     *
     * @param customerId The customerId associated with this customer.
     * @param arrivalTime The arrival time associated with this customer.
     * @param serviceTime The service time associated with this customer.
     */
   public Customer(int customerId, double arrivalTime, double serviceTime) {
     this.customerId = customerId;
     this.arrivalTime = arrivalTime;
     this.serviceTime = serviceTime;
   }

    /**
     * Returning the customer's id
     * @return An integer representing the customer's id
     */
   public int getCustomerId() {
     return customerId;
   }

   /**
    * Returning the customer's arrival time
    * @return An integer representing the customer's arrival time
    */
   public double getArrivalTime() {
     return arrivalTime;
   }

   /**
    * Returning the customer's service time
    * @return An integer representing the customer's service
    */
   public double getServiceTime() {
     return serviceTime;
   }

    /**
     * Set the arrival time of the customer
     * @return nothing
     */
   public void setArrivalTime(double time) {
     this.arrivalTime = time;
   }

   /**
    * Set the service time of the customer
    * @return nothing
    */
   public void setServiceTime(double time) {
     this.serviceTime = time;
   }
}

