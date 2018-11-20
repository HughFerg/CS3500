package cs3500.animator.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
  private JButton editCommand;
  private JButton deleteCommand;
  private JButton exit;

  private JLabel currentSpeed;
  private JLabel paused;

  private JComboBox<String> shapeList;

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
    loopButton = new JButton("Loop On/Off");
    loopButton.addActionListener(this);
    loopButton.setForeground(Color.BLUE);
    loopButton.setOpaque(true);
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
    editCommand = new JButton("Edit");
    editCommand.addActionListener(this);
    deleteCommand = new JButton("Delete");
    deleteCommand.addActionListener(this);
    exit = new JButton("Exit");
    exit.addActionListener(this);
    exit.setForeground(Color.RED);
    exit.setOpaque(true);

    ArrayList<String> shapes = new ArrayList<>();

    for (Command c : viewCommands) {
      shapes.add(c.getName());
    }

    shapeList = new JComboBox(shapes.toArray());
    shapeList.addActionListener(this);

    currentSpeed = new JLabel(Integer.toString(this.tps));
    currentSpeed.setBackground(Color.WHITE);

    paused = new JLabel("Paused");
    paused.setVisible(false);

    add(playButton);
    add(pauseButton);
    add(restartButton);
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
  public void refresh(boolean playing) {
    visualView.refresh(playing);
  }

  @Override
  public void makePanel() {

    JPanel master = new JPanel();
    master.setLayout(new BorderLayout());
    master.add(this, BorderLayout.CENTER);

    JPanel pausePanel = new JPanel();
    pausePanel.add(paused);
    master.add(pausePanel, BorderLayout.WEST);

    JPanel addButtons = new JPanel();
    addButtons.add(addOval);
    addButtons.add(addTri);
    addButtons.add(addRect);
    addButtons.add(shapeList);
    addButtons.add(editCommand);
    addButtons.add(deleteCommand);
    addButtons.add(exit);

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
      paused.setVisible(false);
    }
    if (command.equals("Pause")) {
      pause();
      paused.setVisible(true);
    }
    if (command.equals("Restart")) {
      restart();
    }
    if (command.equals("Loop On/Off")) {
      if (loopButton.getForeground().equals(Color.BLUE)) {
        loopButton.setForeground(Color.red);
      } else {
        loopButton.setForeground(Color.BLUE);
      }
      loop();
    }
    if (command.equals("Speed +")) {
      setSpeed(1);
      currentSpeed.setText(Integer.toString(tps));
    }
    if (command.equals("Speed -")) {
      setSpeed(-1);
      currentSpeed.setText(Integer.toString(tps));
    }
    if (command.equals("Delete")) {
      deleteCommand(shapeList.getSelectedItem().toString());
    }
    if (command.equals("Edit")) {
      editCommand();
    }
    if (command.equals("Add Oval")) {

    }
    if (command.equals("Add Triangle")) {

    }
    if (command.equals("Add Rectangle")) {

    }
    if (command.equals("Exit")) {
      System.exit(0);
    }
  }

  private void showKeyframe(String shape) {

  }

  private void deleteCommand(String name) {
    visualView.deleteShape(name);
    controller.deleteShape(name);
  }

  private void editCommand() {

  }

  private void play() {
    controller.play();
  }

  private void pause() {
    controller.pause();
  }

  private void restart() {
    controller.restart();
  }

  @Override
  public void setCommands(ArrayList<Command> commands) {
    viewCommands = commands;
    visualView.setCommands(viewCommands);
  }

  private void loop() {
    controller.loop();
  }

  private void setSpeed(int diff) {
    tps += diff;
  }

  @Override
  public boolean endTick() {
    return visualView.endTick();
  }
}
