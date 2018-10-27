import java.awt.Point;
import java.awt.Color;

/**
 * Represents an abstract shape to be created in the Animator.
 */
public abstract class AbstractShape {

  private Point coordinates;
  private Color color;

  public AbstractShape(Color color, Point coordinates) {
    this.color = color;
    this.coordinates = coordinates;
  }
}
