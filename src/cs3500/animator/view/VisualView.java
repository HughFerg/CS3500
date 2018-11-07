package cs3500.animator.model.view;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import cs3500.animator.model.AnimatorModel;

// Represents a Visual view for the Animator, displaying images using a JFrame.
public class VisualView extends AbstractView implements AnimatorView {

  JPanel panel;

  public VisualView(int tps, AnimatorModel model, int startX, int startY, int w, int h) {
    super(tps, model, startX, startY, w, h);

    this.setSize(this.WIDTH, this.HEIGHT);
    this.setLocation(this.START_X, this.START_Y);

    this.panel = new JPanel(new BorderLayout());
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
    this.panel.add(g.drawRect(350, 350, 40, 40));
  }
}
