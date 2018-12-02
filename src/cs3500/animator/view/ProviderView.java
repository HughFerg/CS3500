package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.model.Command;
import cs3500.animator.provider.view.JFrameView;

/**
 * Represents an adapter class to interface between the AnimatorViews and the provider views.
 */
public class ProviderView extends JFrameView implements AnimatorView {

  /**
   * A constructor for the JFrameView that forms a JFrame view based on the given ticks per second.
   *
   * @param tps ticks per second
   * @throws IllegalArgumentException if ticks are not zero or positive
   */
  public ProviderView(int tps) throws IllegalArgumentException {
    super(tps);
  }

  @Override
  public String getOutput() {
    throw new UnsupportedOperationException("No output for provider view");
  }

  @Override
  public void makeVisible() {
    // NEED TO CREATE SIMILAR interface between models
    display();
  }

  @Override
  public void refresh(boolean playing) {

  }

  @Override
  public void writeToFile(String fileName) {
    throw new UnsupportedOperationException("No file write for provider view");
  }

  @Override
  public void makePanel() {

  }

  @Override
  public int getTps() {
    // NEED TO FIGURE OUT TIMER (if its in controller or view because provider has it in view)
    return this.timer.getDelay() / 1000;
  }

  @Override
  public void addListener(AnimatorController controller) {

  }

  @Override
  public void setCommands(ArrayList<Command> commands) {

  }

  @Override
  public boolean endTick() {
    return false;
  }
}
