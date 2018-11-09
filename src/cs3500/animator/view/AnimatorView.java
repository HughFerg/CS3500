package cs3500.animator.view;

// Represents the public-facing operations of an Animator view.
public interface AnimatorView {

  /**
   * Generates String representations of data for the views.
   * @return String data for the views
   */
  public String getOutput();

  /**
   * Provides the outward facing representation to the user.
   */
  public void makeVisible();

  /**
   * Updates the frames in an animation. Only used in VisualView.
   */
  public void refresh();
}
