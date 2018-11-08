package cs3500.animator.model;

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

  @Override
  public String generateSVGHeader() {
    return String.format("<rect id=\"%s\" x=\"%1$s\" y=\"%2$s\" width=\"%3$s\" height=\"%4$s\" " +
                    "fill=\"rgb(%5$s,%6$s,%7$s)\" visibility=\"visible\" >\n", getCoordinates().x,
            getCoordinates().y, width, height, getColor().getRed(), getColor().getGreen(),
            getColor().getBlue());
  }

  public String generateEndTag() {
    return "</rect>\n";
  }
}

