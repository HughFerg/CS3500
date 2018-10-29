import java.awt.*;

/**
 * Represents a circle to be displayed and manipulated in the animator.
 */
public class Circle extends AbstractShape {

  private int radius;

  public Circle(Color color, Point coordinates, int radius) {
    super(color, coordinates, 2 * radius, 2 * radius);
  }

  @Override
  public Circle getNextShape(AbstractShape destination, int deltaT) {
    return new Circle(this.getNextColor(destination, deltaT),
            this.getNextPoint(destination, deltaT),
            (int) ((destination.getWidth() - this.radius / deltaT) + this.radius) / 2);
  }
}
