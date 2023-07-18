package cs2030.fp;

/**
 * A Lazy<T> class
 *
 * @author Thi Tran (Group B04)
 * @version CS2030 AY 20/21 Special Term 1
 *
 */
public class Lazy<T> {
  
  /**
   * A private field of the producer
   * that is used to calculate the value
   *
   */
  private Producer<T> producer;

  /**
   * A private field of the value of an object 
   * of this Lazy<T> class
   *
   */
  private Maybe<T> value;

  /**
   * A public constructor to initialise the Lazy<T> class
   *
   * @param value The value of a lazy object
   * @param producer The producer used to calculate the value
   *
   */
  public Lazy(Maybe<T> value, Producer<T> producer) {
    this.value = value;
    this.producer = producer;
  }

  /**
   * A static method that initialises the Lazy object with the given value v
   *
   * @param v The given value v 
   * @return a new Lazy object with the value given
   */
  static public <T> Lazy<T> of(T v) {
    if (v == (Object) null) {
      return new Lazy<T>(null,null);
    }
    Producer<T> k = () -> v;
    return new Lazy<T>(Maybe.of(v), k);
  }

  /**
   * A static method that takes in a producer that 
   * produces the value when needed
   *
   * @param s The producer
   * @return a new Lazy object with the producer given
   */
  static public <T> Lazy<T> of(Producer<T> s) {
    return new Lazy<T>(Maybe.none(), s);
  }

  /**
   * A method that is called
   * when the value is needed
   *
   * @return The value if the value is available
   *         if the value is not available, 
   *         calculate the value then return the value
   *
   */
  public T get() {
    if (this.value != Maybe.none() && this.value != null) {
      return this.value.orElseGet(null);
    } else if( this.value == null) {
      return null;
    }
    T t= this.producer.produce();
    if (t == null) {
      return null;
    } 
      this.value = Maybe.of(t);
      return this.value.orElseGet(null);
  }
  
  /**
   * The toString() method to return 
   * the string representation of the Lazy object
   *
   * @return the string representation of the object, if the value if available
   *         "?" if the value is not available
   */
  @Override
  public String toString() {
    if (this.value != Maybe.none() && this.value != null) { 
    return String.format("%s", this.value.orElseGet(null));
    } else if (this.value == null) {
      return "null";
    }
    return "?";
  }
  
  /**
   * A method that changes the producer of the Lazy object 
   * with the given transformer
   *
   * @param A given transformer
   * @return a new Lazy object with the new producer
   */
  public <U> Lazy<U> map(Transformer<?super T, ?extends U> transformer) {
    Producer<U> producerMap = () -> transformer.transform(this.get());
    return new Lazy<U>(Maybe.none(), producerMap);
  }

  /**
   * A method that changes the producer of the Lazy object
   * with the given transformer, the parameter for 
   * the returned producer in this method is an instance of Lazy
   *
   * @param A given transformer
   * @return a new Lazy object with the new producer
   */
  public <U> Lazy<U> flatMap(Transformer<?super T, ? extends Lazy<?extends U>> transformer) {
    Producer<U> producerFlatMap = () -> transformer.transform(this.get()).get();
    return new Lazy<U>(Maybe.none(), producerFlatMap);
  }

  /**
   * A methd that lazily test if the value passes or not, returning
   * a new Producer<Boolean> object
   *
   * @param condition the condition of the tes
   * @return a new Lazy<Boolean> object with the new producer
   */
  public Lazy<Boolean> filter(BooleanCondition<T> condition) {
    Producer<Boolean> producerBoolean = () -> condition.test(this.value.orElseGet(null));
    return new Lazy<Boolean>(Maybe.none(), producerBoolean);
  }

  /** 
   * A method that compares a given Lazy object 
   * with another Lazy object
   *
   * @param o the object compared with the give Lazy object
   * @return true if the object is Lazy and the value inside is 
   *         equal to the value of the given Lazy object,
   *         returns false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    } else if ( !(o instanceof Lazy)){
      return false;
    }
    Lazy<T> lazyObj = (Lazy<T>) o;
    if (this.value == Maybe.none()) {
      T t = this.producer.produce();
      this.value = Maybe.of(t);
    }
      return this.value.orElseGet(null) == lazyObj.get();
  }

  /** 
   * A method that takes in another Lazy object and a combiner implementation
   * to lazily combine the two Lazy objects, return a new Lazy object
   *
   * @param lazyO the second lazy object
   * @param combiner the combiner implementation
   *
   * @return the new Lazy object
   */
  public <S,R> Lazy<R> combine(Lazy<S> lazyO, Combiner<?super T,S,R> combiner) {
    R combined = combiner.combine(this.get(), lazyO.get());
    Producer<R> producerR = () -> combined;
    return new Lazy<R>(Maybe.none(),producerR);
  }
}
