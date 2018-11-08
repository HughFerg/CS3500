package cs3500.animator.model;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Represents a circle to be displayed and manipulated in the animator.
 */
public class Circle extends AbstractShape {

  private int radius;

  public Circle(Color color, Point coordinates, int radius) {
    super(color, coordinates, 2 * radius, 2 * radius);
    this.radius = radius;
  }

  @Override
  public Circle getNextShape(AbstractShape destination, int deltaT) {
    return new Circle(this.getNextColor(destination, deltaT),
            this.getNextPoint(destination, deltaT),
            (int) (((destination.getWidth() / 2) - this.radius) / deltaT) + this.radius);
  }

  @Override
  protected void getDrawing(Graphics2D g) {
    Shape c = new Ellipse2D.Double(this.getCoordinates().x, this.getCoordinates().y,
            (double) this.radius * 2, (double) this.radius * 2);

    g.setPaint(this.getColor());
    g.draw(c);
    g.fill(c);
  }

  public String generateSVGHeader() {
    return String.format("<ellipse id=\"%s\" cx=\"%1$s\" cy=\"%2$s\" rx=\"%3$s\" ry=\"%4$s\" " +
                    "fill=\"rgb(%5$s,%6$s,%7$s)\" visibility=\"visible\" >\n", getCoordinates().x,
            getCoordinates().y, radius, radius, getColor().getRed(), getColor().getGreen(),
            getColor().getBlue());
  }

  public String generateEndTag() {
    return "</ellipse>\n";
  }
}

