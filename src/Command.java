/**
 * Represents a command to be executed in an Animator. Each command has a current shape that is
 * currently represented, and a destination shape with varying location, color, or size.
 */
public class Command {

  private int start;
  private int end;
  private AbstractShape current;
  private AbstractShape destination;

  public Command(int start, int end, AbstractShape current, AbstractShape destination) {
    if (start < 0 || end < 0) {
      throw new IllegalArgumentException("Start and end times must be greater than or equal to 0.");
    } else {
      this.start = start;
      this.end = end;
      this.current = current;
      this.destination = destination;
    }
  }

  public int getStart() {
    return this.start;
  }

  public int getEnd() {
    return this.end;
  }

  /**
   * Updates the current shape's value by the correct amounts per each field.
   */
  public void update(int currentTick) {

    int deltaT = this.end - currentTick;

    AbstractShape newShape = this.current.getNextShape(this.destination, deltaT);
    this.current = newShape;
  }

  /**
   * Returns a string representation of this command.
   */
  @Override
  public String toString() {

    String result = "";
    result += "From time " + this.start + " to time " + this.end
    + " " + this.current.getClass().getName();

    return result;
  }
}
