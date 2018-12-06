package cs3500.animator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Command;

/*
 * Represents an Editor view for the Animator, including the visual view with looping,
 * speeding/slowing, adding or removing animations etc.
 */
public class EditorView extends AbstractView implements ActionListener {

  private VisualView visualView;

  private JButton addOval;
  private JButton addRect;
  private JButton addTri;
  private JButton editCommand;
  private JButton deleteCommand;
  JButton loopButton;
  private JButton exit;

  private JLabel name = new JLabel("Name");
  private JLabel time = new JLabel("Time");
  private JLabel width = new JLabel("Width");
  private JLabel height = new JLabel("Height");
  private JLabel x = new JLabel("X");
  private JLabel y = new JLabel("Y");
  private JLabel red = new JLabel("Red");
  private JLabel green = new JLabel("Green");
  private JLabel blue = new JLabel("Blue");

  private JLabel currentSpeed;
  private JLabel paused;

  private JTextField nameField = new JTextField();
  private JTextField timeField = new JTextField();
  private JTextField widthField = new JTextField();
  private JTextField heightField = new JTextField();
  private JTextField xField = new JTextField();
  private JTextField yField = new JTextField();
  private JTextField redField = new JTextField();
  private JTextField greenField = new JTextField();
  private JTextField blueField = new JTextField();

  JPanel keyframePanel = new JPanel();

  private JComboBox<String> shapeList;
  private JComboBox<String> keyframes;

  /**
   * Creates a new editor view w the given commands.
   *
   * @param tps          ticks/second.
   * @param viewCommands list of commands to render.
   */
  public EditorView(int tps, ArrayList<Command> viewCommands) {
    super(tps, viewCommands);

    this.visualView = new VisualView(tps, viewCommands);
    initLayout();
  }

  /**
   * Creates an editor view with the given params.
   *
   * @param tps          ticks/second
   * @param viewCommands the list of commands to display.
   * @param w            width.
   * @param h            height.
   * @param x            x location.
   * @param y            y location.
   */
  public EditorView(int tps, ArrayList<Command> viewCommands, int w, int h, int x, int y) {
    super(tps, viewCommands, x, y, w, h);

    this.visualView = new VisualView(tps, viewCommands);
    initLayout();
  }

  /**
   * Initializes the layout of the Panels/frame.
   */
  private void initLayout() {

    JButton playButton;
    JButton pauseButton;
    JButton restartButton;
    JButton incSpeed;
    JButton decSpeed;

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
    shapes.add("-");
    ArrayList<String> kfStrings = new ArrayList<>();
    kfStrings.add("-");

    for (Command c : viewCommands) {
      shapes.add(c.getName());
      kfStrings.add(c.getName() + " " + c.getStart());
    }

    shapes.add(viewCommands.get(viewCommands.size() - 1).getName() + " "
            + viewCommands.get(viewCommands.size() - 1).getEnd());

    shapeList = new JComboBox(shapes.toArray());
    shapeList.addActionListener(this);

    keyframes = new JComboBox(kfStrings.toArray());
    keyframes.addActionListener(this);

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
  public void refresh(boolean playing, ArrayList<Command> commands) {
    visualView.refresh(playing, commands);
  }

  @Override
  public void reset() {
    visualView.reset();
  }

  @Override
  public void makePanel() {

    JFrame frame;

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
    addButtons.add(keyframes);
    addButtons.add(exit);

    master.add(addButtons, BorderLayout.SOUTH);

    setVisible(true);
    visualView.setVisible(true);
    master.add(visualView, BorderLayout.NORTH);

    frame = new JFrame();
    frame.add(master);
    frame.setPreferredSize(new Dimension(visualView.width + 250,
            visualView.height + 100));
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setLocation(this.startX, this.startY);
    frame.pack();
    frame.setVisible(true);

    keyframePanel.setLayout(new GridLayout(0, 1));
    keyframePanel.add(name);
    keyframePanel.add(nameField);
    keyframePanel.add(time);
    keyframePanel.add(timeField);
    keyframePanel.add(x);
    keyframePanel.add(xField);
    keyframePanel.add(y);
    keyframePanel.add(yField);
    keyframePanel.add(width);
    keyframePanel.add(widthField);
    keyframePanel.add(height);
    keyframePanel.add(heightField);
    keyframePanel.add(red);
    keyframePanel.add(redField);
    keyframePanel.add(green);
    keyframePanel.add(greenField);
    keyframePanel.add(blue);
    keyframePanel.add(blueField);
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
      if (!keyframes.getSelectedItem().toString().equals("-")) {
        deleteCommand(keyframes.getSelectedItem().toString());
      }
      if (!shapeList.getSelectedItem().toString().equals("-")) {
        deleteShape(shapeList.getSelectedItem().toString());
      }
    }
    if (command.equals("Edit")) {
      editCommand(keyframes.getSelectedItem().toString());
    }
    if (command.equals("Add Oval")) {
      createOvalKF();
    }
    if (command.equals("Add Triangle")) {
      createTriKF();
    }
    if (command.equals("Add Rectangle")) {
      createRectKF();
    }
    if (command.equals("Exit")) {
      System.exit(0);
    }
  }

