package cs3500.animator.view;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Command;

// Represents a Visual view for the Animator, displaying images using a JFrame.
public class VisualView extends AbstractView {

  private JFrame frame;
  private Timer timer;

  /**
   * Creates a new Visual view based on the given arguments.
   * @param tps the ticks/second.
   * @param model the model to be associated with this view.
   * @param startX the starting x coordinate.
   * @param startY the starting y coordinate.
   * @param w the view's width.
   * @param h the view's height.
   */
  public VisualView(int tps, AnimatorModel model, int startX, int startY, int w, int h) {
    super(tps, model, startX, startY, w, h);

    setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));

    frame = new JFrame();

    frame.setSize(this.WIDTH, this.HEIGHT);
    frame.setLocation(this.startX, this.startY);
    frame.getContentPane().add(this);
    frame.pack();
  }

  /**
   * Creates a visual view with the given speed and model.
   * @param tps the ticks/second.
   * @param model the model to be associated with this view.
   */
  public VisualView(int tps, AnimatorModel model) {
    super(tps, model);

    setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));

    frame = new JFrame();

    frame.setSize(this.WIDTH, this.HEIGHT);
    frame.setLocation(this.startX, this.startY);
    frame.getContentPane().add(this);
    frame.pack();
  }

  @Override
  public void makeVisible() {

    frame.setVisible(true);
    setVisible(true);
    Timer t = new Timer("refresh time");

    while (!this.model.getCommands().isEmpty()) {

      t.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
          refresh();
        }
      }, 0, (long) (1000.0 / this.tps));

      //this.refresh();

      /*try {
        Thread.sleep((long) 1000.0 / this.tps);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }*/
    }
    setVisible(false);
    System.exit(0);
  }

  @Override
  public void refresh() {
    this.model.onTick();
    repaint();
  }

  @Override
  public String getOutput() {
      throw new UnsupportedOperationException("No String output for visual view.");
  }

  @Override
  public void writeToFile(String filename) {
    throw new UnsupportedOperationException("No file output for visual view.");
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    AffineTransform originalTransform = g2d.getTransform();
    g2d.translate(0, this.getPreferredSize().getHeight());
    g2d.scale(1, -1);

    for (Command c : this.model.getCommands()) {
      g2d.setTransform(originalTransform);
      c.getDrawing(g2d, this.model.getTick());
    }
  }
}
