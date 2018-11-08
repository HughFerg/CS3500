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
            (int) (((destination.getWidth() - this.radius) / deltaT) + this.radius) / 2);
  }

  @Override
  protected void getDrawing(Graphics2D g) {
    Shape c = new Ellipse2D.Double((double)2 * this.radius, (double) 2 * this.radius,
            this.getCoordinates().x, this.getCoordinates().y);

    g.setPaint(this.getColor());
    g.draw(c);
    g.fill(c);
  }
}

