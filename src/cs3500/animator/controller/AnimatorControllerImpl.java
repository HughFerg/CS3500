package cs3500.animator.controller;

import java.util.ArrayList;

import cs3500.animator.model.Command;
import cs3500.animator.model.ROModel;
import cs3500.animator.view.AnimatorView;

public class AnimatorControllerImpl implements AnimatorController {

  ROModel model;
  AnimatorView view;
  private boolean playing;
  private boolean looping;

  public AnimatorControllerImpl(ROModel model, AnimatorView view) {

    if (model == null || view == null) {
      throw new IllegalArgumentException("Don't be null.");
    }
    this.model = model;
    this.view = view;
    this.playing = true;
    this.looping = true;
  }

  @Override
  public void animate() throws IllegalArgumentException,
          IllegalStateException {

    view.addListener(this);
    view.makeVisible();

    while(looping) {
      while (!view.endTick()) {

        model.onTick();
        view.refresh(playing);

        try {
          Thread.sleep( 1000L / view.getTps());
        } catch (InterruptedException e) {
          System.out.print("Thread.sleep is a hack.");
        }
      }
      restart();
    }
    System.exit(0);
  }

  @Override
  public void play() {
    playing = true;
  }

  @Override
  public void pause() {
    playing = false;
  }

  @Override
  public void restart() {
    view.setCommands(model.getCommands());
  }

  @Override
  public void loop() {
    looping = !looping;
  }
}
