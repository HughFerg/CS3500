package cs3500.animator.model;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Represents a command to be executed in an Animator. Each command has a current shape that is
 * currently represented, and a destination shape with varying location, color, or size.
 */
public final class Command implements CommandInterface {

  private final String name;
  private int start;
  private int end;
  private IShape current;
  private IShape destination;

  /**
   * Constructs a Command which has 2 ints representing a time frame and two Shapes representing a
   * current and end state.
   *
   * @param start       The tick that the Command begins to update
   * @param end         The tick that the Command finishes updating
   * @param current     The current state of the Shape in the Animator
   * @param destination The goal state of the Shape by the time the end tick is reached
   */
  public Command(String name, int start, int end, IShape current,
                 IShape destination) {
    if (start < 0 || end < 0 || start > end || name.equals("")) {
      throw new IllegalArgumentException("Start and end times must be greater than or equal to 0," +
              " and start cannot be after end.");
    } else {
      this.name = name;
      this.start = start;
      this.end = end;
      this.current = current;
      this.destination = destination;
    }
  }


  @Override
  public int getStart() {
    return this.start;
  }

  @Override
  public int getX() {
    return current.getCoordinates().x;
  }

  @Override
  public int getY() {
    return current.getCoordinates().y;
  }

  @Override
  public int getWidth() {
    return this.current.getWidth();
  }

  @Override
  public int getHeight() {
    return this.current.getHeight();
  }

  @Override
  public int getRed() {
    return this.current.getColor().getRed();
  }

  @Override
  public int getGreen() {
    return this.current.getColor().getGreen();
  }

  @Override
  public int getBlue() {
    return this.current.getColor().getBlue();
  }

  @Override
  public int getEnd() {
    return this.end;
  }

  @Override
  public IShape replaceDest(int x, int y, int w, int h, int r, int g,
                                   int b) {
    destination.replace(x, y, w, g, r, g, b);
    return destination;
  }

  @Override
  public IShape replaceCurrent(int x, int y, int w, int h, int r, int g,
                            int b) {
    current.replace(x, y, w, g, r, g, b);
    return current;
  }


  /**
   * Gets the destination shape.
   * @return destination shape.
   */
  @Override
  public IShape getDest() {
    return destination;
  }

  /**
   * Clones this command.
   * @return a copy of this command.
   */
  @Override
  public Command clone() {
    return new Command(name, start, end, current, destination);
  }

  /**
   * Returns this Command's name.
   *
   * @return this Command's name.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Returns this command's current shape.
   * @return current shape.
   */
  @Override
  public IShape getCurrent() {
    return this.current;
  }

  /**
   * Updates the current shape's value by the correct amounts per each field.
   */
  @Override
  public void update(int currentTick) {
    int deltaT = this.end - currentTick;
    IShape newShape = current.getNextShape(destination, deltaT);
    this.current = newShape;
  }

  /**
   * Draws the graphic onto the canvas.
   *
   * @param g           the canvas of the animator.
   * @param currentTick the tick representing the current time.
   */
  @Override
  public void getDrawing(Graphics2D g, int currentTick) {
    if (this.getStart() <= currentTick && this.getEnd() >= currentTick) {
      this.current.getDrawing(g);
    }
  }

  /**
   * Returns a string representation of this command.
   */
  @Override
  public String toString() {

    String result = "";

    IShape c = this.current;
    IShape d = this.destination;

    result += this.name + " - Start: " + this.start + " X: "
            + (int) c.getCoordinates().getX() + " Y: " + c.getCoordinates().getY() + " W: "
            + c.getWidth() + " H: " + c.getHeight() + " R: " + c.getColor().getRed() + " G: "
            + c.getColor().getGreen() + " B: " + c.getColor().getBlue();

    result += " --- End: " + this.end + " X: " + (int) d.getCoordinates().getX()
            + " Y: " + d.getCoordinates().getY() + " W: " + d.getWidth() + " H: "
            + d.getHeight() + " R: " + d.getColor().getRed() + " G: " + d.getColor().getGreen()
            + " B: " + d.getColor().getBlue();

    return result;
  }

  /**
   * Dispatches the generation of SVG headers to Shape.
   *
   * @return String representation of a SVG shape header.
   */
  @Override
  public String generateSVGHeader() {
    return this.current.generateSVGHeader(this.getName());
  }

  /**
   * Generates the closing tag for an SVG header by dispatching to Shape.
   *
   * @return String representation of a SVG closing tag.
   */
  @Override
  public String generateEndTag() {
    return this.current.generateEndTag();
  }

  /**
   * Dispatches the creation of animation tags for each of the possible transformations a shape can
   * have.
   *
   * @return StringBuilder representing all of the animations over this Command.
   */
  @Override
  public StringBuilder generateAnimationTag() {
    StringBuilder tags = new StringBuilder();
    if (!this.current.getCoordinates().equals(this.destination.getCoordinates())) {
      tags.append(this.destination.generatePositionTag(this.getStart(), this.getEnd(), current));
    }
    if (!this.current.getColor().equals(this.destination.getColor())) {
      tags.append(this.destination.generateColorTag(this.getStart(), this.getEnd(), current));
    }
    if (!(current.getWidth() == destination.getWidth() || current.getHeight() == destination.getHeight())) {
      tags.append(this.destination.generateDimensionTag(this.getStart(), this.getEnd(), current));
    }
    return tags;
  }
}

