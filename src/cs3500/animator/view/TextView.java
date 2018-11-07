package cs3500.animator.model.view;

import java.util.ArrayList;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Command;

public class TextView extends AbstractView implements AnimatorView {

  public TextView(AnimatorModel model, int w, int h) {
    super(0, model, 0, 0, w, h);
  }

  @Override
  public void makeVisible() {

    String result = this.getOutput();

    System.out.print(result);
  }

  public String getOutput() {
    ArrayList<Command> commands = this.model.getCommands();

    // ADD TITLE CONSTRUCTY SHIT + CANVAS

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
  public void refresh() {
    /**
     * Left blank because text view does not print in real time.
     */
  }
}
