package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.AnimatorModel2;
import cs3500.animator.model.Command;
import cs3500.animator.provider.view.ExtendedVisualView;

/**
 * Represents an adapter class to interface between the AnimatorViews and the provider views.
 */
public class ProviderView extends ExtendedVisualView implements AnimatorView {

  private AnimatorModel2 model;

  /**
   * A constructor for the JFrameView that forms a JFrame view based on the given ticks per second.
   *
   * @param tps ticks per second
   * @throws IllegalArgumentException if ticks are not zero or positive
   */
  public ProviderView(int tps, AnimatorModel2 model) throws IllegalArgumentException {
    super(tps);
    this.model = model;
  }

  @Override
  public String getOutput() {
    throw new UnsupportedOperationException("No output for provider view");
  }

  @Override
  public void makeVisible() {
    display(model);
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
    // Left blank because provider view already initializes panels in makeVisible().
  }

  @Override
  public int getTps() {
    // NEED TO FIGURE OUT TIMER (if its in controller or view because provider has it in view)
    //view should be able to just request from the controller I believe
    return this.timer.getDelay() / 1000;
  }

  @Override
  public void addListener(AnimatorController controller) {
    // Left blank because provider view does not have instance of controller but model instead.
  }

  @Override
  public void setCommands(ArrayList<Command> commands) {

  }

  @Override
  public boolean endTick() {
    return model.isOver(time);
  }

  @Override
  public int getTick() {
    return time;
  }
}
