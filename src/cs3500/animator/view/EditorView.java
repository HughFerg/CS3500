package cs3500.animator.view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Command;

/*
 * Represents an Editor view for the Animator, including the visual view with looping,
 * speeding/slowing, adding or removing animations etc.
 */
public class EditorView extends AbstractView {

  private VisualView visualView;
  private JFrame frame;
  private JButton playButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JButton resumeButton;
  private JButton loopButton;
  private JButton incSpeed;
  private JButton decSpeed;
  private boolean playing;
  private boolean looping;


  public EditorView(int tps, ArrayList<Command> viewCommands) {
    super(tps, viewCommands);

    this.visualView = new VisualView(tps, viewCommands);

    this.playButton = new JButton("Gay");
    this.playing = true;
    this.looping = false;
  }

  @Override
  public String getOutput() {
    return null;
  }

  @Override
  public void makeVisible() {
    makePanel();
    setVisible(true);
  }

  @Override
  public void refresh() {

  }

  @Override
  public void makePanel() {

    JFrame frame = new JFrame();

    this.setPreferredSize(new Dimension(this.width, this.height));

    frame.setPreferredSize(new Dimension(this.width, this.height));
    frame.setLocation(this.startX, this.startY);

    add(playButton);

    JPanel superPanel = new JPanel();
    superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.Y_AXIS));

    JPanel vis = visualView;

    superPanel.add(vis);
    superPanel.add(this);

    frame.add(superPanel);
    frame.pack();

    frame.setVisible(true);
  }

  private void play() {
    this.playing = true;
  }

  private void pause() {
    this.playing = false;
  }

  private void restart(ArrayList<Command> cache) {
    this.viewCommands = cache;
    visualView.tick = 0;
  }

  private void loop() {
    looping = !looping;
  }
}
