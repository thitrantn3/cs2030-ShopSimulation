/**
 * CS2030 Lab 5
 * AY20/21 Special Term 1
 *
 * @author Tran Ngoc Nha Thi (Group B04)
 */

package cs2030.fp;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;

public abstract class Maybe<T> {
 
  static public class None<T> extends Maybe<T> {
    @Override
    public String toString() {
      return "[]";
    }

    public boolean equals(Object none) {
      if (none == null) {
        return false;
      }
      return this == none;
    }
    
    public boolean equals(Some some) {
      return false;
    }
    
    @Override 
    protected T get() throws NoSuchElementException {
      throw new NoSuchElementException();
    }
    
    @Override
    public Maybe<T> filter(BooleanCondition<? super T> condition) {
      return (None) NONE;
    }
   
    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer) {
      return (None) NONE;
    }

    @Override 
    public <U> Maybe<U> flatMap(Transformer<?super T, ? extends Maybe<? extends U>> transformer) {
      return (None) NONE;
    }

    @Override
    public <U extends T> U orElse(U value) {
      return value;
    }

    @Override
    public <U extends T> U orElseGet(Producer<? extends T> producer) {
      return (U) producer.produce();
    }
  }

  private static final None NONE = new None();

  static public class Some<T> extends Maybe<T> {
    private final T t;
    private Some(T t) {
      this.t = t;
    }

    @Override
    public String toString() {
      if (this.t == null) {
        return "[null]";
      }
      return String.format("[%s]", this.t);
    }

    @Override
    public boolean equals(Object a) {
      if ( a != NONE) {
        Some<T> someA = (Some<T>) a;
        if (this == a) {
          return true;
        } else if (this.t == a) {
          return false;
        } else if (a == null) {
          return false;
        } else if (this == null) {
          return false;
        } else if (this.t == null) {
          if (someA.t == null) {
            return true;
          } else {
            return false;
          } 
        }
        return someA.t.equals(this.t);
      }

      return false;
    }

    @Override 
    protected T get() {
      return this.t;
    }
    
    @Override
    public Maybe<T> filter(BooleanCondition<? super T> condition) {
      if (t != null && condition != null) {
        if (!condition.test(t)) {
        return (None) NONE;
        }
      }
        return (Maybe<T>) this;
    }
  
    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer)
      throws NullPointerException {
      if (this == null ){
        return (None) NONE;
      } 
      return new Some<U>(transformer.transform(this.t));
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> transformer)
      throws NullPointerException {
      if (this == null) {
        throw new NullPointerException();
      } 
      if (transformer.transform(this.t) instanceof Maybe) {
        return (Maybe<U>) transformer.transform(this.t);
      }
      return (Maybe<U>) some(transformer.transform(this.t));
    }

    @Override
    public <U extends T> U orElse (U value) {
      return (U) this.t;
    }

    @Override
    public <U extends T> U orElseGet(Producer<? extends T> producer) {
      return (U) this.t;
    }
  }

  public static None none() {
    return (None) NONE;
  }

  public static <T> Some<T> some(T t) {
    return new Some<>(t);
  }

  public static <T> Maybe<T> of(T o) {
    if (o != null) {
      return new Some<>(o);
    } else {
      return (None) none();
    }
  }

  protected abstract T get();

  public abstract  Maybe<T> filter(BooleanCondition<? super T> condition);

  public abstract <U> Maybe<U> map(Transformer<?super T, ? extends U> transformer);

  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> transformer);
  
  public abstract <U extends T> U orElse(U value);

  public abstract <U extends T> U orElseGet(Producer<? extends T> producer);
}
