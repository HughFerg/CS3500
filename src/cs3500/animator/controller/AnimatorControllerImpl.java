package cs3500.animator.controller;

import cs3500.animator.model.ROModel;
import cs3500.animator.view.AnimatorView;

public class AnimatorControllerImpl implements AnimatorController {

  ROModel model;
  AnimatorView view;

  public AnimatorControllerImpl(ROModel model, AnimatorView view) {

    if (model == null || view == null) {
      throw new IllegalArgumentException("Don't be null.");
    }
    this.model = model;
    this.view = view;
  }

  @Override
  public void animate() throws IllegalArgumentException,
          IllegalStateException {

    while (view.getCommands().isEmpty()) {
      
    }
  }
}
