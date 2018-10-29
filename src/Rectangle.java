import java.awt.*;

/**
 * Represents a Rectangle to be used in the Animator.
 */
public class Rectangle extends AbstractShape {

  private int width;
  private int height;

  public Rectangle(Color color, Point coordinates, int width, int height) {
    super(color, coordinates, width, height);
  }

  @Override
  public Rectangle getNextShape(Shape destination, int deltaT) {
    return null;
  }
}
