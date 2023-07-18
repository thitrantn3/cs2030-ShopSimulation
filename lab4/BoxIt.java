/**
 * This is the BoxIt class
 */
public class BoxIt<T> implements Transformer<T,Box<T>> {
  public Box<T> transform(T content) {
    return Box.ofNullable(content);
  }
}

