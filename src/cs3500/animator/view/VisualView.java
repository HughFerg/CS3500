package cs3500.animator.view;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Command;

// Represents a Visual view for the Animator, displaying images using a JFrame.
public class VisualView extends AbstractView {

  private ArrayList<Command> viewCommands;
  protected int tick;
  private Timer timer;

  /**
   * Creates a visual view with the given speed and model.
   *
   * @param tps   the ticks/second.
   * @param viewCommands the model to be associated with this view.
   */
  public VisualView(int tps, ArrayList<Command> viewCommands) {
    super(tps, viewCommands);
    this.tick = 0;
  }

  @Override
  public void makeVisible() {

    makePanel();

    setVisible(true);
    Timer t = new Timer("refresh time");

    while (!this.viewCommands.isEmpty()) {


      t.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
          refresh();
        }
      }, 0, (long) (1000.0 / this.tps));


//      refresh();
//
//      try {
//        Thread.sleep((long) 1000.0 / this.tps);
//      } catch (InterruptedException e) {
//        Thread.currentThread().interrupt();
//      }
    }
//    setVisible(false);
//    System.exit(0);
  }

  @Override
  public void refresh() {
    //this.model.onTick();
    repaint();
    tick += 1;
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

    g2d.setTransform(originalTransform);

    for (Command c : this.viewCommands) {
      c.getDrawing(g2d, this.tick);
    }
  }
}
