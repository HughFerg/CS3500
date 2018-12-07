package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.model.Command;

/**
 * Animator view that generates SVG file formatting of an Animator.
 */
public class SVGView extends AbstractView implements AnimatorView {

  /**
   * Constructs an SVGView with the given parameters.
   *
   * @param tps          ticks per second.
   * @param viewCommands the commands of our Animator.
   * @param startX       top left x coordinate of the canvas.
   * @param startY       top left y coordinate of the canvas.
   * @param w            overall width of the canvas.
   * @param h            overall height of the canvas.
   */
  public SVGView(int tps, ArrayList<Command> viewCommands, int startX, int startY, int w, int h) {
    super(tps, viewCommands, startX, startY, w, h);
  }

  /**
   * Constructs an SVGView with the given model and tps.
   *
   * @param tps          ticks per second.
   * @param viewCommands the commands of our Animator.
   */
  public SVGView(int tps, ArrayList<Command> viewCommands) {
    super(tps, viewCommands);
  }

  @Override
  public void makeVisible() {
    System.out.print(this.getOutput());
  }

  /**
   * Generates a StringBuilder which represents the entire set of instructions converted to an SVG.
   *
   * @return StringBuilder of the entire SVG text properly formatted.
   */
  public String getOutput() {
    ArrayList<Command> commands = this.viewCommands;

    StringBuilder result = new StringBuilder(String.format("<svg width=\"%s\" height=\"%s\" " +
            "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n", WIDTH, HEIGHT));

    while (commands.size() > 0) {
      Command currentShape = commands.get(0);
      ArrayList<Command> notUsed = new ArrayList<>();

      result.append(String.format(commands.get(0).generateSVGHeader(), currentShape.getName()));
      for (Command command : commands) {

        if (command.getName().equals(currentShape.getName())) {
          result.append(command.generateAnimationTag());
        } else {
          notUsed.add(command);
        }
      }
      commands = notUsed;
      result.append(currentShape.generateEndTag());
    }
    result.append("</svg>");
    return result.toString();
  }

  @Override
  public void makePanel() {
    throw new UnsupportedOperationException("SVG view does not have a panel.");
  }

  @Override
  public int getTick() {
    return 0;
  }

  @Override
  public boolean endTick() {
    controller.loop();
    return true;
  }

  @Override
  public void refresh(boolean playing, ArrayList<Command> commands) {
    throw new UnsupportedOperationException("SVG does not print in real time.");
  }
}
