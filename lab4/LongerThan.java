/** 
 * LongerThan class
 */
public class LongerThan implements BooleanCondition<String> {
  
  /** length limit of the string. */
  private int limit;

  /** Constructor of this class. */
  public LongerThan(int limit) {
    this.limit = limit;
  }

  /** return if the string passed in is longer than the given limit. */
  @Override
  public boolean test(String str) {
    return (str.length() > this.limit) && (str.length() != 0);
  }
}
