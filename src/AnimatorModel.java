/**
 * Represents the possible operations performed by an Animator object.
 */
public interface AnimatorModel {

  /**
   * Returns the String output rendering of the current model state.
   */
  public String render();

  /**
   * Updates all shapes based on the current active commands.
   */
  public void onTick();

  /**
   * Adds the given command to the Animator.
   *
   * @param cmd the command to be added to the Animator.
   */
  public void addCommand(Command cmd);

  /**
   * Returns the current tick of this Animator.
   *
   * @return current tick.
   */
  public int getTick();
}
