/**
 * This class is the shop class
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY20/21 ST1
 */
class Shop {
    /**
     * An array storing the counters available at the shop
     */
    private final Counter[] counter;
    //private Customer customer;

    /**
     *
     * @param counter the counter in the shop
     */
    public Shop(Counter[] counter) {
      this.counter  = counter;
    }
}

