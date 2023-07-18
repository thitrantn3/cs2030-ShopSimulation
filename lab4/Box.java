public class Box<T> {

  /**
   * Content of the box
   */
  private final T content; //the content of the box
  
  /**
   * The empty box
   */
  private static final Box<?> EMPTY_BOX = new Box<>(null);

  /**
   * Constructor for the Box
   *
   * @param: content: the content of the box
   */
  private Box(T content) {
    this.content = content;
  }

  /** Returns the string representation of the box. */
  @Override
  public String toString() {
    if (content == null) {
      return "[]";
    } 
    return String.format("[%s]", content);
  }

  /** 
   * Returns the boolean value of whether 
   * the box is equal to another object
   */
  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object a) {
    if ( this == a ) {
      return true;
    } else if ( this.content == a ) {
      return false;
    } else if ( a == null) {
      return false;
    } else if (content == null) {
      return false;
    }
    Box boxA = (Box) a;
    return content.equals(boxA.content);
  }

  /** Returns the box given the item in the box. */
  public static <T> Box<T> of(T a) {
    if (a == null) {
      return null;
    }
    return new Box<>(a);
  }

  /** Returns the empty box. */
  @SuppressWarnings("unchecked")
  public static <T> Box<T> empty() {
    return (Box<T>) EMPTY_BOX;
  }

  /** Return whether the box is empty. */
  public boolean isPresent() {
    return this != EMPTY_BOX;
  }
    
  /**
   * Return the box given the item
   * Return an empty box if the item is null.
   */
  public static <T> Box<T> ofNullable(T item) {
    if (item == null) {
      return (Box<T>) EMPTY_BOX;
    } else {
      return new Box<>(item);
    }
  }

  /** 
   * Test the box 
   * Return an empty box if the item failed the test
   * If the item passed the test, return the box with the same content.
   */
  public Box<T> filter(BooleanCondition<?super T> condition) {
    if (content != null && condition != null) {
      if (!condition.test(content)) {
        return empty();
      }
    }
    return this;
  }

  /** 
   * Transform the box into a box of another type
   */
  public <U> Box<U> map(Transformer<? super T,U> transformer) {
    if( this == EMPTY_BOX) {
      return empty();
    }
    return ofNullable(transformer.transform(content));
  }
}
