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

  Command cmd1 = new Command(0, 5, c1, c1);

  @Before
  public void init() {
    animator = new AnimatorModelImpl();
  }

  @Test
  public void testInit() {
    animator.addCommand(cmd1);
  }
}
