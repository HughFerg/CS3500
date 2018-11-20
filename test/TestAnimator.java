import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import java.awt.Point;

import cs3500.animator.model.AbstractShape;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.model.Circle;
import cs3500.animator.model.Command;
import cs3500.animator.model.Oval;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Triangle;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.TextView;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for Animator and its related components.
 */
public class TestAnimator {

  AnimatorModel animator;

  AnimatorView textView;

  AbstractShape c1 = new Circle(Color.BLACK, new Point(3, 3), 3);
  AbstractShape c2 = new Circle(Color.BLACK, new Point(3, 5), 3);
  AbstractShape c3 = new Circle(Color.BLUE, new Point(3, 5), 3);

  AbstractShape r1 = new Rectangle(new Color(100, 100, 100), new Point(1, 1), 3, 4);
  AbstractShape r2 = new Rectangle(new Color(100, 200, 0), new Point(2, 2), 3, 4);
  AbstractShape r3 = new Rectangle(new Color(100, 200, 0), new Point(2, 4), 5, 3);

  AbstractShape t1 = new Triangle(new Color(0, 100, 0), new Point(1, 1), 3);
  AbstractShape t2 = new Triangle(new Color(0, 200, 0), new Point(4, 4), 5);

  Command triCmd1 = new Command("Triangle1", 0, 4, t1, t2);
  Command triCmd2 = new Command("Triangle1",4, 8, t2, t2);

  Command rectCmd1 = new Command("Rectangle1", 3, 4, r1, r2);
  Command rectCmd2 = new Command("Rectangle1",4, 6, r2, r3);
  Command rectCmd3 = new Command("Rectangle1",6, 10, r3, r3);

  Command circleCmd1 = new Command("Circle1", 0, 5, c1, c1);
  Command circleCmd2 = new Command("Circle1",5, 7, c1, c2);
  Command circleCmd3 = new Command("Circle1",7, 10, c2, c3);

  @Before
  public void init() {
    animator = new AnimatorModelImpl();
    textView = new TextView(animator,300, 300);
  }
/*
  @Test(expected = IllegalArgumentException.class)
  public void testTimeTravellingCommand() {
    animator.addCommand(new Command("Circle",2, 1, c1, c2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTime() {
    animator.addCommand(new Command("Circle",-2, 1, c1, c2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDoubleNegativeTime() {
    animator.addCommand(new Command("Circle",-2, -1, c1, c2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadRect() {
    new Rectangle(Color.RED, new Point(3, 5), 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadCirc() {
    new Circle(Color.RED, new Point(3, 5), -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTri() {
    new Triangle(Color.RED, new Point(3, 5), -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadOval() {
    new Oval(Color.RED, new Point(3, 5), -1, 4);
  }

  @Test
  public void testGet0Tick() {
    assertEquals(0, animator.getTick());
  }

  @Test
  public void testIncrementTickEmpty() {
    animator.onTick();
    assertEquals(1, animator.getTick());
  }

  @Test
  public void test0TickFull() {
    animator.addCommand(rectCmd2);

    assertEquals(0, animator.getTick());
  }

  @Test
  public void testIncrementTickFull() {
    animator.addCommand(triCmd1);
    animator.addCommand(circleCmd1);
    animator.addCommand(rectCmd1);
    animator.onTick();

    assertEquals(1, animator.getTick());
  }
  */
}

