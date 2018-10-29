import java.awt.Point;
import java.awt.Color;

/**
 * Represents an abstract shape to be created in the Animator.
 */
public abstract class AbstractShape {

  private Point coordinates;
  private Color color;
  private int width;
  private int height;

  public AbstractShape(Color color, Point coordinates, int width, int height) {

    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width and height must be greater than 0.");
    } else {
      this.color = color;
      this.coordinates = coordinates;
      this.width = width;
      this.height = height;
    }
  }

  protected Point getCoordinates() {
    return this.coordinates;
  }

  protected Color getColor() {
    return this.color;
  }

  protected int getWidth() {
    return this.width;
  }

  protected int getHeight() {
    return this.height;
  }

  protected Color getNextColor(AbstractShape destination, int deltaT) {
    return new Color((destination.getColor().getRed() - this.color.getRed() / deltaT) + this.color.getRed(),
            (destination.getColor().getGreen() - this.color.getGreen() / deltaT) + this.color.getGreen(),
            (destination.getColor().getBlue() - this.color.getBlue() / deltaT) + this.color.getBlue());
  }

  protected Point getNextPoint(AbstractShape destination, int deltaT) {
    return new Point((int) ((destination.getCoordinates().getX() - this.getCoordinates().getX() / deltaT) + this.getCoordinates().getX()),
            (int) ((destination.getCoordinates().getY() - this.getCoordinates().getY() / deltaT) + this.getCoordinates().getY()));
  }

  protected abstract AbstractShape getNextShape(AbstractShape destination, int deltaT);
}
