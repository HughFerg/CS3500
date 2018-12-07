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
  public void testBadTickSVG() {
    new SVGView(0, model.getCommands(), 0, 0, 0, 0);
  }

  @Test
  public void testEmptyTextView() {
    visual = new TextView(model.getCommands(), 0, 0);

    assertEquals(visual.getOutput(), "");
  }

  @Test
  public void testEmptySVGView() {
    visual = new SVGView(1, model.getCommands(), 0, 0, 0, 0);

    assertEquals(visual.getOutput(), "<svg width=\"1\" height=\"2\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n</svg>");
  }

  @Test
  public void testSmallTextView() throws IllegalAccessException {
    model.addCommand(circleCmd1);
    visual = new TextView(model.getCommands(), 0, 0);

    assertEquals(visual.getOutput(), "Circle1 - Start: 0 X: 300 Y: 300.0 W: 400 H: 400 R: " +
            "0 G: 0 B: 0 --- End: 100 X: 300 Y: 500.0 W: 400 H: 400 R: 0 G: 0 B: 255");
  }

  @Test
  public void testSmallSVGView() throws IllegalAccessException {
    model.addCommand(circleCmd1);
    visual = new SVGView(1, model.getCommands(), 0, 0, 0, 0);

    assertEquals(visual.getOutput(), "<svg width=\"1\" height=\"2\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<ellipse id=\"Circle1\" cx=\"300\" cy=\"300\" rx=\"200\" ry=\"200\" " +
            "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"0000.0ms\" dur=\"100000.0ms\" " +
            "attributeName=\"cy\" from=\"300\" to=\"500\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"0000.0ms\" dur=\"100000.0ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(0,0,255)\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "</svg>");
  }

  @Test
  public void testBigTextView() throws IllegalAccessException {
    model.addCommand(circleCmd1);
    model.addCommand(rectCmd1);
    model.addCommand(triCmd1);
    visual = new TextView(model.getCommands(), 0, 0);

    assertEquals(visual.getOutput(), "Circle1 - Start: 0 X: 300 Y: 300.0 W: 400 H: " +
            "400 R: 0 G: 0 B: 0 --- End: 100 X: 300 Y: 500.0 W: 400 H: 400 R: 0 G: 0 B: 255\n" +
            "Rectangle1 - Start: 300 X: 100 Y: 100.0 W: 300 H: 400 R: 100 G: 100 B: 100 --- " +
            "End: 400 X: 200 Y: 200.0 W: 300 H: 400 R: 100 G: 200 B: 0\n" +
            "Triangle1 - Start: 0 X: 100 Y: 100.0 W: 300 H: 259 R: 0 G: 100 B: 0 --- " +
            "End: 400 X: 400 Y: 400.0 W: 500 H: 433 R: 0 G: 200 B: 0");
  }

  @Test
  public void testBigSVGView() throws IllegalAccessException {
    model.addCommand(circleCmd1);
    model.addCommand(rectCmd1);
    model.addCommand(triCmd1);
    visual = new SVGView(1, model.getCommands(), 0, 0, 0, 0);

    assertEquals(visual.getOutput(), "<svg width=\"1\" height=\"2\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<ellipse id=\"Circle1\" cx=\"300\" cy=\"300\" rx=\"200\" ry=\"200\" " +
            "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"0000.0ms\" dur=\"100000.0ms\" " +
            "attributeName=\"cy\" from=\"300\" to=\"500\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"0000.0ms\" dur=\"100000.0ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(0,0,255)\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "<rect id=\"Rectangle1\" x=\"100\" y=\"100\" width=\"300\" height=\"400\" " +
            "fill=\"rgb(100,100,100)\" visibility=\"visible\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"300000.0ms\" dur=\"400000.0ms\" " +
            "attributeName=\"x\" from=\"100\" to=\"200\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"300000.0ms\" dur=\"400000.0ms\" " +
            "attributeName=\"y\" from=\"100\" to=\"200\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"300000.0ms\" dur=\"400000.0ms\" " +
            "attributeName=\"fill\" from=\"rgb(100,100,100)\" to=\"rgb(100,200,0)\" " +
            "fill=\"freeze\" />\n" +
            "</rect>\n" +
            "<polygon id=\"Triangle1\" points=\"100,100 250,-159 400,100\" " +
            "fill=\"rgb(0,100,0)\" visibility=\"visible\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"0000.0ms\" dur=\"400000.0ms\" " +
            "attributeName=\"points\" from=\"400,100 250,-159 400,100\" to=\"400,400 " +
            "650,-33 900,400\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"0000.0ms\" dur=\"400000.0ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,100,0)\" to=\"rgb(0,200,0)\" " +
            "fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"0000.0ms\" dur=\"400000.0ms\" " +
            "attributeName=\"points\" from=\"400,100 250,-159 400,100\" to=\"400,400 650,-33 " +
            "900,400\" fill=\"freeze\" />\n" +
            "</polygon>\n" +
            "</svg>");
  }

  @Test
  public void testSameShapeTextView() throws IllegalAccessException {
    model.addCommand(circleCmd1);
    model.addCommand(circleCmd2);
    model.addCommand(circleCmd3);
    visual = new TextView(model.getCommands(), 0, 0);

    assertEquals(visual.getOutput(), "Circle1 - Start: 0 X: 300 Y: 300.0 W: 400 H: " +
            "400 R: 0 G: 0 B: 0 --- End: 100 X: 300 Y: 500.0 W: 400 H: 400 R: 0 G: 0 B: 255\n" +
            "Circle1 - Start: 500 X: 300 Y: 300.0 W: 400 H: 400 R: 0 G: 0 B: 0 --- " +
            "End: 700 X: 300 Y: 500.0 W: 400 H: 400 R: 0 G: 0 B: 255\n" +
            "Circle1 - Start: 700 X: 300 Y: 500.0 W: 400 H: 400 R: 0 G: 0 B: 255 --- " +
            "End: 1000 X: 300 Y: 500.0 W: 400 H: 400 R: 0 G: 0 B: 255");
  }

  @Test
  public void testSameShapeSVGView() throws IllegalAccessException {
    model.addCommand(circleCmd1);
    model.addCommand(circleCmd2);
    model.addCommand(circleCmd3);
    visual = new SVGView(1, model.getCommands(), 0, 0, 0, 0);

    assertEquals(visual.getOutput(), "<svg width=\"1\" height=\"2\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "<ellipse id=\"Circle1\" cx=\"300\" cy=\"300\" rx=\"200\" ry=\"200\" " +
            "fill=\"rgb(0,0,0)\" visibility=\"visible\" >\n" +
            "    <animate attributeType=\"xml\" begin=\"0000.0ms\" dur=\"100000.0ms\" " +
            "attributeName=\"cy\" from=\"300\" to=\"500\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"0000.0ms\" dur=\"100000.0ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(0,0,255)\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"500000.0ms\" dur=\"700000.0ms\" " +
            "attributeName=\"cy\" from=\"300\" to=\"500\" fill=\"freeze\" />\n" +
            "    <animate attributeType=\"xml\" begin=\"500000.0ms\" dur=\"700000.0ms\" " +
            "attributeName=\"fill\" from=\"rgb(0,0,0)\" to=\"rgb(0,0,255)\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "</svg>");
  }
}
