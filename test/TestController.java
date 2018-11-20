import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.Point;

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
import cs3500.animator.view.TextView;

import static org.junit.Assert.assertEquals;

public class TestController {
  AnimatorModel animator;

  AnimatorView editor;

  AnimatorController controller;

  AbstractShape c1 = new Circle(Color.BLACK, new Point(3, 3), 3);
  AbstractShape c2 = new Circle(Color.BLACK, new Point(3, 5), 3);
  AbstractShape c3 = new Circle(Color.BLUE, new Point(3, 5), 3);

  AbstractShape r1 = new cs3500.animator.model.Rectangle(new Color(100, 100, 100), new Point(1, 1), 3, 4);
  AbstractShape r2 = new cs3500.animator.model.Rectangle(new Color(100, 200, 0), new Point(2, 2), 3, 4);
  AbstractShape r3 = new Rectangle(new Color(100, 200, 0), new Point(2, 4), 5, 3);

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


  @Before
  public void init() {
    animator = new AnimatorModelImpl();
    //animator.addCommand(rectCmd1);
    //animator.addCommand(rectCmd2);

    editor = new EditorView(25, animator.getCommands());
    controller = new AnimatorControllerImpl(new ROModel(animator), editor);
  }

  @Test
  public void testDeleteShape() {
    controller.deleteShape("Rectangle1");
    assertEquals(animator.getCommands().size(), 0);
  }
}