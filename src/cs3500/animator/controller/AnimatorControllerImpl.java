package cs3500.animator.controller;

import cs3500.animator.model.ROModel;
import cs3500.animator.view.AnimatorView;

public class AnimatorControllerImpl implements AnimatorController {
  ROModel model;
  AnimatorView view;

  public AnimatorControllerImpl(ROModel model, AnimatorView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void animate(ROModel model, AnimatorView view) throws IllegalArgumentException,
          IllegalStateException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Don't be null.");
    }
    //Make it go
  }
}
