package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.controller.AnimatorController;
import cs3500.animator.model.Command;

// Represents the public-facing operations of an Animator view.
public interface AnimatorView {

  /**
   * Generates String representations of data for the views.
   *
   * @return String data for the views.
   */
  String getOutput();

  /**
   * Provides the outward facing representation to the user.
   */
  void makeVisible();

  /**
   * Updates the frames in an animation. Only used in VisualView.
   *
   * @param playing if the animation is playing or not.
   */
  void refresh(boolean playing, ArrayList<Command> commands);

  /**
   * Writes the view's output to the given file.
   */
  void writeToFile(String fileName);

  /*
   * Places the view onto a JPanel for display.
   */
  void makePanel();

  /**
   * Resets the time back to 0.
   */
  void reset();

  /**
   * Returns this view's ticks/second.
   */
  int getTps();

  /**
   * Adds the given controller to this view.
   *
   * @param controller controller to add.
   */
  void addListener(AnimatorController controller);

  /**
   * Sets the list of commands for this view.
   *
   * @param commands list of commands.
   */
  void setCommands(ArrayList<Command> commands);

  /**
   * Checks if the view is at its last tick.
   *
   * @return if view has ended.
   */
  boolean endTick();

  /**
   * Returns the current tick of this view.
   */
  int getTick();
}
