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
import cs3500.animator.view.SVGView;

import static org.junit.Assert.assertEquals;

// Testing class for Animator views.
public class TestViews {

  AnimatorModel model;
  AnimatorView visual;

  AbstractShape r1 = new Rectangle(new Color(100, 100, 100), new Point(100, 100), 300, 400);
  AbstractShape r2 = new Rectangle(new Color(100, 200, 0), new Point(200, 200), 300, 400);
  AbstractShape r3 = new Rectangle(new Color(100, 200, 0), new Point(200, 400), 500, 300);

  Command rectCmd1 = new Command("Rectangle1", 300, 400, r1, r2);
  Command rectCmd2 = new Command("Rectangle1", 400, 600, r2, r3);
  Command rectCmd3 = new Command("Rectangle1", 600, 1000, r3, r3);

  AbstractShape c1 = new Circle(Color.BLACK, new Point(300, 300), 200);
  AbstractShape c2 = new Circle(Color.BLUE, new Point(300, 500), 200);
  AbstractShape c3 = new Circle(Color.BLUE, new Point(300, 500), 200);

  Command circleCmd1 = new Command("Circle1", 0, 100, c1, c2);
  Command circleCmd2 = new Command("Circle1", 500, 700, c1, c2);
  Command circleCmd3 = new Command("Circle1", 700, 1000, c2, c3);

  AbstractShape t1 = new Triangle(new Color(0, 100, 0), new Point(100, 100), 300);
  AbstractShape t2 = new Triangle(new Color(0, 200, 0), new Point(400, 400), 500);

  Command triCmd1 = new Command("Triangle1", 0, 400, t1, t2);
  Command triCmd2 = new Command("Triangle1", 400, 800, t2, t2);

  AbstractShape o1 = new Oval(Color.RED, new Point(220, 100), 100, 50);
  AbstractShape o2 = new Oval(Color.RED, new Point(220, 100), 100, 100);

  Command ovalCmd1 = new Command("Oval1", 0, 1000, o1, o2);

  @Before
  public void init() {
    model = new AnimatorModelImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModelText() {
    new TextView(null, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModelSVG() {
    new SVGView(1,null, 0, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadBoundsText() {
    new TextView(model.getCommands(), 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadTickSVG() {
    new SVGView(0, model.getCommands(), 0, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadDimensionSVG() {
    new SVGView(1, model.getCommands(), 0, 0, -10, 0);
  }

  @Test
  public void testEmptyTextView() {
    visual = new TextView(model.getCommands(), 0, 0);

    assertEquals(visual.getOutput(), "");
  }

  @Test
  public void testEmptySVGView() {
    visual = new SVGView(1, model.getCommands(), 0, 0, 0, 0);

    assertEquals(visual.getOutput(), "<svg width=\"0\" height=\"0\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n</svg>");
  }
}
