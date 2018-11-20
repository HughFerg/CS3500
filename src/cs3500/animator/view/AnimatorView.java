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
   * @param playing
   */
  void refresh(boolean playing);

  /**
   * Writes the view's output to the given file.
   */
  void writeToFile(String fileName);

  /*
   * Places the view onto a JPanel for display.
   */
  void makePanel();

  /**
   * Returns this view's ticks/second.
   */
  int getTps();

  /**
   *
   * @param controller
   */
  void addListener(AnimatorController controller);

  /*

   */
  void setCommands(ArrayList<Command> commands);

  /*

   */
  boolean endTick();
}
