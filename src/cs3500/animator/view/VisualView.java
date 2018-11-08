package cs3500.animator.view;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Command;

// Represents a Visual view for the Animator, displaying images using a JFrame.
public class VisualView extends AbstractView implements AnimatorView {

  JFrame frame;

  public VisualView(int tps, AnimatorModel model, int startX, int startY, int w, int h) {
    super(tps, model, startX, startY, w, h);

    setPreferredSize(new Dimension(this.WIDTH, this.HEIGHT));

    frame = new JFrame();

    frame.setSize(this.WIDTH, this.HEIGHT);
    frame.setLocation(this.START_X, this.START_Y);
    frame.getContentPane().add(this);
    frame.pack();
  }

  @Override
  public void makeVisible() {

    frame.setVisible(true);
    this.setVisible(true);

    while (!this.model.getCommands().isEmpty()) {
      this.refresh();

      try {
        Thread.sleep((long) 1000.0 / this.TPS);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    setVisible(false);
  }

  @Override
  public void refresh() {
    this.model.onTick();
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    AffineTransform originalTransform = g2d.getTransform();
    g2d.translate(0, this.getPreferredSize().getHeight());
    g2d.scale(1, -1);

    for (Command c : this.model.getCommands()) {
      c.getDrawing(g2d, this.model.getTick());
    }

    g2d.setTransform(originalTransform);
  }
}
