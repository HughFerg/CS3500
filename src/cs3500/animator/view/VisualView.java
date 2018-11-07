package cs3500.animator.view;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import cs3500.animator.model.AnimatorModel;

// Represents a Visual view for the Animator, displaying images using a JFrame.
public class VisualView extends AbstractView implements AnimatorView {

  JFrame frame;

  public VisualView(int tps, AnimatorModel model, int startX, int startY, int w, int h) {
    super(tps, model, startX, startY, w, h);

    frame.setSize(this.WIDTH, this.HEIGHT);
    frame.setLocation(this.START_X, this.START_Y);

    this.frame = new JFrame();
    this.frame.getContentPane().add(this);

  }

  @Override
  public void makeVisible() {

    this.setVisible(true);

    while (!this.model.getCommands().isEmpty()) {
      try {
        TimeUnit.SECONDS.sleep(1 / this.TPS);
      } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
      }
      this.refresh();
    }
    setVisible(false);
  }

  @Override
  public void refresh() {
    
    this.model.onTick();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    g = (Graphics2D)g;
    add(g.drawRect(350, 350, 40, 40)));
  }
}
