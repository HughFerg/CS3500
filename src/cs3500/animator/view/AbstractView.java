package cs3500.animator.view;

import java.awt.*;

import javax.swing.JPanel;

import cs3500.animator.model.AnimatorModel;

// Represents an abstract superclass for the 3 Animator views.
public abstract class AbstractView extends JPanel {

  // Ticks per second.
  protected final int TPS;
  protected final AnimatorModel model;
  protected final int START_X;
  protected final int START_Y;
  protected final int WIDTH;
  protected final int HEIGHT;

  public AbstractView(int tps, AnimatorModel model, int startX, int startY, int w, int h) {
    super();
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

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
