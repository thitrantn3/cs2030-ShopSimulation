/**
 * The transformer interface
 */
public interface Transformer<T,U> {
  U transform(T content);
}
