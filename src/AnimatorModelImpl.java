import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Represents an Animator model implementation for creating, displaying, and manipulating shapes.
 */
public class AnimatorModelImpl implements AnimatorModel {

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
    this.commands.add(cmd);
  }

  @Override
  public void onTick() {
    for (Command cmd : commands) {
      if (cmd.getEnd() <= this.tick) {
        commands.remove(cmd);
      } else {
        if (cmd.getStart() <= this.tick) {
          cmd.update(this.tick);
        }
      }
      tick++;
    }
  }

  @Override
  public String render() {

    //Add title constructy shit

    String result = "";

    for (Command cmd : commands) {
      result += cmd.toString() + "\n";
    }
    // Trim newline
    return result.substring(0, result.length() - 1);
  }

  @Override
  public int getTick() {
    return this.tick;
  }
}
