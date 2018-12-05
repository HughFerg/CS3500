package cs3500.animator.controller;

/**
 * This interface represents the operations offered by the Animator Controller. An instance of an
 * AnimatorController connects a model and a view.
 */
public interface AnimatorController {

  /**
   * Runs a new animation using the given model and view.
   *
   * @throws IllegalArgumentException if the controller's model or view is null
   * @throws IllegalStateException    if the controller is unable to successfully receive input or
   *         transmit output
   */
  void animate();

  /**
   * Plays the animation.
   */
  void play();

  /**
   * Pauses the animation.
   */
  void pause();

  /**
   * Restarts the animation.
   */
  void restart();

  /**
   * Changes the ticks/second by the given amount.
   * @param delta the amount to change the tps by.
   */
  void changeSpeed(int delta);

  /**
   * Returns if the animation is on its last tick.
   * @return if it is the last tick.
   */
  boolean endTick(int t);

  /**
   * Loops the animation.
   */
  void loop();

  /**
   * Deletes the given shape.
   * @param name name of shape to be deleted.
   */
  void deleteShape(String name);

  /**
   * Deletes the given command.
   * @param name name of command to be deleted.
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

