import java.awt.*;

/**
 * Represents a Rectangle to be used in the Animator.
 */
public class Rectangle extends AbstractShape {

  private int width;
  private int height;

  public Rectangle(int width, int height, Color color, Point coordinates) {
    super(color, coordinates);

    if (this.height > 0 && this.width > 0) {
      this.width = width;
      this.height = height;
    } else {
      throw new IllegalArgumentException("Cannot have width or height less than 1.");
    }
  }
}
