package cs3500.animator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import cs3500.animator.model.ROModel;
import cs3500.animator.view.AnimatorView;

public class AnimatorControllerImpl implements AnimatorController {

  private ROModel model;
  private AnimatorView view;
  private boolean playing;
  private boolean looping;
  private Timer timer;

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
    this.timer = new Timer(1000 / view.getTps(), null);
  }

  /**
   * Timer event class used for refreshing the model and view.
   */
  private class PaintRefresh implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

      if (view.endTick() && looping) {
        restart();
      } else if (playing) {
        model.tick(view.getTick());
        view.refresh(playing, model.getCommands());
      }
    }
  }

  @Override
  public void animate() {

    view.addListener(this);
    view.makeVisible();
    timer.addActionListener(new PaintRefresh());
    timer.start();
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
    timer.start();
    playing = true;
  }

  @Override
  public void pause() {
    timer.stop();
    playing = false;
  }

  @Override
  public void restart() {
    model.reset();
    view.reset();
    view.refresh(playing, model.getCommands());
  }

  @Override
  public void changeSpeed(int delta) {
    this.timer.setDelay(1000 / view.getTps() + delta);
  }

  @Override
  public boolean endTick(int t) {
    return model.isOver(t);
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
