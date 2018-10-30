import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.Point;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for Animator and its related components.
 */
public class TestAnimator {

  AnimatorModel animator;

  AbstractShape c1 = new Circle(Color.BLACK, new Point(3, 3), 3);
  AbstractShape c2 = new Circle(Color.BLACK, new Point(3, 5), 3);
  AbstractShape c3 = new Circle(Color.BLUE, new Point(3, 5), 3);

  AbstractShape r1 = new Rectangle(new Color(100, 100, 100), new Point(1, 1), 3, 4);
  AbstractShape r2 = new Rectangle(new Color(100, 200, 0), new Point(2, 2), 3, 4);
  AbstractShape r3 = new Rectangle(new Color(100, 200, 0), new Point(2, 4), 5, 3);

  AbstractShape t1 = new Triangle(new Color(0, 100, 0), new Point(1, 1), 3);
  AbstractShape t2 = new Triangle(new Color(0, 200, 0), new Point(4, 4), 5);

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

  @Test(expected = IllegalArgumentException.class)
  public void testTimeTravellingCommand() {
    animator.addCommand(new Command(2, 1, c1, c2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTime() {
    animator.addCommand(new Command(-2, 1, c1, c2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDoubleNegativeTime() {
    animator.addCommand(new Command(-2, -1, c1, c2));
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
  public void testInit() {
    animator.addCommand(circleCmd1);
    animator.addCommand(circleCmd2);
    animator.addCommand(circleCmd3);

    assertEquals("Circle - Start: 0 X: 3 Y: 3.0 W: 6 H: 6 R: 0 G: 0 B: 0 ---" +
            " End: 5 X: 3 Y: 3.0 W: 6 H: 6 R: 0 G: 0 B: 0\n" +
            "Circle - Start: 5 X: 3 Y: 3.0 W: 6 H: 6 R: 0 G: 0 B: 0 ---" +
            " End: 7 X: 3 Y: 5.0 W: 6 H: 6 R: 0 G: 0 B: 0\n" +
            "Circle - Start: 7 X: 3 Y: 5.0 W: 6 H: 6 R: 0 G: 0 B: 0 ---" +
            " End: 10 X: 3 Y: 5.0 W: 6 H: 6 R: 0 G: 0 B: 255", animator.render());
  }

  @Test
  public void testEmptyRender() {

    assertEquals("", animator.render());
  }

  @Test
  public void testSmallRender() {
    animator.addCommand(triCmd1);

    assertEquals("Triangle - Start: 0 X: 1 Y: 1.0 W: 3 H: 2 R: 0 G: 100 B: 0 ---" +
            " End: 4 X: 4 Y: 4.0 W: 5 H: 4 R: 0 G: 200 B: 0", animator.render());
  }

  @Test
  public void testMultiShapeRender() {
    animator.addCommand(triCmd1);
    animator.addCommand(circleCmd3);

    assertEquals("Triangle - Start: 0 X: 1 Y: 1.0 W: 3 H: 2 R: 0 G: 100 B: 0 ---" +
            " End: 4 X: 4 Y: 4.0 W: 5 H: 4 R: 0 G: 200 B: 0\n" +
            "Circle - Start: 7 X: 3 Y: 5.0 W: 6 H: 6 R: 0 G: 0 B: 0 ---" +
            " End: 10 X: 3 Y: 5.0 W: 6 H: 6 R: 0 G: 0 B: 255", animator.render());
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

  @Test
  public void testOnTickSmall() {
    animator.addCommand(rectCmd2);
    animator.onTick();
    animator.onTick();
    animator.onTick();
    animator.onTick();
    animator.onTick();

    assertEquals("Rectangle - Start: 4 X: 2 Y: 3.0 W: 1 H: 0 R: 100 G: 200 B: 0 ---" +
            " End: 6 X: 2 Y: 4.0 W: 5 H: 3 R: 100 G: 200 B: 0", animator.render());
  }

  @Test
  public void testOnTickLarge() {
    animator.addCommand(circleCmd1);
    animator.addCommand(circleCmd2);
    animator.addCommand(circleCmd3);
    animator.onTick();

    assertEquals("Circle - Start: 0 X: 3 Y: 3.0 W: 0 H: 0 R: 0 G: 0 B: 0 ---" +
            " End: 5 X: 3 Y: 3.0 W: 6 H: 6 R: 0 G: 0 B: 0\n" +
            "Circle - Start: 5 X: 3 Y: 3.0 W: 6 H: 6 R: 0 G: 0 B: 0 ---" +
            " End: 7 X: 3 Y: 5.0 W: 6 H: 6 R: 0 G: 0 B: 0\n" +
            "Circle - Start: 7 X: 3 Y: 5.0 W: 6 H: 6 R: 0 G: 0 B: 0 ---" +
            " End: 10 X: 3 Y: 5.0 W: 6 H: 6 R: 0 G: 0 B: 255", animator.render());
  }

  @Test
  public void testOnTickLargeCommandRemoved() {
    animator.addCommand(circleCmd1);
    animator.addCommand(circleCmd2);
    animator.addCommand(circleCmd3);
    animator.onTick();
    animator.onTick();
    animator.onTick();
    animator.onTick();
    animator.onTick();
    animator.onTick();
    animator.onTick();


    assertEquals("Circle - Start: 5 X: 3 Y: 5.0 W: 6 H: 6 R: 0 G: 0 B: 0 ---" +
            " End: 7 X: 3 Y: 5.0 W: 6 H: 6 R: 0 G: 0 B: 0\n" +
            "Circle - Start: 7 X: 3 Y: 5.0 W: 6 H: 6 R: 0 G: 0 B: 0 ---" +
            " End: 10 X: 3 Y: 5.0 W: 6 H: 6 R: 0 G: 0 B: 255", animator.render());
  }

  @Test
  public void testOnTickLargeToEnd() {
    animator.addCommand(circleCmd1);
    animator.addCommand(circleCmd2);
    animator.addCommand(circleCmd3);
    animator.onTick();
    animator.onTick();
    animator.onTick();
    animator.onTick();
    animator.onTick();
    animator.onTick();
    animator.onTick();
    animator.onTick();
    animator.onTick();
    animator.onTick();
    animator.onTick();

    assertEquals("", animator.render());
  }
}
