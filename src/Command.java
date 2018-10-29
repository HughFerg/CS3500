public class Command {

  private int start;
  private int end;
  private Shape current;
  private Shape destination;

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

  /**
   * Updates the current shape's value by the correct amounts per each field.
   */
  public void update(int currentTick) {

    int deltaT = this.end - currentTick;

    Shape newShape = this.current.getNextShape(this.destination, deltaT);
  }

  /**
   * Returns a string representation of this command.
   */
  @Override
  public String toString() {
   return null;
  }

  public int getStart() {
    return this.start;
  }

  public int getEnd() {
    return this.end;
  }
}
