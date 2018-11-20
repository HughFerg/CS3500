package cs3500.animator.view;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Command;

/**
 * Represents an abstract superclass for the 4* Animator views.
 */
public abstract class AbstractView extends JPanel implements AnimatorView {

  // Ticks per second.
  protected int tps = 1;
  protected ArrayList<Command> viewCommands;
  protected int startX = 200;
  protected int startY = 200;
  protected int width = 1000;
  protected int height = 1000;

  /**
   * Constructor for an AbstractView which initializes all basic values that the views share.
   *
   * @param tps     ticks per second
   * @param viewCommands   the list of commands that represents our animation
   * @param startX  x coordinate of the top left corner
   * @param startY  y coordinate of the top left corner
   * @param w       width of the canvas
   * @param h       height of the canvas
   */
  public AbstractView(int tps, ArrayList<Command> viewCommands, int startX, int startY, int w,
                      int h) throws IllegalArgumentException {
    super();
    if (tps > 0 && viewCommands != null && w >= 0 && h >= 0) {
      this.tps = tps;
      this.viewCommands = viewCommands;
      this.startX = startX;
      this.startY = startY;
      this.width = w;
      this.height = h;
    } else {
      throw new IllegalArgumentException("Model cannot be null.");
    }
  }

  /**
   * Constructor for Abstract view that only takes in a tick/second and a model.
   * @param tps the ticks/second.
   * @param viewCommands the specified list of commands in the animation.
   */
  public AbstractView(int tps, ArrayList<Command> viewCommands) throws IllegalArgumentException {
    super();

    if (tps > 0 && viewCommands != null) {
      this.tps = tps;
      this.viewCommands = viewCommands;
    } else {
      throw new IllegalArgumentException("Commands cannot be null, and tps must be above 0.");
    }
  }

  @Override
  public void writeToFile(String fileName) {

    if (fileName.equals("") || fileName.equals("out")) {
      this.makeVisible();
      return;
    }

    try {
      BufferedWriter w = new BufferedWriter(new FileWriter(fileName));
      w.write(this.getOutput());
      w.close();
    } catch (IOException e) {
      JOptionPane.showMessageDialog(new JPanel(), "Cannot write to output file", "File Write " +
              "Error", JOptionPane.WARNING_MESSAGE);
    }
  }

  @Override
  public void makePanel() {

    JFrame frame = new JFrame();

    this.setPreferredSize(new Dimension(this.width, this.height));

    frame.setPreferredSize(new Dimension(this.width, this.height));
    frame.setLocation(this.startX, this.startY);
    frame.getContentPane().add(this);
    frame.pack();
    frame.setVisible(true);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
  }
}
