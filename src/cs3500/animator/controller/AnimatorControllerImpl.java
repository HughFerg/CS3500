package cs3500.animator.controller;

import cs3500.animator.model.ROModel;
import cs3500.animator.view.AnimatorView;

public class AnimatorControllerImpl implements AnimatorController {

  ROModel model;
  AnimatorView view;
  private boolean playing;
  private boolean looping;

  /**
   * Constructs a controller with the given model and view.
   * @param model the animator model to be controlled.
   * @param view the view to be controlled.
   */
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
  public void animate() {

    view.addListener(this);
    view.makeVisible();

    do {
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
    while (looping);
    System.exit(0);
  }

  @Override
  public void addKeyFrame(String shapename, String name, int time, int x, int y, int w,
                          int h,
                          int r, int g, int b) {
    model.addKeyFrame(shapename, name, time, x, y, w, h, r, g, b);
    view.setCommands(model.getCommands());
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

  @Override
  public void deleteShape(String name) {
    model.deleteShape(name);
  }

  @Override
  public void deleteCommand(String name) {
    model.deleteCommand(name);
  }
}
