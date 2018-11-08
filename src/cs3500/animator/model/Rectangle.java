package cs3500.animator.model;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Represents a Rectangle to be used in the Animator.
 */
public class Rectangle extends AbstractShape {

  private int width;
  private int height;

  public Rectangle(Color color, Point coordinates, int width, int height) {
    super(color, coordinates, width, height);
    this.width = width;
    this.height = height;
  }

  @Override
  public Rectangle getNextShape(AbstractShape destination, int deltaT) {
    return new Rectangle(this.getNextColor(destination, deltaT), this.getNextPoint(destination,
            deltaT), (int) (((destination.getWidth() - this.width) / deltaT) + this.width),
            (int) (((destination.getHeight() - this.height) / deltaT) + this.height));
  }

  @Override
  protected void getDrawing(Graphics2D g) {

    Shape r = new Rectangle2D.Double(this.coordinates.x,
            this.coordinates.y, this.width, this.height);

    g.setPaint(this.color);
    g.fill(r);
    g.draw(r);
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

