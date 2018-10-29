import java.awt.*;

/**
 * Represents a circle to be displayed and manipulated in the animator.
 */
public class Oval extends AbstractShape {

  private int xRadius;
  private int yRadius;

  public Oval(Color color, Point coordinates, int xRadius, int yRadius) {
    super(color, coordinates, 2 * xRadius, 2 * yRadius);
  }

  @Override
  public Oval getNextShape(AbstractShape destination, int deltaT) {
    return new Oval(this.getNextColor(destination, deltaT), this.getNextPoint(destination, deltaT),
            (int) ((destination.getWidth() - this.xRadius / deltaT) + this.xRadius) / 2,
            (int) ((destination.getHeight() - this.yRadius / deltaT) + this.yRadius) / 2);
  }
}
