import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for Animator and its related components.
 */
public class testAnimator {

  AnimatorModel animator;

  AbstractShape c1 = new Circle(Color.BLACK, new Point(3, 3), 3);
  AbstractShape c2 = new Circle(Color.BLACK, new Point(3, 5), 3);
  AbstractShape c3 = new Circle(Color.BLUE, new Point(3, 5), 3);

  AbstractShape r1 = new Rectangle(new Color(100, 100, 100), new Point(1, 1), 3, 4);
  AbstractShape r2 = new Rectangle(new Color(100, 200, 0), new Point(2, 2), 3, 4);
  AbstractShape r3 = new Rectangle(new Color(100, 200, 0), new Point(2, 4), 5, 3);

  AbstractShape t1 = new Rectangle(new Color(0, 100, 0), new Point(1, 1), 3, 4);
  AbstractShape t2 = new Rectangle(new Color(0, 200, 0), new Point(4,4), 5, 8);

  Command triCmd1 = new Command(0, 4, t1, t2);
  Command triCmd2 = new Command(4, 8, t2, t2);

  Command rectCmd1 = new Command(3, 4, r1, r2);
  Command rectCmd2 = new Command(4, 6, r2, r3);
  Command rectCmd3 = new Command(6, 10, r3, r3);

  Command circleCmd1 = new Command(0, 5, c1, c1);
  Command circleCmd2 = new Command(5, 7, c1, c2);
  Command circleCmd3 = new Command(7, 10, c2, c3);

  @Before
  public void init() {
    animator = new AnimatorModelImpl();
  }

  @Test
  public void testInit() {

    assertEquals("", animator.render());
  }
}
