import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import cs3500.animator.model.AbstractShape;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModelImpl;
import cs3500.animator.model.Command;
import cs3500.animator.model.Rectangle;
import cs3500.animator.view.AnimatorView;
import cs3500.animator.view.VisualView;

// Testing class for Animator views.
public class TestViews {

  AnimatorModel model;
  AnimatorView visual;

  AbstractShape r1 = new Rectangle(new Color(100, 100, 100), new Point(1, 1), 3, 4);
  AbstractShape r2 = new Rectangle(new Color(100, 200, 0), new Point(2, 2), 3, 4);
  AbstractShape r3 = new Rectangle(new Color(100, 200, 0), new Point(2, 4), 5, 3);

  Command rectCmd1 = new Command("Rectangle1", 3, 4, r1, r2);
  Command rectCmd2 = new Command("Rectangle1",4, 6, r2, r3);
  Command rectCmd3 = new Command("Rectangle1",6, 10, r3, r3);


  @Before
  public void init() {
    model = new AnimatorModelImpl();

    model.addCommand(rectCmd1);
    model.addCommand(rectCmd2);
    model.addCommand(rectCmd3);

    visual = new VisualView(1, model, 200, 200, 300, 300);
  }

  @Test
  public void testInit() {
    visual.makeVisible();
  }
}
