import java.util.List;

/**
 * Represents an Animator model implementation for creating, displaying, and manipulating shapes.
 */
public class AnimatorModelImpl implements AnimatorModel {

  private int width;
  private int height;
  private List<Command> commands;
  private int tick = 0;

  // construct

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
    return result.substring(0, result.length() - 2);
  }

  @Override
  public int getTick() {
    return this.tick;
  }
}
