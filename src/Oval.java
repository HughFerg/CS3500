import java.awt.*;

public class Oval extends AbstractShape {

  private int xRadius;
  private int yRadius;

  public Oval(int xRadius, int yRadius, Color color, Point coordinates) {
    super(color, coordinates);

    if (this.xRadius > 0 && this.yRadius > 0) {
      this.xRadius = xRadius;
      this.yRadius = yRadius;
    } else {
      throw new IllegalArgumentException("X and Y radius must be greater than 1.");
    }
  }
}
