package cs3500.animator.model;

import java.awt.*;

/**
 * Represents an abstract shape with all common shape characteristics (coordinates, color, width
 * and height). More specific dimensions are established in subclasses.
 */
public abstract class AbstractShape {

  protected Point coordinates;
  protected Color color;
  protected int width;
  protected int height;

  /**
   * Abstract constructor for creating the backbone of what every shape is defined to be.
   *
   * @param color       The Color of a Shape
   * @param coordinates The defining coordinate of a Shape used for placement
   * @param width       The width of a Shape
   * @param height      The height of a Shape
   */
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

  /**
   * Gets the next color for this shape based on the destination shape and the given delta T.
   * @param destination the shape to eventually be transformed into.
   * @param deltaT the current tick - the transformation end time.
   * @return
   */
  protected Color getNextColor(AbstractShape destination, int deltaT) {
    return new Color(((destination.getColor().getRed() - this.color.getRed()) / deltaT)
            + this.color.getRed(),
            ((destination.getColor().getGreen() - this.color.getGreen()) / deltaT)
                    + this.color.getGreen(),
            ((destination.getColor().getBlue() - this.color.getBlue()) / deltaT)
                    + this.color.getBlue());
  }

  /**
   * Gets the next point for his shape given the target shape and the deltaT.
   * @param destination the destination shape to eventually transform into.
   * @param deltaT the current tick - the transformation end time.
   * @return
   */
  protected Point getNextPoint(AbstractShape destination, int deltaT) {
    return new Point((int) (((destination.getCoordinates().getX() - this.getCoordinates().getX())
            / deltaT) + this.getCoordinates().getX()),
            (int) (((destination.getCoordinates().getY() - this.getCoordinates().getY()) / deltaT)
                    + this.getCoordinates().getY()));
  }

  /**
   * Returns the next shape to render based on the current command's destination shape and the
   * amount they should transform to the next shape (deltaT)
   * @param destination the destination shape.
   * @param deltaT the amount to transform the shape's fields.
   * @return the shape to be rendered on the next tick.
   */
  protected abstract AbstractShape getNextShape(AbstractShape destination, int deltaT);

  /**
   * Returns the shape representation of this shape for rendering in the view.
   * @return this shape in Java.Awt shape format.
   */
  protected abstract void getDrawing(Graphics2D g);

  public abstract String generateSVGHeader();

  public abstract String generateEndTag();
}

