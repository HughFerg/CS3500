package cs3500.animator.model;

import java.awt.*;

/**
 * Represents a command to be executed in an Animator. Each command has a current shape that is
 * currently represented, and a destination shape with varying location, color, or size.
 */
public final class Command {

  private int start;
  private int end;
  private AbstractShape current;
  private AbstractShape destination;
  private final String NAME;

  /**
   * Constructs a Command which has 2 ints representing a time frame and two Shapes representing a
   * current and end state.
   *
   * @param start       The tick that the Command begins to update
   * @param end         The tick that the Command finishes updating
   * @param current     The current state of the Shape in the Animator
   * @param destination The goal state of the Shape by the time the end tick is reached
   */
  public Command(String NAME, int start, int end, AbstractShape current,
                 AbstractShape destination) {
    if (start < 0 || end < 0 || start > end || NAME.equals("")) {
      throw new IllegalArgumentException("Start and end times must be greater than or equal to 0," +
              " and start cannot be after end.");
    } else {
      this.NAME = NAME;
      this.start = start;
      this.end = end;
      this.current = current;
      this.destination = destination;
    }
  }

  /**
   * Returns the start time of this command.
   *
   * @return the start time, in ticks, of this command.
   */
  public int getStart() {
    return this.start;
  }

  /**
   * Returns the end time of this command.
   *
   * @return the end time, in ticks, of this command.
   */
  public int getEnd() {
    return this.end;
  }

  /**
   * Returns this Command's name.
   * @return this Command's name.
   */
  public String getName() {
    return this.NAME;
  }

  /**
   * Updates the current shape's value by the correct amounts per each field.
   */
  public void update(int currentTick) {

    int deltaT = this.end - currentTick;
    this.current = this.current.getNextShape(this.destination, deltaT);
  }

  /**
   * Draws the graphic onto the canvas.
   *
   * @param g            the canvas of the animator
   * @param currentTick  the tick representing the current time
   */
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

    AbstractShape c = this.current;
    AbstractShape d = this.destination;

    result += this.NAME + " - Start: " + this.start + " X: "
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
   * Dispatches the generation of SVG headers to AbstractShpe.
   *
   * @return String representation of a SVG shape header
   */
  public String generateSVGHeader(){
    return this.current.generateSVGHeader(this.getName());
  }

  /**
   * Generates the closing tag for an SVG header by dispatching to AbstractShape
   *
   * @return String representation of a SVG closing tag
   */
  public String generateEndTag() {
    return this.current.generateEndTag();
  }

  /**
   * Dispatches the creation of animation tags for each of the possible transformations a shape can
   * have.
   *
   * @return StringBuilder representing all of the animations over this Command
   */
  public StringBuilder generateAnimationTag() {
    StringBuilder tags = new StringBuilder();
    if(!this.current.getCoordinates().equals(this.destination.getCoordinates())) {
      tags.append(this.destination.generatePositionTag(this.getStart(), this.getEnd(), current));
    }
    if (!this.current.getColor().equals(this.destination.getColor())) {
      tags.append(this.destination.generateColorTag(this.getStart(), this.getEnd(), current));
    }
    if (!(current.width == destination.width || current.height == destination.height)) {
      tags.append(this.destination.generateDimensionTag(this.getStart(), this.getEnd(), current));
    }
    return tags;
  }
}

