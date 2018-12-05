package cs3500.animator.view;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import cs3500.animator.model.Command;

// Represents a Visual view for the Animator, displaying images using a JFrame.
public class VisualView extends AbstractView {

  private ArrayList<Command> viewCommands;
  private int tick;

  /**
   * Creates a visual view with the given speed and model.
   *
   * @param tps          the ticks/second.
   * @param viewCommands the commands to be associated with this view.
   */
  public VisualView(int tps, ArrayList<Command> viewCommands) {
    super(tps, viewCommands);
    this.viewCommands = viewCommands;
    this.tick = 0;
  }

  /**
   * Creates a visual view w given speed commands and dimensions.
   * @param tps ticks/sec
   * @param viewCommands commands to be rendered.
   * @param w witdh.
   * @param h height.
   * @param x x coord.
   * @param y y coord.
   */
  public VisualView(int tps, ArrayList<Command> viewCommands, int w, int h, int x, int y) {
    super(tps, viewCommands, x, y, w, h);
    this.tick = 0;
  }

  @Override
  public void makeVisible() {
    makePanel();
    setVisible(true);
  }

  @Override
  public void refresh(boolean playing) {

    if (playing) {
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
      repaint();
      tick += 1;
    }
  }

  @Override
  public void setCommands(ArrayList<Command> commands) {
    tick = 0;
    viewCommands = commands;
  }

  @Override
  public int getTick() {
    return tick;
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

  @Override
  public boolean endTick() {
    return tick > viewCommands.get(viewCommands.size() - 1).getEnd();
  }

  /**
   * Deletes the given shape from this list of commands.
   * @param name the name of the shape to be deleted.
   */
  public void deleteShape(String name) {
    ArrayList<Command> toRemove = new ArrayList<>();
    for (Command c : viewCommands) {
      if (c.getName().equals(name)) {
        toRemove.add(c);
      }
    }
    for (Command aboutToRemove : toRemove) {
      viewCommands.remove(aboutToRemove);
    }
  }
}
