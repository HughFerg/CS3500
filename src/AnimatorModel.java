/**
 * Represents the possible operations performed by an Animator object.
 */
public interface AnimatorModel {


  /**
   * Adds the given shape, with the given dimensions, to the animator model.
   */
  public void addShape();

  /**
   * Returns the String output rendering of the current model state.
   */
  public String render();
}
