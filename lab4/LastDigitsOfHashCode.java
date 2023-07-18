/**
 * The LastDigitsOfHashCode class
 */
public class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  
  /** The length of the final object. */
  private int k;

  /**
   * The constructor of this class.
   */
  public LastDigitsOfHashCode(int k) {
    this.k = k;
  }

  /** return the string of the object 
   * which has the length k
   */
  @Override
  public Integer transform(Object o) {
    int hashCode = o.hashCode();
    
    String hashString = String.valueOf(hashCode);
   
    if (hashString.length() < k) {
       return Integer.parseInt(hashString);
    }

    String newString = hashString.substring(hashString.length() - k);
    return Integer.parseInt(newString);
    }
}

