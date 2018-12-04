package cs3500.animator.model;

public class CommandToAnimationAdapter implements Animation {

  Command base;

  public CommandToAnimationAdapter(Command base) {
    this.base = base;
  }

  @Override
  public void applyAnimation(ModelShape s, int t) {

  }

  @Override
  public boolean isContained(int t) {
    return (base.getStart() >= t || base.getEnd() <= t);
  }

  @Override
  public String getType() {
    return base.getCurrent().getShapeType();
  }

  @Override
  public Animation getCopy() {
    return new CommandToAnimationAdapter(this.base);
  }

  @Override
  public boolean linesUp(Animation that) {
    return true;
  }

  @Override
  public int getStart() {
    return this.base.getStart();
  }

  @Override
  public int getEnd() {
    return this.base.getEnd();
  }

  @Override
  public Attributes getStartShape() {
    return base.getCurrent();
  }

  @Override
  public Attributes getEndShape() {
    return base.getDest();
  }

  @Override
  public void setStart(int start) {
    this.base = new Command(base.getName(), start, base.getEnd(), base.getCurrent(),
            base.getDest());
  }

  @Override
  public void setEnd(int end) {
    this.base = new Command(base.getName(), base.getStart(), end, base.getCurrent(),
            base.getDest());
  }
}
