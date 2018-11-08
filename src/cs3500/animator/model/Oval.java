package cs3500.animator.model;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Represents a circle to be displayed and manipulated in the animator.
 */
public class Oval extends AbstractShape {

  private int xRadius;
  private int yRadius;

  public Oval(Color color, Point coordinates, int xRadius, int yRadius) {
    super(color, coordinates, 2 * xRadius, 2 * yRadius);
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  @Override
  public Oval getNextShape(AbstractShape destination, int deltaT) {
    return new Oval(this.getNextColor(destination, deltaT), this.getNextPoint(destination, deltaT),
            (int) ((((destination.getWidth() / 2) - this.xRadius) / deltaT) + this.xRadius),
            (int) ((((destination.getHeight() / 2) - this.yRadius) / deltaT) + this.yRadius));
  }

  @Override
  protected void getDrawing(Graphics2D g) {
    Shape e = new Ellipse2D.Double(this.coordinates.x, this.coordinates.y, (double)this.width,
            (double)this.height);

    g.setPaint(this.getColor());
    g.fill(e);
    g.draw(e);
  }

  @Override
  public String generateSVGHeader() {
    return String.format("<ellipse id=\"%s\" cx=\"%1$s\" cy=\"%2$s\" rx=\"%3$s\" ry=\"%4$s\" " +
                    "fill=\"rgb(%5$s,%6$s,%7$s)\" visibility=\"visible\" >\n", getCoordinates().x,
            getCoordinates().y, xRadius, yRadius, getColor().getRed(), getColor().getGreen(),
            getColor().getBlue());
  }

  public String generateEndTag() {
    return "</ellipse>\n";
  }
}
