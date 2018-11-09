package cs3500.animator.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Represents a Rectangle to be used in the Animator.
 */
public class Rectangle extends AbstractShape {

  private int width;
  private int height;

  /**
   * Constructor for a rectangle shape
   *
   * @param color       The Color of the rectangle
   * @param coordinates The coordinate of the top left corner of the rectangle
   * @param width       The width of the rectangle
   * @param height      The height of the rectangle
   */
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
  public String generateSVGHeader(String name) {
    return String.format("<rect id=\"%s\" x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\" " +
                    "fill=\"rgb(%s,%s,%s)\" visibility=\"visible\" >\n", name,
            getCoordinates().x, getCoordinates().y, width, height, getColor().getRed(),
            getColor().getGreen(), getColor().getBlue());
  }

  @Override
  public String generateEndTag() {
    return "</rect>\n";
  }

  @Override
  public StringBuilder generatePositionTag(int start, int end, AbstractShape source) {
    StringBuilder animation = new StringBuilder();
    String template = "    <animate attributeType=\"xml\" begin=\"" + start + "000.0ms\" dur=\""
            + end + "000.0ms\" attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n";
    if(this.getCoordinates().x != source.getCoordinates().x) {
      animation.append(String.format(template, "x", source.getCoordinates().x,
              this.getCoordinates().x));
    }
    if(this.getCoordinates().y != source.getCoordinates().y) {
      animation.append(String.format(template, "y", source.getCoordinates().y,
              this.getCoordinates().y));
    }
    return animation;
  }

  @Override
  public StringBuilder generateDimensionTag(int start, int end, AbstractShape source) {
    StringBuilder animation = new StringBuilder();
    String template = "    <animate attributeType=\"xml\" begin=\"" + start + "000.0ms\" dur=\""
            + end + "000.0ms\" attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n";
    if(this.width != source.width) {
      animation.append(String.format(template, "width", source.width, this.width));
    }
    if(this.height != source.height) {
      animation.append(String.format(template, "height", source.height, this.height));
    }
    return animation;
  }
}

