package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;
import cs3500.animator.provider.view.JFrameView;

// Represents a factory to build the specified view.
public class ViewFactory {

  /**
   * Factory for a specific View type. Constructs a view based on the given arguments.
   * @param type type of view to be created - Visual, text, or svg.
   * @param tps ticks/second of the view.
   * @param model the model to be associated with this view.
   * @return a new Animator view as created by the given args.
   */
  public AnimatorView getView(String type, int tps, AnimatorModel model) {
    if (type.equals("text")) {
      return new TextView(model.getCommands());
    } else if (type.equals("visual")) {
      return new VisualView(tps, model.getCommands(), model.getX(), model.getY(), model.getW(),
              model.getH());
    } else if (type.equals("svg")) {
      return new SVGView(tps, model.getCommands());
    } else if (type.equals("edit")) {
      return new EditorView(tps, model.getCommands(), model.getX(), model.getY(), model.getW(),
              model.getH());
    } else {
        throw new IllegalArgumentException("Not an available view");
      }
  }
}
