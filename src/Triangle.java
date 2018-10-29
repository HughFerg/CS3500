import java.awt.*;

/**
 * Represents an equilateral triangle to be represented in the Animator.
 */
public class Triangle extends AbstractShape {

  private int sideLength;

  public Triangle(Color color, Point coordinates, int sideLength) {
    super(color, coordinates, sideLength, (int) (Math.sqrt(3.0) * sideLength) / 2);
  }

  @Override
  public Triangle getNextShape(Shape destination, int deltaT) {
    return null;
  }
}
