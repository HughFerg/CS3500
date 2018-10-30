import java.awt.Color;
import java.awt.Point;

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
  public Rectangle getNextShape(AbstractShape destination, int deltaT) {
    return new Rectangle(this.getNextColor(destination, deltaT), this.getNextPoint(destination,
            deltaT), (int) (((destination.getWidth() - this.width) / deltaT) + this.width) / 2,
            (int) (((destination.getHeight() - this.height) / deltaT) + this.height) / 2);
  }
}
