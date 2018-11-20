package cs3500.animator.controller;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cs3500.animator.model.Command;
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

    view.addListener(this);
    view.makeVisible();

    while (view.hasCommands()) {

          model.onTick();
          view.refresh();

          try {
            Thread.sleep( 1000L / view.getTps());
          } catch (InterruptedException e) {

          }
    }
    System.exit(0);
  }

  @Override
  public void restart() {
    view.setCommands(new ArrayList<Command>(model.getCommands()));
  }
}
