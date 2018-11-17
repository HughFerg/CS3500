package cs3500.animator.view;

import javax.swing.*;

import cs3500.animator.model.AnimatorModel;

/*
COMMENT
 */
public class EditorView extends AbstractView {

  private VisualView visualView;
  private JFrame frame;

  public EditorView(int tps, AnimatorModel model) {
    super(tps, model);
  }

  @Override
  public String getOutput() {
    return null;
  }

  @Override
  public void makeVisible() {

  }

  @Override
  public void refresh() {

  }
}
