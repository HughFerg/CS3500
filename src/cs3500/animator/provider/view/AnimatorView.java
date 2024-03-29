package cs3500.animator.provider.view;

import cs3500.animator.model.AnimatorModel2;

/**
 * Classes that implement this interface will in some way display an animation whether through text
 * or images.
 */

public interface AnimatorView {

  /**
   * Executes a display for the type of view based off an animation model that it is given.
   *
   * @param model the given animation model
   */
  void display(AnimatorModel2 model);

}
