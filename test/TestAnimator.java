import org.junit.Before;
import org.junit.Test;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

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

  ArrayList<Command> commands = new ArrayList<Command>();


  @Before
  public void init() {
    commands.add(triCmd1);
    commands.add(triCmd2);
    commands.add(rectCmd1);
    commands.add(rectCmd2);
    commands.add(rectCmd3);
    commands.add(circleCmd1);
    commands.add(circleCmd2);
    commands.add(circleCmd3);

    animator = new AnimatorModelImpl(800, 700, 0, 1, commands);
    textView = new TextView(animator.getCommands(),animator.getCanvasWidth(), animator.getCanvasHeight());
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
  public void testGetCanvasW() {
    assertEquals(800, animator.getCanvasWidth());
  }

  @Test
  public void testGetCanvasH() {
    assertEquals(700, animator.getCanvasHeight());
  }

  @Test
  public void testGetCanvasX() {
    assertEquals(0, animator.getCanvasX());
  }

  @Test
  public void testGetCanvasY() {
    assertEquals(1, animator.getCanvasY());
  }

  @Test
  public void testDeleteNonexistantShape() {
    assertEquals(8, animator.getCommands().size());
    animator.deleteShape("sdaf");
    assertEquals(8, animator.getCommands().size());
  }

  @Test
  public void testDeleteShape() {
    assertEquals(8, animator.getCommands().size());
    animator.deleteShape("Circle1");
    assertEquals(5, animator.getCommands().size());
  }

  @Test
  public void testDeleteCommand() {
    animator.deleteCommand("Circle1 5");
    assertEquals(7, animator.getCommands().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addInvalidShapeType() {
    animator.addKeyFrame("fieri", "Circle1 11", 11, 5, 5, 100, 100, 100, 100, 100);
  }

  @Test
  public void testAddCommand() {
    animator.addKeyFrame("oval", "Circle1 11", 11, 5, 5, 100, 100, 100, 100, 100);
    assertEquals(9, animator.getCommands().size());
  }

  @Test
  public void testIsOver() {
    assertEquals(false, animator.isOver(0));
    assertEquals(false, animator.isOver(5));
    assertEquals(true, animator.isOver(11));
  }

  @Test
  public void testIncrementTickEmpty() {
    animator.tick(1);
    assertEquals(1, animator.getTick());
  }

  @Test
  public void testReset() {
    animator.tick(10);
    assertEquals(10, animator.getTick());

    animator.reset();
    assertEquals(0, animator.getTick());
  }
}

