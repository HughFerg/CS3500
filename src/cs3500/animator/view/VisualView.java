package cs3500.animator.view;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
   * @param tps          the ticks/second.
   * @param viewCommands the model to be associated with this view.
   */
  public VisualView(int tps, ArrayList<Command> viewCommands) {
    super(tps, viewCommands);
    this.viewCommands = viewCommands;
    this.tick = 0;
  }

  public VisualView(int tps, ArrayList<Command> viewCommands, int w, int h, int x, int y) {
    super(tps, viewCommands, x, y, w, h);
    this.tick = 0;
  }

  @Override
  public void makeVisible() {

//    this.timer = new Timer("refresh time");
      /*
      timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
          refresh();
        }
      }, 0, (long) (1000.0 / this.tps));
      */
}

  @Override
  public void refresh() {

    List toRemove = new ArrayList();

    for (Command cmd : viewCommands) {
      if (cmd.getEnd() <= this.tick) {
        toRemove.add(cmd);
      } else {
        if (cmd.getStart() <= this.tick) {
          cmd.update(this.tick);
        }
      }
    }
    for (Object past : toRemove) {
//      viewCommands.remove(past);
    }
    repaint();
    tick += 1;
  }

  @Override
  public boolean hasCommands() {
    return false;
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
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    AffineTransform originalTransform = g2d.getTransform();
    g2d.translate(0, this.getPreferredSize().getHeight());
    g2d.scale(1, -1);

    g2d.setTransform(originalTransform);

    for (Command c : viewCommands) {
      c.getDrawing(g2d, tick);
    }
  }
}
