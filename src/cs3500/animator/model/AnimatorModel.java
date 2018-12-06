package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the possible operations performed by an Animator object.
 */
public interface AnimatorModel {

  /**
   * Updates all shapes based on the current active commands.
   */
  void tick(int tick);

  /**
   * Returns a list of current shape names.
   *
   * @return list of shape names.
   */
  List<String> getShapeNames();

  /**
   * Returns this Animator's list of commands.
   *
   * @return This Animator's commands.
   */
  ArrayList<Command> getCommands();

  /**
   * Sets the bounds for the canvas.
   *
   * @param x the x value of the canvas.
   * @param y the y value of the canvas.
   * @param w the width of the canvas.
   * @param h the height of the canvas.
   * @throws IllegalArgumentException if the width or height are less than 0.
   */
  void setBounds(int x, int y, int w, int h) throws IllegalArgumentException;

  /**
   * Returns if the animation has finished running.
   *
   * @return if the animation is over or not.
   */
  boolean isOver(int time);

  /**
   * Resets the current animation to tick 0 and reinitialize the commands.
   */
  void reset();

  /**
   * Gets the x location.
   *
   * @return x coordinate.
   */
  int getCanvasX();

  /**
   * Gets the y location.
   *
   * @return y coordinate.
   */
  int getCanvasY();

  /**
   * Gets the width of this model.
   *
   * @return width.
   */
  int getCanvasWidth();

  /**
   * Gets the height of this model.
   *
   * @return height.
   */
  int getCanvasHeight();

  /**
   * Deletes the given shape.
   *
   * @param name the shape name to be deleted.
   */
  void deleteShape(String name);

  /**
   * Deletes the given command.
   *
   * @param name name of the command to be deleted.
   */
  void deleteCommand(String name);

  /**
   * Adds the given keyframe to the model.
   *
   * @param shapename type of the new shape.
   * @param name      name of the shape.
   * @param time      time of Kf.
   * @param x         x location.
   * @param y         y location.
   * @param w         width.
   * @param h         height.
   * @param r         red val.
   * @param g         green val.
   * @param b         blue val.
   */
  void addKeyFrame(String shapename, String name, int time, int x, int y, int w,
                   int h,
                   int r, int g, int b);

  /**
   * Gets the ticking tick.
   *
   * @return the tick.
   */
  int getTick();
}

