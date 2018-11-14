package cs3500.animator.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

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
   * @param color       The Color of a Shape.
   * @param coordinates The defining coordinate of a Shape used for placement.
   * @param width       The width of a Shape.
   * @param height      The height of a Shape.
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

  /**
   * Getter method for retrieving a shape's coordinate without allowing for mutation.
   *
   * @return  The Coordinate of a shape
   */
  protected Point getCoordinates() {
    return this.coordinates;
  }

  /**
   * Getter method for retrieving a shape's Color without allowing for mutation.
   *
   * @return  The Color of a shape
   */
  protected Color getColor() {
    return this.color;
  }

  /**
   * Getter method for retrieving a shape's width without allowing for mutation.
   *
   * @return  The width of a shape
   */
  protected int getWidth() {
    return this.width;
  }

  /**
   * Getter method for retrieving a shape's height without allowing for mutation.
   *
   * @return  The height of a shape
   */
  protected int getHeight() {
    return this.height;
  }

  /**
   * Gets the next color for this shape based on the destination shape and the given delta T.
   * @param destination the shape to eventually be transformed into.
   * @param deltaT the current tick - the transformation end time.
   * @return  The color of the shape after a time of deltaT.
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
   * @param deltaT the current tick - transformation end time
   * @return  The point of a shape after a time of deltaT.
   */
  protected Point getNextPoint(AbstractShape destination, int deltaT) {
    return new Point((int) (((destination.getCoordinates().getX() - this.getCoordinates().getX())
            / deltaT) + this.getCoordinates().getX()),
            (int) (((destination.getCoordinates().getY() - this.getCoordinates().getY()) / deltaT)
                    + this.getCoordinates().getY()));
  }

  /**
   * Returns the next shape to render based on the current command's destination shape and the
   * amount they should transform to the next shape (deltaT).
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

  /**
   * Abstract method for dispatching to each shape to render their own headings.
   *
   * @param name The name of the shape that a header is being generated for
   * @return     The String representing the header for an SVG file
   */
  public abstract String generateSVGHeader(String name);

  /**
   * Abstract method for dispatching to each shape to render their own end tag.
   *
   * @return A String representing the closing tag for a SVG header
   */
  public abstract String generateEndTag();

  /**
   * Abstract method for dispatching to each shape to render their own animation tag for position.
   *
   * @param start  the starting tick of the animation
   * @param end    the ending tick of the animation
   * @param source the starting state of the shape that is being transformed
   * @return       StringBuilder representing all of the animations needed to move the position
   */
  public abstract StringBuilder generatePositionTag(int start, int end, AbstractShape source);

  /**
   * Abstract method for dispatching to each shape to render their own animation tag for dimension.
   *
   * @param start  the starting tick of the animation
   * @param end    the ending tick of the animation
   * @param source the starting state of the shape that is being transformed
   * @return       StringBuilder representing all of the animations needed to change the dimension
   */
  public abstract StringBuilder generateDimensionTag(int start, int end, AbstractShape source);

  /**
   * Generates the color tag for the given Abstract shape.
   * @param start the start time of the motion.
   * @param end the end time of the motion.
   * @param source the shape to be generated from/
   * @return string representation of the given shape's color tag.
   */
  public StringBuilder generateColorTag(int start, int end, AbstractShape source) {
    StringBuilder animation = new StringBuilder();
    String template = "    <animate attributeType=\"xml\" begin=\"" + start + "000.0ms\" dur=\""
            + end + "000.0ms\" attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n";
    if (!this.getColor().equals(source.getColor())) {
      String colorStart = String.format("rgb(%s,%s,%s)", source.getColor().getRed(),
              source.getColor().getGreen(), source.getColor().getBlue());
      String colorEnd = String.format("rgb(%s,%s,%s)", this.getColor().getRed(),
              this.getColor().getGreen(), this.getColor().getBlue());
      animation.append(String.format(template, "fill", colorStart, colorEnd));
    }
    return animation;
  }
}

