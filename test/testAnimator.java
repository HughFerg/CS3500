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

  Command cmd1 = new Command(0, 5, c1, c1);
  Command cmd2 = new Command(5, 7, c1, c2);
  Command cmd3 = new Command(7, 10, c2, c3);

  @Before
  public void init() {
    animator = new AnimatorModelImpl();
  }

  @Test
  public void testInit() {
    animator.addCommand(cmd1);
    animator.addCommand(cmd2);
    animator.addCommand(cmd3);

    assertEquals("", animator.render());
  }
}
