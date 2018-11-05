package cs3500.animator.model;

/**
 * Represents a command to be executed in an Animator. Each command has a current shape that is
 * currently represented, and a destination shape with varying location, color, or size.
 */
public class Command {

  private int start;
  private int end;
  private AbstractShape current;
  private AbstractShape destination;

  /**
   * Constructs a Command which has 2 ints representing a time frame and two Shapes representing a
   * current and end state.
   *
   * @param start       The tick that the Command begins to update
   * @param end         The tick that the Command finishes updating
   * @param current     The current state of the Shape in the Animator
   * @param destination The goal state of the Shape by the time the end tick is reached
   */
  public Command(int start, int end, AbstractShape current, AbstractShape destination) {
    if (start < 0 || end < 0 || start > end) {
      throw new IllegalArgumentException("Start and end times must be greater than or equal to 0," +
              " and start cannot be after end.");
    } else {
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
   * Updates the current shape's value by the correct amounts per each field.
   */
  public void update(int currentTick) {

    int deltaT = this.end - currentTick;
    this.current = this.current.getNextShape(this.destination, deltaT);
  }

  /**
   * Returns a string representation of this command.
   */
  @Override
  public String toString() {

    String result = "";

    AbstractShape c = this.current;
    AbstractShape d = this.destination;

    result += c.getClass().getName() + " - Start: " + this.start + " X: "
            + (int) c.getCoordinates().getX() + " Y: " + c.getCoordinates().getY() + " W: "
            + c.getWidth() + " H: " + c.getHeight() + " R: " + c.getColor().getRed() + " G: "
            + c.getColor().getGreen() + " B: " + c.getColor().getBlue();

    result += " --- End: " + this.end + " X: " + (int) d.getCoordinates().getX()
            + " Y: " + d.getCoordinates().getY() + " W: " + d.getWidth() + " H: "
            + d.getHeight() + " R: " + d.getColor().getRed() + " G: " + d.getColor().getGreen()
            + " B: " + d.getColor().getBlue();

    return result;
  }
}

