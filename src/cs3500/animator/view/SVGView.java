package cs3500.animator.view;

import java.util.ArrayList;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.model.Command;

public class SVGView extends AbstractView implements AnimatorView {

  public SVGView(int tps, AnimatorModel model, int startX, int startY, int w, int h) {
    super(tps, model, startX, startY, w, h);
  }

  @Override
  public void makeVisible() {

    String result = this.getOutput();

    System.out.print(result);
  }

  public String getOutput() {
    ArrayList<Command> commands = this.model.getCommands();

    String result = String.format("<svg width=\"%s\" height=\"%s\" version=\"1.1\" " +
            "xmlns=\"http://www.w3.org/2000/svg\">\n", WIDTH, HEIGHT);

    while(commands.size() > 0) {
      String currentShape = commands.get(0).getName();

      result += String.format(commands.get(0).generateSVGHeader(), currentShape);
    }

    return result;
  }

  @Override
  public void refresh() {
    /**
     * Left blank because SVG view does not print in real time.
     */
  }
}
