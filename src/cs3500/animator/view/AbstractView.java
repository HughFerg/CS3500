package cs3500.animator.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import cs3500.animator.model.AnimatorModel;

/**
 * Represents an abstract superclass for the 3 Animator views.
 */
public abstract class AbstractView extends JPanel {

  // Ticks per second.
  protected int TPS = 1;
  protected final AnimatorModel model;
  protected final int START_X;
  protected final int START_Y;
  protected final int WIDTH;
  protected final int HEIGHT;

  /**
   * Constructor for an AbstractView which initializes all basic values that the views share.
   *
   * @param tps     ticks per second
   * @param model   the model that represents our data
   * @param startX  x coordinate of the top left corner
   * @param startY  y coordinate of the top left corner
   * @param w       width of the canvas
   * @param h       height of the canvas
   */
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
