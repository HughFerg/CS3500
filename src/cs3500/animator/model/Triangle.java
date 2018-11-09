package cs3500.animator.model;

import java.awt.*;

/**
 * Represents an equilateral triangle to be represented in the Animator.
 */
public class Triangle extends AbstractShape {

  private int sideLength;

  /**
   * Contructs an equilateral triangle to be used in an animator
   *
   * @param color       The Color of the triangle
   * @param coordinates The bottom left corner coordinate of the rectangle
   * @param sideLength  The length of each side of the triangle
   */
  public Triangle(Color color, Point coordinates, int sideLength) {
    super(color, coordinates, sideLength, (int) (Math.sqrt(3.0) * sideLength) / 2);
    this.sideLength = sideLength;
  }

  @Override
  public Triangle getNextShape(AbstractShape destination, int deltaT) {
    return new Triangle(this.getNextColor(destination, deltaT),
            this.getNextPoint(destination, deltaT),
            (int) (((destination.getWidth() - this.sideLength) / deltaT) + this.sideLength));
  }

  @Override
  protected void getDrawing(Graphics2D g) {

    int x[] = {this.getCoordinates().x, this.getCoordinates().x + this.sideLength,
            (int)(this.getCoordinates().x + (.5 * this.sideLength))};
    int y[] = {this.getCoordinates().y, this.getCoordinates().y,
            (int)this.getCoordinates().y + this.getHeight()};

    Shape t = new Polygon(x, y, 3);

    g.setPaint(this.getColor());
    g.draw(t);
    g.fill(t);
  }

  @Override
  public String generateSVGHeader(String name) {
    return String.format("<polygon id=\"%s\" points=\"%s,%s %s,%s %s,%s\" " +
                    "fill=\"rgb(%s,%s,%s)\" visibility=\"visible\" >\n", name,
            getCoordinates().x, getCoordinates().y, getCoordinates().x + (getWidth()/2),
            getCoordinates().y - getHeight(), getCoordinates().x + getWidth(), getCoordinates().y,
            getColor().getRed(), getColor().getGreen(), getColor().getBlue());
  }

  @Override
  public String generateEndTag() {
    return "</polygon>\n";
  }

  @Override
  public StringBuilder generatePositionTag(int start, int end, AbstractShape source) {
    StringBuilder animation = new StringBuilder();
    String template = "    <animate attributeType=\"xml\" begin=\"" + start + "000.0ms\" dur=\""
            + end + "000.0ms\" attributeName=\"%s\" from=\"%s,%s %s,%s %s,%s\" " +
            "to=\"%s,%s %s,%s %s,%s\" fill=\"freeze\" />\n";
    if((this.getCoordinates().x != source.getCoordinates().x) ||
            (this.getCoordinates().y != source.getCoordinates().y)) {
      animation.append(String.format(template, "points", getCoordinates().x,
              source.getCoordinates().y, source.getCoordinates().x + (source.getWidth()/2),
              source.getCoordinates().y - source.getHeight(),
              source.getCoordinates().x + source.getWidth(), source.getCoordinates().y,
              getCoordinates().x, getCoordinates().y, getCoordinates().x + (getWidth()/2),
              getCoordinates().y - getHeight(), getCoordinates().x + getWidth(),
              getCoordinates().y));
    }
    return animation;
  }

  @Override
  public StringBuilder generateDimensionTag(int start, int end, AbstractShape source) {
    return this.generatePositionTag(start, end, source);
  }
}
