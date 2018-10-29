import java.awt.*;

/**
 * Represents a Shape to be manipulated in an Animator.
 */
public interface Shape {

  public Point getCoordinates();

  public Color getColor();

  public int getWidth();

  public int getHeight();

  public Shape getNextShape(Shape destination, int deltaT);
}
