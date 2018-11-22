package cs3500.animator.model;

import java.util.ArrayList;

/**
 * Represents the possible operations performed by an Animator object.
 */
public interface AnimatorModel {

  /**
   * Updates all shapes based on the current active commands.
   */
  public void onTick();

  /**
   * Returns this Animator's list of commands.
   * @return This Animator's commands.
   */
  public ArrayList<Command> getCommands();

  /**
   * Returns the current tick of this Animator.
   *
   * @return current tick.
   */
  public int getTick();

  /**
   * Resets the current animation to tick 0 and reinitializes the commands.
   */
  public void reset();

  /**
   * Gets the x location.
   * @return x coordinate.
   */
  public int getX();

  /**
   * Gets the y location.
   * @return y coordinate.
   */
  public int getY();

  /**
   * Gets the width of this model.
   * @return width.
   */
  public int getW();

  /**
   * Gets the height of this model.
   * @return height.
   */
  public int getH();

  /**
   * Deletes the given shape.
   * @param name the shape name to be deleted.
   */
  void deleteShape(String name);

  /**
   * Deletes the given command.
   * @param name name of the command to be deleted.
   */
  void deleteCommand(String name);

  /**
   * Adds the given keyframe to the model.
   * @param shapename type of the new shape.
   * @param name name of the shape.
   * @param time time of Kf.
   * @param x x location.
   * @param y y location.
   * @param w width.
   * @param h height.
   * @param r red val.
   * @param g green val.
   * @param b blue val.
   */
  void addKeyFrame(String shapename, String name, int time, int x, int y, int w,
                   int h,
                   int r, int g, int b);
}

