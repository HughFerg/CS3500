
package cs3500.animator.model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.view.AnimatorView;

/**
 * Represents an Animator model implementation for creating, displaying, and manipulating shapes.
 */
public final class AnimatorModelImpl implements AnimatorModel {

  private ArrayList<Command> commands;
  private int tick = 0;

  // Creates an Animator Model with no commands.
  public AnimatorModelImpl() {
    this.commands = new ArrayList<>();
  }

  // Creates an Animator Model with the given commands.
  public AnimatorModelImpl(ArrayList<Command> commands) {
    this.commands = commands;
  }

  @Override
  public void addCommand(Command cmd) {
    if (validCommand(cmd)) {
      this.commands.add(cmd);
    } else {
      throw new IllegalArgumentException("Command is not valid.");
    }
  }

  /**
   * Returns if the given command is valid - that is, has non-conflicting start/end times, and does
   * not move a shape out of bounds or to a negative size.
   *
   * @param cmd the command to be tested.
   * @return true if the command is valid, false if not.
   */
  private boolean validCommand(Command cmd) {

    for (Command c : this.commands) {

      if (!cmd.getName().equals(c.getName())) {
        return true;
      } else {
        return (cmd.getStart() >= c.getEnd() || cmd.getEnd() <= c.getStart());
      }
    }
    return true;
  }

  @Override
  public void onTick() {
    List toRemove = new ArrayList();
    for (Command cmd : commands) {
      if (cmd.getEnd() <= this.tick) {
        toRemove.add(cmd);
      } else {
        if (cmd.getStart() <= this.tick) {
          cmd.update(this.tick);
        }
      }
    }
    for (Object past : toRemove) {
      commands.remove(past);
    }
    this.tick++;
  }

  @Override
  public ArrayList<Command> getCommands() {
    return new ArrayList<>(this.commands);
  }

  @Override
  public int getTick() {
    return this.tick;
  }

  // Represents a builder class for constructing an animation read by the AnimationReader
  public static final class Builder implements AnimationBuilder<AnimatorModel> {

    AnimatorModel model;
    AnimatorView view;

    private int x;
    private int y;
    private int width;
    private int height;

    private String newShapeName;
    private String newShapeType;

    Command cmd;

    @Override
    public AnimatorModel build() {

      model = new AnimatorModelImpl();
      model.addCommand(cmd);

      return model;
    }

    @Override
    public AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height) {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;

      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> declareShape(String name, String type) {
      this.newShapeName = name;
      this.newShapeType = type;

      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1, int y1, int w1,
                                                     int h1, int r1, int g1, int b1, int t2, int x2,
                                                     int y2, int w2, int h2, int r2, int g2,
                                                     int b2) {

      AbstractShape s1;
      AbstractShape s2;

      Color c1 = new Color(r1, g1, b1);
      Color c2 = new Color(r2, g2, b2);

      Point p1 = new Point(x1, y1);
      Point p2 = new Point(x2, y2);

      if (this.newShapeName.equals("ellipse")) {
        s1 = new Oval(c1, p1, w1, h1);
        s2 = new Oval(c2, p2, w2, h2);
      } else if (this.newShapeName.equals("rectangle")) {
        s1 = new Rectangle(c1, p1, w1, h1);
        s2 = new Rectangle(c2, p2, w2, h2);
      } else if (this.newShapeName.equals("triangle")) {
        s1 = new Triangle(c1, p1, w1);
        s2 = new Triangle(c2, p2, w2);
      } else {
        throw new IllegalArgumentException("Shape not recognized.");
      }

      this.cmd = new Command(name, t1, t2, s1, s2);

      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addKeyframe(String name, int t, int x, int y, int w,
                                                       int h, int r, int g, int b) {
      return null;
    }
  }
}