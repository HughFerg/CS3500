package cs3500.animator.controller;

import cs3500.animator.model.ROModel;
import cs3500.animator.view.AnimatorView;

/**
 * This interface represents the operations offered by the Animator Controller. An instance of an
 * AnimatorController connects a model and a view.
 */
public interface AnimatorController {

  /**
   * Runs a new animation using the given model and view.
   *
   * @param model The AnimatorModel which represents the animation being played
   * @param view  The AnimatorView which represents the visual representation of the model
   * @throws IllegalArgumentException if the controller's model or view is null
   * @throws IllegalStateException    if the controller is unable to successfully receive input or
   *         transmit output
   */
  void animate(ROModel model, AnimatorView view) throws IllegalArgumentException,
          IllegalStateException;
}
