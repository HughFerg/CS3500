package cs3500.animator.view;

import sun.tools.jps.Jps;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import cs3500.animator.model.Command;

/*
 * Represents an Editor view for the Animator, including the visual view with looping,
 * speeding/slowing, adding or removing animations etc.
 */
public class EditorView extends AbstractView implements ActionListener {

  private VisualView visualView;

  private JFrame frame;
  private JButton playButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JButton resumeButton;
  private JButton loopButton;
  private JButton incSpeed;
  private JButton decSpeed;
  private JButton addOval;
  private JButton addRect;
  private JButton addTri;

  private JLabel currentSpeed;

  private boolean playing = true;
  private boolean looping = false;

  public EditorView(int tps, ArrayList<Command> viewCommands) {
    super(tps, viewCommands);

    this.visualView = new VisualView(tps, viewCommands);
    initLayout();
  }

  public EditorView(int tps, ArrayList<Command> viewCommands, int w, int h, int x, int y) {
    super(tps, viewCommands, x, y, w, h);

    this.visualView = new VisualView(tps, viewCommands);
    initLayout();
  }

  private void initLayout() {

    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

    playButton = new JButton("Play");
    playButton.addActionListener(this);
    pauseButton = new JButton("Pause");
    pauseButton.addActionListener(this);
    restartButton = new JButton("Restart");
    restartButton.addActionListener(this);
    resumeButton = new JButton("Resume");
    resumeButton.addActionListener(this);
    loopButton = new JButton("Loop On/Off");
    loopButton.addActionListener(this);
    incSpeed = new JButton("Speed +");
    incSpeed.addActionListener(this);
    decSpeed = new JButton("Speed -");
    decSpeed.addActionListener(this);
    addOval = new JButton("Add Oval");
    addOval.addActionListener(this);
    addTri = new JButton("Add Triangle");
    addTri.addActionListener(this);
    addRect = new JButton("Add Rectangle");
    addRect.addActionListener(this);

    currentSpeed = new JLabel(Integer.toString(this.tps));
    currentSpeed.setBackground(Color.WHITE);

    add(playButton);
    add(pauseButton);
    add(restartButton);
    add(resumeButton);
    add(loopButton);
    add(incSpeed);
    add(decSpeed);
    add(currentSpeed);

  }

  @Override
  public String getOutput() {
    throw new UnsupportedOperationException("No output for editor view.");
  }

  @Override
  public void makeVisible() {
    makePanel();
  }

  @Override
  public void refresh() {
    visualView.refresh();
  }

  @Override
  public void makePanel() {

    JPanel master = new JPanel();
    master.setLayout(new BorderLayout());
    master.add(this, BorderLayout.CENTER);

    JPanel addButtons = new JPanel();
    addButtons.add(addOval);
    addButtons.add(addTri);
    addButtons.add(addRect);
    
    master.add(addButtons, BorderLayout.SOUTH);

    setVisible(true);
    visualView.setVisible(true);
    master.add(visualView, BorderLayout.NORTH);

    frame = new JFrame();
    frame.add(master);
    frame.setPreferredSize(new Dimension(visualView.width + 200, visualView.height + 80));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLocation(this.startX, this.startY);
    frame.pack();
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if (command.equals("Play")) {
      play();
    }
    if (command.equals("Pause")) {
      pause();
    }
    if (command.equals("Restart")) {
//      restart();
    }
    if (command.equals("Loop")) {
      loop();
    }
    if (command.equals("Speed +")) {
      setSpeed(1);
    }
    if (command.equals("Speed -")) {
      setSpeed(-1);
    }
  }

  private void play() {
    playing = true;
  }

  private void pause() {
    playing = false;
  }

  private void restart(ArrayList<Command> cache) {
    this.viewCommands = cache;
    visualView.tick = 0;
  }

  private void loop() {
    looping = !looping;
  }

  private void setSpeed(int diff) {

  }
}
