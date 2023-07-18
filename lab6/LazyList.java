import cs2030.fp.Transformer;
import java.util.ArrayList;
import java.util.List;
import cs2030.fp.Lazy;
import cs2030.fp.Maybe;
import cs2030.fp.Producer;
/**
 * A wrapper around an lazily evaluated list that
 * can be generated with a lambda expression.
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY 20/21 Special Term 1
 *
 */
class LazyList<T> {
  /** The wrapped java.util.List object */
  private List<Lazy<T>> list;

  /** 
   * A private constructor to initialize the list to the given one. 
   *
   * @param list The given java.util.List to wrap around.
   */
  private LazyList(List<Lazy<T>> list) {
    this.list = list;
  }

  /** 
   * Generate the content of the list.  Given x and a lambda f, 
   * generate the list of n elements as [x, f(x), f(f(x)), f(f(f(x))), 
   * ... ]
   *
   * @param <T> The type of the elements in the list.
   * @param n The number of elements.
   * @param seed The first element.
   * @param f The transformation function on the elements.
   * @return The created list.
   */
  public static <T> LazyList<T> generate(int n, T seed, Transformer<T,T> f) {
    LazyList<T> lazyList = new LazyList<>(new ArrayList<>());
    T curr = seed;
    Producer<T> producer = () -> curr;
    Lazy<T> first = Lazy.of(producer);
    first.get();
    lazyList.list.add(0,first);
    for (int i = 1; i < n; i++) {
      lazyList.list.add(i, lazyList.list.get(i-1).map(f));;
    }
    return lazyList;
  }

  /** 
   * Return the element at index i of the list.  
   *
   * @param i The index of the element to retrieved (0 for the 1st element).
   * @return The element at index i.
   */
  public T get(int i) {
    return this.list.get(i).get();
  }

  /** 
   * Find the index of a given element.
   *
   * @param v The value of the element to look for.
   * @return The index of the element in the list.  -1 is element is not in the list.
   */
  public int indexOf(T v) {
    int index = 0;
    for (int i = 0; i< this.list.size(); i++) {
      if(this.list.get(i).get().equals(v)) {
        return index;
      }
    }
    return index;
  }

  /** 
   * Return the string representation of the list.
   *
   * @return The string representation of the list.
   */
  @Override
  public String toString() {
    return this.list.toString();
  }
}
