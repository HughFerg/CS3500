package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Command;

/**
 * Animator view that generates SVG file formatting of an Animator
 */
public class SVGView extends AbstractView implements AnimatorView {

  /**
   * Constructs an SVGView
   *
   * @param tps     ticks per second
   * @param model   the model of our Animator
   * @param startX  top left x coordinate of the canvas
   * @param startY  top left y coordinate of the canvas
   * @param w       overall width of the canvas
   * @param h       overall height of the canvas
   */
  public SVGView(int tps, AnimatorModel model, int startX, int startY, int w, int h) {
    super(tps, model, startX, startY, w, h);
  }

  public SVGView(int tps, AnimatorModel model) {
    super(tps, model);
  }

  @Override
  public void makeVisible() {

    String result = this.getOutput();

    System.out.print(result);
  }

  /**
   * Generates a StringBuilder which represents the entire set of instructions converted to an SVG.
   *
   * @return StringBuilder of the entire SVG text properly formatted
   */
  public String getOutput() {
    ArrayList<Command> commands = this.model.getCommands();

    StringBuilder result = new StringBuilder(String.format("<svg width=\"%s\" height=\"%s\" " +
            "version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n", WIDTH, HEIGHT));

    while(commands.size() > 0) {
      Command currentShape = commands.get(0);
      ArrayList<Command> notUsed = new ArrayList<>();

      result.append(String.format(commands.get(0).generateSVGHeader(), currentShape.getName()));
      for(Command command : commands) {

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
  public void refresh() {
    /**
     * Left blank because SVG view does not print in real time.
     */
  }
}
