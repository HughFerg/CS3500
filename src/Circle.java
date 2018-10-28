import java.awt.*;

public class Circle extends AbstractShape {

  private int radius;

  public Circle(int radius, Color color, Point coordinates) {
    super(color, coordinates);

    if (radius > 0) {
      this.radius = radius;
    } else {
      throw new IllegalArgumentException("Radius must be greater than 0.");
    }
  }
}
