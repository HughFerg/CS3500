package cs3500.animator.model;

import java.util.ArrayList;

public final class ROModel implements AnimatorModel {
  private AnimatorModelImpl baseModel;

  public ROModel(AnimatorModelImpl baseModel) {
    this.baseModel = baseModel;
  }

  @Override
  public void onTick() {
    baseModel.onTick();
  }

  @Override
  public void addCommand(Command cmd) throws IllegalAccessException {
    throw new IllegalAccessException("You are not allowed to edit this.");
  }

  @Override
  public ArrayList<Command> getCommands() {
    return baseModel.getCommands();
  }

  @Override
  public int getTick() {
    return baseModel.getTick();
  }

  @Override
  public void reset() {
    baseModel.reset();
  }
}
