import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.controller.AnimatorControllerImpl;
import cs3500.animator.model.AbstractShape;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.model.Circle;
import cs3500.animator.model.Command;
import cs3500.animator.model.ROModel;
import cs3500.animator.model.Rectangle;
import cs3500.animator.model.Triangle;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.EditorView;

import static org.junit.Assert.assertEquals;

public class TestController {
  AnimatorModel animator;

  AnimatorView editor;

  AnimatorController controller;

  AbstractShape c1 = new Circle(Color.BLACK, new Point(3, 3), 3);
  AbstractShape c2 = new Circle(Color.BLACK, new Point(3, 5), 3);
  AbstractShape c3 = new Circle(Color.BLUE, new Point(3, 5), 3);

  AbstractShape r1 = new cs3500.animator.model.Rectangle(new Color(100, 100, 100),
          new Point(1, 1), 3, 4);
  AbstractShape r2 = new cs3500.animator.model.Rectangle(new Color(100, 200, 0),
          new Point(2, 2), 3, 4);
  AbstractShape r3 = new Rectangle(new Color(100, 200, 0), new Point(2, 4),
          5, 3);

  AbstractShape t1 = new Triangle(new Color(0, 100, 0), new Point(1, 1), 3);
  AbstractShape t2 = new Triangle(new Color(0, 200, 0), new Point(4, 4), 5);

  Command triCmd1 = new Command("Triangle1", 0, 4, t1, t2);
  Command triCmd2 = new Command("Triangle1", 4, 8, t2, t2);

  Command rectCmd1 = new Command("Rectangle1", 3, 4, r1, r2);
  Command rectCmd2 = new Command("Rectangle1", 4, 6, r2, r3);
  Command rectCmd3 = new Command("Rectangle1", 6, 10, r3, r3);

  Command circleCmd1 = new Command("Circle1", 0, 5, c1, c1);
  Command circleCmd2 = new Command("Circle1", 5, 7, c1, c2);
  Command circleCmd3 = new Command("Circle1", 7, 10, c2, c3);

  ArrayList<Command> commandList = new ArrayList<>();

  @Before
  public void init() {

    commandList.add(triCmd1);
    commandList.add(triCmd2);
    commandList.add(rectCmd1);
    commandList.add(rectCmd2);
    commandList.add(rectCmd3);
    commandList.add(circleCmd1);
    commandList.add(circleCmd3);
    commandList.add(circleCmd2);

    animator = new AnimatorModelImpl(commandList);

    editor = new EditorView(25, animator.getCommands());
    controller = new AnimatorControllerImpl(new ROModel(animator), editor);
  }

  @Test
  public void testDeleteShape() {
    controller.deleteShape("Rectangle1");
    assertEquals(animator.getCommands().size(), 5);
  }

  @Test
  public void testDeleteInvalidShape() {
    controller.deleteShape("Rectangle1dfsahjklh");
    assertEquals(8, animator.getCommands().size());
  }

  @Test
  public void testRestart() {
    assertEquals(0, animator.getTick());
    animator.tick(50);
    assertEquals(50, animator.getTick());
    animator.reset();
    assertEquals(0, animator.getTick());
  }

  @Test
  public void testDeleteShapes() {
    assertEquals(animator.getCommands().size(), 8);
    controller.deleteShape("Rectangle1");
    controller.deleteShape("Circle1");
    assertEquals(animator.getCommands().size(), 2);
  }

  @Test
  public void testDeleteCommand() {
    controller.deleteCommand("Circle1 0");
    assertEquals(animator.getCommands().size(), 7);
  }

  @Test
  public void testDeleteCommands() {
    controller.deleteCommand("Circle1 0");
    controller.deleteCommand("Triangle1 4");
    assertEquals(animator.getCommands().size(), 6);
  }

  @Test
  public void testAddKF() {
    controller.addKeyFrame("rect", "Rectangle1",
            10, 5, 5, 5, 5, 23, 23, 23);
    assertEquals(animator.getCommands().size(), 8);
  }

  @Test
  public void testAddManyKF() {
    controller.addKeyFrame("rect", "Rectangle1",
            0, 5, 5, 5, 5, 23, 23, 23);
    controller.addKeyFrame("rect", "Rectangle1",
            10, 5, 5, 5, 5, 23, 23, 23);
    controller.addKeyFrame("rect", "Circle1",
            6, 5, 5, 5, 5, 23, 23, 23);
    assertEquals(animator.getCommands().size(), 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateInvalidController2Null() {
    new AnimatorControllerImpl(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateInvalidControllerNullModel() {
    new AnimatorControllerImpl(null, editor);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateInvalidControllerNullView() {
    new AnimatorControllerImpl(new ROModel(animator), null);
  }
}