  /**
   * Creates an oval shape.
   */
  private void createOvalKF() {

    int result = JOptionPane.showConfirmDialog(null, keyframePanel, "Test",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      addKeyframe("oval", nameField.getText(), timeField.getText(), xField.getText(),
              yField.getText(),
              widthField.getText(), heightField.getText(), redField.getText(),
              greenField.getText(), blueField.getText());
    }
  }

  /**
   * Creates a triangle shape.
   */
  private void createTriKF() {

    int result = JOptionPane.showConfirmDialog(null, keyframePanel, "Test",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      addKeyframe("tri", nameField.getText(), timeField.getText(), xField.getText(),
              yField.getText(),
              widthField.getText(), heightField.getText(), redField.getText(),
              greenField.getText(), blueField.getText());
    }
  }

  /**
   * Creates a rectangle shape.
   */
  private void createRectKF() {

    int result = JOptionPane.showConfirmDialog(null, keyframePanel, "Test",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      addKeyframe("rect", nameField.getText(), timeField.getText(), xField.getText(),
              yField.getText(),
              widthField.getText(), heightField.getText(), redField.getText(),
              greenField.getText(), blueField.getText());
    }
  }

  /**
   * Deletes the given shape from the animation.
   *
   * @param name the name of the shape to be deleted.
   */
  private void deleteShape(String name) {
    visualView.deleteShape(name);
    controller.deleteShape(name);
  }

  /**
   * Deletes the given command.
   *
   * @param name the name of the command to be deleted.
   */
  private void deleteCommand(String name) {
    controller.deleteCommand(name);
  }

  /**
   * Allows the user to edit the given command.
   *
   * @param cmd the command (keyframe) to be edited.
   */
  private void editCommand(String cmd) {

    String[] data = cmd.split(" ");

    for (Command c : viewCommands) {
      if (c.getName().equals(data[0]) && c.getStart() == Integer.parseInt(data[1])) {
        nameField.setText(c.getName());
        timeField.setText(Integer.toString(c.getStart()));
        xField.setText(Integer.toString(c.getX()));
        yField.setText(Integer.toString(c.getY()));
        widthField.setText(Integer.toString(c.getWidth()));
        heightField.setText(Integer.toString(c.getHeight()));
        redField.setText(Integer.toString(c.getRed()));
        greenField.setText(Integer.toString(c.getGreen()));
        blueField.setText(Integer.toString(c.getBlue()));
      }
    }

    int result = JOptionPane.showConfirmDialog(null, keyframePanel, "Test",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      addKeyframe("", nameField.getText(), timeField.getText(), xField.getText(), yField.getText(),
              widthField.getText(), heightField.getText(), redField.getText(),
              greenField.getText(), blueField.getText());
    }
  }

  /**
   * Adds a key frame to this animation.
   *
   * @param shapename the name of the shape type to be made.
   * @param name      the name of the animation.
   * @param time      the time of the keyframe.
   * @param x         x coord of KF.
   * @param y         y coord of KF.
   * @param w         witdh of KF.
   * @param h         height of KF.
   * @param r         red value of KF.
   * @param g         green value of KF.
   * @param b         blue value of KF.
   */
  private void addKeyframe(String shapename, String name, String time, String x, String y, String w,
                           String h,
                           String r, String g, String b) {

    controller.addKeyFrame(shapename, name, Integer.parseInt(time), Integer.parseInt(x),
            Integer.parseInt(y), Integer.parseInt(w), Integer.parseInt(h),
            Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b));

    ArrayList<String> kfStrings = new ArrayList<>();
    for (Command c : viewCommands) {
      kfStrings.add(c.getName() + " " + c.getStart());
    }
    keyframes = new JComboBox(kfStrings.toArray());
  }

  /*
  Plays the current animation.
   */
  private void play() {
    controller.play();
  }

  /*
  Pauses the current running animation.
   */
  private void pause() {
    controller.pause();
  }

  /*
  Restarts the current animation.
   */
  private void restart() {
    controller.restart();
  }

  @Override
  public void setCommands(ArrayList<Command> commands) {
    viewCommands = commands;
    visualView.setCommands(commands);
  }

  /*
  Loops the current animation.
   */
  private void loop() {
    controller.loop();
  }

  /**
   * Changes the current speed by the given difference.
   *
   * @param diff the speed by which to change the tps by.
   */
  private void setSpeed(int diff) {
    controller.changeSpeed(diff);
    tps = tps + diff;
  }


  public ArrayList<Command> getCommands() {
  return visualView.viewCommands;
  }

  @Override
  public boolean endTick() {
    return visualView.endTick();
  }

  @Override
  public int getTick() {
    return visualView.getTick();
  }
}
