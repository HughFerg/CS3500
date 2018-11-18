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
   * Adds the given command to the Animator.
   *
   * @param cmd the command to be added to the Animator.
   */
  public void addCommand(Command cmd) throws IllegalAccessException;

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
}

