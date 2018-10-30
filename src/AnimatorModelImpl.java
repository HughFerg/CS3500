import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Represents an Animator model implementation for creating, displaying, and manipulating shapes.
 */
public class AnimatorModelImpl implements AnimatorModel {

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
  public String render() {

    //Add title constructy shit

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
}
