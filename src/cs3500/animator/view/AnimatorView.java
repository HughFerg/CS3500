package cs3500.animator.view;

// Represents the public-facing operations of an Animator view.
public interface AnimatorView {

  /**
   * Generates String representations of data for the views.
   *
   * @return String data for the views.
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

  /**
   * Returns if there are any more commands to be processed.
   */
  public boolean hasCommands();

  /**
   * Writes the view's output to the given file.
   */
  public void writeToFile(String fileName);

  /*
   * Places the view onto a JPanel for display.
   */
  public void makePanel();

  /**
   * Returns this view's ticks/second.
   */
  public int getTps();
}
