package cs3500.animator.model;

import java.awt.Color;
import java.awt.Point;

/**
 * Represents an abstract shape with all common shape characteristics (coordinates, color, width and
 * height). More specific dimensions are established in subclasses.
 */
public abstract class AbstractShape implements IShape {

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
   * Sets default values for a shape to be changed later.
   */
  public AbstractShape() {
    this.color = Color.ORANGE;
    this.coordinates = new Point(0, 0);
    this.width = 0;
    this.height = 0;
  }

  @Override
  public void replace(int x, int y, int w, int h, int r, int g, int b) {
    this.coordinates = new Point(x, y);
    this.width = w;
    this.height = h;
    this.color = new Color(r, g, b);
  }

  /**
   * Getter method for retrieving a shape's coordinate without allowing for mutation.
   *
   * @return The Coordinate of a shape
   */
  @Override
  public Point getCoordinates() {
    return this.coordinates;
  }

  @Override
  public void changeShapeSize(int width, int height) {
    this.replace(this.getX(), this.getY(), width, height, this.color.getRed(),
            this.color.getGreen(), this.color.getBlue());
  }

  @Override
  public void moveShape(int x, int y) {
    this.replace(x, y, this.getWidth(), this.getHeight(), this.color.getRed(),
            this.color.getGreen(), this.color.getBlue());
  }

  @Override
  public void setColor(Color color) {
    this.replace(this.getX(), this.getY(), this.getWidth(), this.getHeight(), color.getRed(),
            color.getGreen(), color.getBlue());
  }

  /**
   * Getter method for retrieving a shape's Color without allowing for mutation.
   *
   * @return The Color of a shape
   */
  @Override
  public Color getColor() {
    return this.color;
  }


  @Override
  public int getX() {
    return (int) this.getCoordinates().getX();
  }

  @Override
  public int getY() {
    return (int) this.getCoordinates().getY();
  }

  /**
   * Getter method for retrieving a shape's width without allowing for mutation.
   *
   * @return The width of a shape
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public Attributes copyShape() {
    return this.copyShape();
  }

  /**
   * Getter method for retrieving a shape's height without allowing for mutation.
   *
   * @return The height of a shape
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Gets the next color for this shape based on the destination shape and the given delta T.
   *
   * @param destination the shape to eventually be transformed into.
   * @param deltaT      the current tick - the transformation end time.
   * @return The color of the shape after a time of deltaT.
   */
  @Override
  public Color getNextColor(IShape destination, int deltaT) {
    return new Color(((destination.getColor().getRed() - this.color.getRed()) / deltaT)
            + this.color.getRed(),
            ((destination.getColor().getGreen() - this.color.getGreen()) / deltaT)
                    + this.color.getGreen(),
            ((destination.getColor().getBlue() - this.color.getBlue()) / deltaT)
                    + this.color.getBlue());
  }

  /**
   * Gets the next point for his shape given the target shape and the deltaT.
   *
   * @param destination the destination shape to eventually transform into.
   * @param deltaT      the current tick - transformation end time
   * @return The point of a shape after a time of deltaT.
   */
  @Override
  public Point getNextPoint(IShape destination, int deltaT) {
    return new Point((int) (((destination.getCoordinates().getX() - this.getCoordinates().getX())
            / deltaT) + this.getCoordinates().getX()),
            (int) (((destination.getCoordinates().getY() - this.getCoordinates().getY()) / deltaT)
                    + this.getCoordinates().getY()));
  }


  /**
   * Generates the color tag for the given shape.
   *
   * @param start  the start time of the motion.
   * @param end    the end time of the motion.
   * @param source the shape to be generated from/
   * @return string representation of the given shape's color tag.
   */
  @Override
  public StringBuilder generateColorTag(int start, int end, IShape source) {
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

