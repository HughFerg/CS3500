package cs3500.animator.view;

import cs3500.animator.model.AnimatorModel;

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
      return new TextView(model);
    } else if (type.equals("visual")) {
      return new VisualView(tps, model);
    } else if (type.equals("svg")) {
      return new SVGView(tps, model);
    } else {
      throw new IllegalArgumentException("Not an available view");
    }
  }
}
