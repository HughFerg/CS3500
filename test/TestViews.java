import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.Point;

import cs3500.animator.model.AbstractShape;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.model.Circle;
import cs3500.animator.model.Command;
import cs3500.animator.model.Rectangle;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.VisualView;

// Testing class for Animator views.
public class TestViews {

  AnimatorModel model;
  AnimatorView visual;

  AbstractShape r1 = new Rectangle(new Color(100, 100, 100), new Point(100, 100), 300, 400);
  AbstractShape r2 = new Rectangle(new Color(100, 200, 0), new Point(200, 200), 300, 400);
  AbstractShape r3 = new Rectangle(new Color(100, 200, 0), new Point(200, 400), 500, 300);

  Command rectCmd1 = new Command("Rectangle1", 3, 4, r1, r2);
  Command rectCmd2 = new Command("Rectangle1",4, 6, r2, r3);
  Command rectCmd3 = new Command("Rectangle1",6, 10, r3, r3);

  AbstractShape c1 = new Circle(Color.BLACK, new Point(300, 300), 300);
  AbstractShape c2 = new Circle(Color.BLACK, new Point(300, 500), 300);
  AbstractShape c3 = new Circle(Color.BLUE, new Point(300, 500), 300);

  Command circleCmd1 = new Command("Circle1", 0, 5, c1, c1);
  Command circleCmd2 = new Command("Circle1",5, 7, c1, c2);
  Command circleCmd3 = new Command("Circle1",7, 10, c2, c3);


  @Before
  public void init() {
    model = new AnimatorModelImpl();

    model.addCommand(circleCmd1);
    model.addCommand(circleCmd2);
    model.addCommand(circleCmd3);

    visual = new VisualView(2, model, 200, 200, 500, 500);
  }

  @Test
  public void testInit() {
    visual.makeVisible();
  }
}
