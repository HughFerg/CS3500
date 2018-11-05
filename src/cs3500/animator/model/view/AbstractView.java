package cs3500.animator.model.view;

import cs3500.animator.model.AnimatorModel;

public abstract class AbstractView {

  // Ticks per second.
  protected final int TPS;
  protected final AnimatorModel model;
  protected final int START_X;
  protected final int START_Y;
  protected final int WIDTH;
  protected final int HEIGHT;

  public AbstractView(int tps, AnimatorModel model, int startX, int startY, int w, int h) {
    if (tps > 0 && model != null && w > 0 && h > 0) {
      this.TPS = tps;
      this.model = model;
      this.START_X = startX;
      this.START_Y = startY;
      this.WIDTH = w;
      this.HEIGHT = h;
    } else {
      throw new IllegalArgumentException("Model cannot be null.");
    }
  }
}
