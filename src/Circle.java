import java.awt.*;

/**
 * Represents a circle to be displayed and manipulated in the animator.
 */
public class Circle extends AbstractShape {

  private int radius;

  public Circle(int radius, Color color, Point coordinates) {
    super(color, coordinates, 2 * radius, 2 * radius);
  }

  @Override
  public Circle getNextShape(Shape destination, int deltaT) {
    return null;
  }
}
