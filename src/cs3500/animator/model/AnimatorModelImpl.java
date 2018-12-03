
package cs3500.animator.model;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import cs3500.animator.util.AnimationBuilder;

/**
 * Represents an Animator model implementation for creating, displaying, and manipulating shapes.
 */
public final class AnimatorModelImpl implements AnimatorModel {

  private ArrayList<Command> commands;
  private int tick = 0;
  private int w;
  private int h;
  private int x;
  private int y;

  // Creates an Animator Model with no commands.
  public AnimatorModelImpl() {
    this.commands = new ArrayList<>();
  }

  /**
   Creates an Animatormodel with the given dimensions and start point.
   */
  public AnimatorModelImpl(int w, int h, int x, int y, ArrayList<Command> commands) {
    this.w = w;
    this.h = h;
    this.x = x;
    this.y = y;
    this.commands = commands;
  }

  // Creates an Animator Model with the given commands.
  public AnimatorModelImpl(ArrayList<Command> commands) {
    this.commands = commands;
  }

  @Override
  public void onTick() {
    this.tick += 1;
  }

  @Override
  public ArrayList<Command> getCommands() {

    ArrayList<Command> next = new ArrayList<>(commands.size());
    for (Command c : commands) {
      next.add(c.clone());
    }
    return next;
  }

  @Override
  public boolean isOver(int time) {

    int lastTime = 0;

    for (Command c : commands) {
      if (c.getEnd() > lastTime) {
        lastTime = c.getEnd();
      }
    }

    return (time == lastTime);
  }

  @Override
  public int getCanvasX() {
    return x;
  }

  @Override
  public int getCanvasY() {
    return y;
  }

  @Override
  public int getCanvasWidth() {
    return w;
  }

  @Override
  public int getCanvasHeight() {
    return h;
  }

  @Override
  public void deleteShape(String name) {
    ArrayList<Command> toRemove = new ArrayList<>();
    for (Command c : commands) {
      if (c.getName().equals(name)) {
        toRemove.add(c);
      }
    }
    for (Command aboutToRemove : toRemove) {
      commands.remove(aboutToRemove);
    }
  }

  @Override
  public void deleteCommand(String name) {
    String[] data = name.split(" ");
    String shapeName = data[0];
    int start = Integer.parseInt(data[1]);
    ArrayList<Command> specifiedShape = new ArrayList<>();

    for (Command c : commands) {
      if (c.getName().equals(shapeName)) {
        specifiedShape.add(c);
      }
    }

    if (specifiedShape.size() == 1) {
      this.deleteShape(shapeName);
    }

    if (start == specifiedShape.get(0).getStart()) {
      commands.remove(specifiedShape.get(0));
    } else if (start == specifiedShape.get(specifiedShape.size() - 1).getStart()) {
      commands.add(new Command(name, specifiedShape.get(specifiedShape.size() - 2).getStart(),
              specifiedShape.get(specifiedShape.size() - 1).getEnd(),
              specifiedShape.get(specifiedShape.size() - 2).getCurrent(),
              specifiedShape.get(specifiedShape.size() - 1).getDest()));
      commands.remove(specifiedShape.get(specifiedShape.size() - 2));
      commands.remove(specifiedShape.get(specifiedShape.size() - 1));
    } else {
      for (int i = 1; i < specifiedShape.size() - 1; i += 1) {
        if (start == specifiedShape.get(i).getStart()) {
          commands.remove(specifiedShape.get(i));
          Command temp = specifiedShape.get(i - 1);
          commands.add(commands.indexOf(temp), new Command(name, temp.getStart(),
                  specifiedShape.get(i + 1).getStart(), temp.getCurrent(),
                  specifiedShape.get(i + 1).getCurrent()));
          commands.remove(temp);
        }
      }
    }
  }

  @Override
  public void addKeyFrame(String shapename, String name, int time, int x, int y, int w,
                          int h,
                          int r, int g, int b) {

    ArrayList<Command> toRm = new ArrayList<>();
    ArrayList<Command> toAdd = new ArrayList<>();

    for (Command c : commands) {
      // If KF already exists
      if (c.getName().equals(name)) {
        if (c.getStart() == time) {
          c.replaceCurrent(x, y, w, h, r, g, b);
        } else if (c.getEnd() == time) {
          toAdd.add(new Command(name, c.getStart(), c.getEnd(), c.getCurrent(),
                  c.replaceDest(x, y, w, h, r, g, b)));
          toRm.add(c);
        } else {
          if (c.getStart() < time && c.getEnd() > time) {
            toRm.add(c);
            toAdd.add(new Command(name, c.getStart(), time, c.replaceCurrent(x, y, w, h, r, g,
                    b),
                    c.getDest()));
            toAdd.add(new Command(name, time, c.getEnd(), c.getCurrent(), c.replaceDest(x, y, w, h,
                    r, g, b)));
          }
        }
      }
    }

    // If shape does not exist yet
    if (toAdd.isEmpty()) {

      AbstractShape nextShape;

      if (shapename.equals("tri")) {

        nextShape = new Triangle(new Color(r, g,
                b), new Point(x, y),
                w);

        toAdd.add(new Command(name, time, time, nextShape,
                nextShape));

      } else if (shapename.equals("rect")) {

        nextShape = new Rectangle(new Color(r, g,
                b), new Point(x, y),
                w, h);

        toAdd.add(new Command(name, time, time, nextShape,
                nextShape));

      } else if (shapename.equals("oval")) {

        nextShape = new Oval(new Color(r, g,
                b), new Point(x, y),
                w, h);

        toAdd.add(new Command(name, time, time, nextShape,
                nextShape));
      } else {
        throw new IllegalArgumentException("Invalid shape type.");
      }
    }
    commands.addAll(toAdd);
    commands.removeAll(toRm);
  }

  // Represents a builder class for constructing an animation read by the AnimationReader
  public static final class Builder implements AnimationBuilder<AnimatorModel> {

    AnimatorModel model;

    private int x;
    private int y;
    private int width;
    private int height;

    private HashMap<String, String> shapes = new HashMap<>();
    ArrayList<Command> commands = new ArrayList<>();

    @Override
    public AnimatorModel build() {

      model = new AnimatorModelImpl(width, height, x, y, this.commands);
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

      shapes.put(name, type);

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

      String newShapeType = this.shapes.get(name);

      if (newShapeType.equals("ellipse")) {
        s1 = new Oval(c1, p1, w1 / 2, h1 / 2);
        s2 = new Oval(c2, p2, w2 / 2, h2 / 2);
      } else if (newShapeType.equals("rectangle")) {
        s1 = new Rectangle(c1, p1, w1, h1);
        s2 = new Rectangle(c2, p2, w2, h2);
      } else if (newShapeType.equals("triangle")) {
        s1 = new Triangle(c1, p1, w1);
        s2 = new Triangle(c2, p2, w2);
      } else {
        throw new IllegalArgumentException("Shape not recognized.");
      }

      this.commands.add(new Command(name, t1, t2, s1, s2));

      return this;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addKeyframe(String name, int t, int x, int y, int w,
                                                       int h, int r, int g, int b) {
      addMotion(name, t, x, y, w, g, r, g, b, t, x, y, w, g, r, g, b);
      return this;
    }

  }

  public void reset() {
    tick = 0;
  }
}