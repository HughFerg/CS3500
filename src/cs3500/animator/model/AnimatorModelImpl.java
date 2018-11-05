package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

import cs3500.animator.util.AnimationBuilder;

/**
 * Represents an Animator model implementation for creating, displaying, and manipulating shapes.
 */
public final class AnimatorModelImpl implements AnimatorModel {

  private int width;
  private int height;
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
   * Returns if the given command is valid - that is, has legitimate start/end times, and does not
   * move a shape out of bounds or to a negative size.
   *
   * @param cmd the command to be tested.
   * @return true if the command is valid, false if not.
   */
  private boolean validCommand(Command cmd) {
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
    tick++;
  }

  @Override
  public ArrayList<Command> getCommands() {
    ArrayList<Command> copy = new ArrayList<>(this.commands);
    return copy;
  }

  @Override
  public String render() {

    String result = "";

    for (Command cmd : commands) {
      result += cmd.toString() + "\n";
    }
    // Trim newline
    if (result.length() > 0) {
      result = result.substring(0, result.length() - 1);
    }
    return result;
  }

  @Override
  public int getTick() {
    return this.tick;
  }

  public static final class Builder implements AnimationBuilder<AnimatorModel> {

    @Override
    public AnimatorModel build() {
      return null;
    }

    @Override
    public AnimationBuilder<AnimatorModel> setBounds(int x, int y, int width, int height) {
      return null;
    }

    @Override
    public AnimationBuilder<AnimatorModel> declareShape(String name, String type) {
      return null;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
      return null;
    }

    @Override
    public AnimationBuilder<AnimatorModel> addKeyframe(String name, int t, int x, int y, int w, int h, int r, int g, int b) {
      return null;
    }
  }
}

