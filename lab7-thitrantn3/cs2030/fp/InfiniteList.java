/**
 * InfiniteList class
 *
 */
package cs2030.fp;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class InfiniteList<T> {

  /** The head of the list*/
  private final Lazy<Maybe<T>> head;

  /** The tail of the list */
  private final Lazy<InfiniteList<T>> tail;

  /** Initialising the list*/
  InfiniteList() { 
    head = null; 
    tail = null;
  }

  /**
   * Method to generate the list with given producer
   *
   * @param producer the producer to generate the infinitelist
   * @return a new infinitelist 
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    Lazy<Maybe<T>> newHead = Lazy.of(() -> Maybe.some(producer.produce()));
    Lazy<InfiniteList<T>> newTail = Lazy.of(() ->generate(producer));
    return new InfiniteList<T>(newHead, newTail);
  }

  /**
   * the iterate method to produce an infinite list with a given transformer and a seed
   *
   * @param seed the given seed
   * @param next the given transformer
   * @return a new infinite list
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    return new InfiniteList<T>(
        Lazy.of(() -> Maybe.of(seed)),
        Lazy.of(() -> InfiniteList.iterate(next.transform(seed), next))
        );
  }

  /**
   * the constructor for the infinite list
   *
   * @param head the head of the list
   * @param tail the producer for the tail of the list
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    this.head  = Lazy.of(Maybe.of(head));
    this.tail = Lazy.of(() -> tail.produce());
  }

  /**
   * the constructor for the infinite list
   *
   * @param head the head of the lis
   * @param tail the tail of the list
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    this.head = head;
    this.tail = tail;
  }

 /**
  * method to call the head of the list
  *
  * @return the head of the list
  */
  public T head() {
    return this.head.get().orElseGet( () -> this.tail.get().head());
  }

  /**
   * method to return the tail of the list
   *
   * @return the infinite list of the tail of the given infinite list
   */
  public InfiniteList<T> tail() {
    return this.head.get().map(x -> this.tail.get()).orElseGet(() -> this.tail.get().tail());

  }

  /**
   * the map method to map the given infinite list with a transformer
   * @param mapper the transformer
   * @return a new infinite list
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    return new InfiniteList<R>(
        head.map(x -> x.map(mapper)), 
        tail.map(x -> x.map(mapper))
        );
  }

  /**
   * a filter method to return a new infinite list with a booleancondition
   *
   * @param predicate the given boolean condition
   * @return a new infinite list
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    return new InfiniteList<T>(
        head.map(x -> x.filter(predicate)), 
        tail.map(x -> x.filter(predicate))
        );
  }
  /** the empty list */
  private static InfiniteList<?> EMPTY = (InfiniteList<?>) new EmptyList<>();

  /** static method to empty the infinite list */
  public static <T> InfiniteList<T> empty() {
    return (InfiniteList<T>) EMPTY;
  }

  /** 
   * a limit method to limit the length of the infinite list given a length value
   *
   * @param n the length
   * @return a new infinite list
   */
  public InfiniteList<T> limit(long n) {
    if (n < 1) {
      return InfiniteList.empty();
    }
    return new InfiniteList<>(Lazy.of( () -> this.head.get()), 
     Lazy.of(() -> {
       if  (n == 1) {
         return InfiniteList.empty();
       }
       InfiniteList<T> theTail = this.tail.get();
       if (theTail.isEmpty()) {
         return theTail;
       } else {
         return theTail.limit(n - ((this.head.get() == Maybe.none() || this.head() == null)? 0:1));
       }
    }));
  }

  /**
   * a takeWhile method that takes in a booleancondition
   * and truncates the list as soon as it finds an element that evaluates the condition to false
   *
   * @param predicate the boolean condition
   * @return the truncated infinite list
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    if (this instanceof EmptyList) {
      return InfiniteList.empty();
    }
    T newHead = this.head.get().filter(predicate).get();
    Producer<InfiniteList<T>> newTail = 
      () -> (Maybe.of(newHead) == Maybe.none() || newHead == null) ?
      new EmptyList<>() : this.tail().takeWhile(predicate);
    return new InfiniteList<>(newHead, newTail);
  }

  /** 
   * a method to check if the infinite list is empty
   *
   * @return the boolean value if the list is empty
   */
  public boolean isEmpty() {
    if (this == null) {
      return true;
    }
    if (this instanceof EmptyList) {
      return true;
    }
    if (this instanceof InfiniteList && this.head == null & this.tail == null) {
      return true;
    }
    return false;
  }

  /**
   * the reduce method to reduce the infinite list given an identity and a combiner
   *
   * @param identity a given identity
   * @param accumulator the combiner
   *
   * @return the final value after the infinite list is reduced
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    U t = null;
    InfiniteList<T> list = this;

    while(list != list.empty()) {
      if(list.head() != null) {
        if (t == null) {
          t = accumulator.combine(identity,list.head());
        } else {
          t = accumulator.combine(t, list.head());
        }
        list = list.tail();
      }
    }
    return t;
  }

  /**
   * the count method to return the number of valyues in the infinite list
   * @return a long value of the count
   */
  public long count() {
    if (!this.isEmpty()) {
    return (this.head() != null || this.head.get() != Maybe.none()) 
      ? (1+this.tail().count()) : this.tail().count();
    }
    return 0;
  }

  /** 
   * the toList method that collects the elements in the infinite list to a list
   *
   * @return a new list
   */
  public List<T> toList() {
    InfiniteList<T> list = this;
    List<T> newList = new ArrayList<>();
    while( this != this.empty()) {
      if(this.head() != null) {
        newList.add(list.head());
      }
      list = list.tail();
    }
    return newList;
  }

  /** the string representation of the infinite list*/
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }

  /** 
   * the nested static emptylist class 
   */
  static class EmptyList<T> extends InfiniteList<T> {
    /** the filter method 
     * @return an empty list
     */
     @Override
     public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
       return new EmptyList<T>();
     }

     /** the map method
      * @return an empty list
      */
     @Override
     public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
       return new EmptyList<R>();
     }

     /** the limit method 
      * @return an empty list
      */
     @Override
     public InfiniteList<T> limit(long n) {
       return new EmptyList<T>();
     }

     /**
      * the method to call the head 
      */
     @Override
     public T head() throws NoSuchElementException {
       throw new NoSuchElementException();
     }

     /**
      * the method to call the tail */
     @Override
     public InfiniteList<T> tail() throws NoSuchElementException{
       throw new NoSuchElementException();
      }
  }
}
