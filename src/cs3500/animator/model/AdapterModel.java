package cs3500.animator.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an adapter for our model and the provider's.
 */
public class AdapterModel implements AnimatorModel2 {

  private AnimatorModel base;

  public AdapterModel(AnimatorModel base) {
    this.base = base;
  }

  @Override
  public void tick(int time) {
    base.tick(time);
  }

  @Override
  public List<ModelShape> getShapeStates() {
    //need some class for what a ModelShape is
    return null;
  }

  @Override
  public void addShape(ModelShape shape) {

  }

  @Override
  public void removeShape(String shapeName) {
    base.deleteShape(shapeName);
  }

  @Override
  public List<Animation> getAnimationsOfShapeToEdit(String shapeName) {
    return null;
  }

  @Override
  public void setAnimation(String shapeName, List<Animation> animations) throws IllegalStateException {

  }

  @Override
  public void setBounds(int x, int y, int width, int height) throws IllegalArgumentException {
    base.setBounds(x, y, width, height);
  }

  @Override
  public List<String> getListOfShapeNames() {
    return base.getShapeNames();
  }

  @Override
  public int getCanvasX() {
    return base.getCanvasX();
  }

  @Override
  public int getCanvasY() {
    return base.getCanvasY();
  }

  @Override
  public int getCanvasWidth() {
    return base.getCanvasWidth();
  }

  @Override
  public int getCanvasHeight() {
    return base.getCanvasHeight();
  }

  @Override
  public ModelShape getCopy(String name) {
    return null;
  }

  @Override
  public void addSingleAnimation(String name, Animation animation) {

  }

  @Override
  public boolean isOver(int time) {
    return base.isOver(time);
  }

  @Override
  public List<String> getKeyFrames(String name) {

    List<String> names = new ArrayList<>();

    for (Command c : base.getCommands()) {
      names.add(c.getName());
    }

    return names;
  }

  @Override
  public void addKeyFrame(String name, int time, int height, int width, Color color, int x, int y) {

  }

  @Override
  public void removeKeyFrame(String name, int index) {

  }
}
