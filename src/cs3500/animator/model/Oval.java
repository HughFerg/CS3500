package cs3500.animator.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * Represents a circle to be displayed and manipulated in the animator.
 */
public class Oval extends AbstractShape {

  private int xRadius;
  private int yRadius;

  /**
   * Constructor for an oval shape.
   *
   * @param color        The Color of the the oval
   * @param coordinates  The coordinate of the center of the oval
   * @param xRadius      The radius in the x direction of the oval
   * @param yRadius      The radius in the y direction of the oval
   */
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
  public String generateSVGHeader(String name) {
    return String.format("<ellipse id=\"%s\" cx=\"%s\" cy=\"%s\" rx=\"%s\" ry=\"%s\" " +
                    "fill=\"rgb(%s,%s,%s)\" visibility=\"visible\" >\n", name,
            getCoordinates().x, getCoordinates().y, xRadius, yRadius, getColor().getRed(),
            getColor().getGreen(), getColor().getBlue());
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
    if (this.getCoordinates().x != source.getCoordinates().x) {
      animation.append(String.format(template, "cx", source.getCoordinates().x,
              this.getCoordinates().x));
    }
    if (this.getCoordinates().y != source.getCoordinates().y) {
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
    if (this.width != source.width) {
      animation.append(String.format(template, "rx", source.width / 2, this.width / 2));
    }
    if (this.height != source.height) {
      animation.append(String.format(template, "ry", source.height / 2, this.height / 2));
    }
    return animation;
  }
}
