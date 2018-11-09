package cs3500.animator.model;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Represents a circle to be displayed and manipulated in the animator.
 */
public class Circle extends AbstractShape {

  private int radius;

  /**
   * Constructor for a circle shape.
   *
   * @param color        The Color of the the circle
   * @param coordinates  The coordinate of the center of the circle
   * @param radius       The radius of the circle
   */
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

  @Override
  public String generateSVGHeader(String name) {
    return String.format("<ellipse id=\"%s\" cx=\"%s\" cy=\"%s\" rx=\"%s\" ry=\"%s\" " +
                    "fill=\"rgb(%s,%s,%s)\" visibility=\"visible\" >\n", name, getCoordinates().x,
            getCoordinates().y, radius, radius, getColor().getRed(), getColor().getGreen(),
            getColor().getBlue());
  }

  @Override
  public String generateEndTag() {
    return "</ellipse>\n";
  }

  @Override
  public StringBuilder generatePositionTag(int start, int end, AbstractShape source) {
    StringBuilder animation = new StringBuilder();
    String template = "    <animate attributeType=\"xml\" begin=\"" + start + "000.0ms\" dur=\""
            + end + "000.0ms\" attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n";
    if(this.getCoordinates().x != source.getCoordinates().x) {
      animation.append(String.format(template, "cx", source.getCoordinates().x,
              this.getCoordinates().x));
    }
    if(this.getCoordinates().y != source.getCoordinates().y) {
      animation.append(String.format(template, "cy", source.getCoordinates().y,
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
      animation.append(String.format(template, "rx", source.width / 2, this.width / 2));
    }
    if(this.height != source.height) {
      animation.append(String.format(template, "ry", source.height / 2, this.height / 2));
    }
    return animation;
  }
}

