package cs3500.animator.model;

import java.util.ArrayList;
import java.util.List;

public final class ROModel implements AnimatorModel {
  private AnimatorModel baseModel;

  public ROModel(AnimatorModel baseModel) {
    this.baseModel = baseModel;
  }

  @Override
  public void tick(int tick) {
    baseModel.tick(tick);
  }

  @Override
  public List<String> getShapeNames() {
    return baseModel.getShapeNames();
  }

  @Override
  public ArrayList<Command> getCommands() {
    return baseModel.getCommands();
  }

  @Override
  public void setBounds(int x, int y, int w, int h) throws IllegalArgumentException {
    baseModel.setBounds(x, y, w, h);
  }

  @Override
  public boolean isOver(int time) {
    return baseModel.isOver(time);
  }

  @Override
  public void reset() {
    baseModel.reset();
  }

  @Override
  public int getCanvasX() {
    return baseModel.getCanvasX();
  }

  @Override
  public int getCanvasY() {
    return baseModel.getCanvasY();
  }

  @Override
  public int getCanvasWidth() {
    return baseModel.getCanvasWidth();
  }

  @Override
  public int getCanvasHeight() {
    return baseModel.getCanvasHeight();
  }

  @Override
  public void deleteShape(String name) {
    baseModel.deleteShape(name);
  }

  @Override
  public void deleteCommand(String name) {
    baseModel.deleteCommand(name);
  }

  public void addKeyFrame(String shapename, String name, int time, int x, int y, int w,
                          int h,
                          int r, int g, int b) {
    baseModel.addKeyFrame(shapename, name, time, x, y, w, h, r, g, b);
  }

  @Override
  public int getTick() {
    return baseModel.getTick();
  }
}
