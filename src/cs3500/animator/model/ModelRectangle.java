package cs3500.animator.model;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class ModelRectangle extends AbstractShape implements ModelShape {

  private IShape base;
  private String name;
  private boolean visible = false;
  private List<Animation> animations;

  public ModelRectangle(String name, Rectangle base) {
    this.name = name;
    this.base = base;
    this.animations = new ArrayList<Animation>();
  }

  @Override
  public ModelShape toModelShape(String name) {
    return this;
  }

  @Override
  public IShape getNextShape(IShape destination, int deltaT) {
    return base.getNextShape(destination, deltaT);
  }

  @Override
  public void getDrawing(Graphics2D g) {
    base.getDrawing(g);
  }

  @Override
  public String getShapeType() {
    return "rectangle";
  }

  @Override
  public String generateSVGHeader(String name) {
    return base.generateSVGHeader(name);
  }

  @Override
  public String generateEndTag() {
    return base.generateEndTag();
  }

  @Override
  public StringBuilder generatePositionTag(int start, int end, IShape source) {
    return base.generatePositionTag(start, end, source);
  }

  @Override
  public StringBuilder generateDimensionTag(int start, int end, IShape source) {
    return base.generateDimensionTag(start, end, source);
  }

  @Override
  public ModelShape copy() {
    return this;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean isVisible() {
    return visible;
  }

  @Override
  public void show() {
    visible = true;
  }

  @Override
  public void hide() {
    visible = false;
  }

  @Override
  public void setAttributes(Attributes a) {

  }

  @Override
  public void goTo(int time) {

  }

  @Override
  public Attributes getAttributes() {
    return base;
  }

  @Override
  public String getType() {
    return base.getShapeType();
  }

  @Override
  public List<Animation> getCopyOfAnimations() {
    List<Animation> copy = new ArrayList();

    for (Animation a : animations) {
      copy.add(a);
    }
    return copy;
  }

  @Override
  public void setAnimations(List<Animation> animations) {
    this.animations = animations;
  }

  @Override
  public void addKeyFrame(int time, Attributes attributes) {
    //do nothing
  }

  @Override
  public void removeKeyFrame(int index) {
    //do nothing
  }
}
