package cs3500.animator.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
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
  public ModelShape toModelShape(String name) {
    return new ModelEllipse(name, new Oval(this.color, this.coordinates, this.radius, this.radius));
  }

  @Override
  public IShape getNextShape(IShape destination, int deltaT) {
    return new Circle(this.getNextColor(destination, deltaT),
            this.getNextPoint(destination, deltaT),
            (int) (((destination.getWidth() / 2) - this.radius) / deltaT) + this.radius);
  }

  @Override
  public void getDrawing(Graphics2D g) {
    Shape c = new Ellipse2D.Double(this.getCoordinates().x, this.getCoordinates().y,
            (double) this.radius * 2, (double) this.radius * 2);

    g.translate(this.coordinates.x, this.coordinates.y);
    g.setPaint(this.getColor());
    g.draw(c);
    g.fill(c);
  }

  @Override
  public String getShapeType() {
    return "circle";
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
  public StringBuilder generatePositionTag(int start, int end, IShape source) {
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
  public StringBuilder generateDimensionTag(int start, int end, IShape source) {
    StringBuilder animation = new StringBuilder();
    String template = "    <animate attributeType=\"xml\" begin=\"" + start + "000.0ms\" dur=\""
            + end + "000.0ms\" attributeName=\"%s\" from=\"%s\" to=\"%s\" fill=\"freeze\" />\n";
    if (this.width != source.getWidth()) {
      animation.append(String.format(template, "rx", source.getWidth()/ 2, this.getWidth() / 2));
    }
    if (this.height != source.getHeight()) {
      animation.append(String.format(template, "ry", source.getHeight() / 2, this.height / 2));
    }
    return animation;
  }
}

