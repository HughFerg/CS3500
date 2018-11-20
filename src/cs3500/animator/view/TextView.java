package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.model.Command;

// Represents a text-based view for the Animator that displays the shapes and their
// transformations over time.
public class TextView extends AbstractView {

  public TextView(ArrayList<Command> viewCommands, int w, int h) {
    super(1, viewCommands, 0, 0, w, h);
  }

  public TextView(ArrayList<Command> viewCommands) {
    super(1, viewCommands);
  }

  @Override
  public void makeVisible() {
    System.out.print(this.getOutput());
  }

  @Override
  public String getOutput() {
    ArrayList<Command> commands = this.viewCommands;

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
  public void makePanel() {
    throw new UnsupportedOperationException("Text view does not have a panel.");
  }

  @Override
  public void refresh(boolean playing) {
    throw new UnsupportedOperationException("Text view does not print in real time.");
  }
}
