package cs3500.animator.model;

import java.awt.*;

/**
 * Represents an equilateral triangle to be represented in the Animator.
 */
public class Triangle extends AbstractShape {

  private int sideLength;

  public Triangle(Color color, Point coordinates, int sideLength) {
    super(color, coordinates, sideLength, (int) (Math.sqrt(3.0) * sideLength) / 2);
  }

  @Override
  public Triangle getNextShape(AbstractShape destination, int deltaT) {
    return new Triangle(this.getNextColor(destination, deltaT),
            this.getNextPoint(destination, deltaT),
            (int) (((destination.getWidth() - this.sideLength) / deltaT) + this.sideLength) / 2);
  }
}
