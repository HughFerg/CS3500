package cs3500.animator.model;

import java.awt.Graphics2D;
import java.util.List;

/**
 * Represents the public facing functionality of a command object.
 */
interface CommandInterface {

  /**
   * Returns the start time of this command.
   *
   * @return this command's start time.
   */
  int getStart();

  /**
   * Returns the starting x value.
   *
   * @return starting x value.
   */
  int getX();

  /**
   * Returns the starting y value.
   *
   * @return starting y value.
   */
  int getY();

  /**
   * Returns the starting width.
   *
   * @return starting width.
   */
  int getWidth();

  /**
   * Returns the starting height.
   *
   * @return starting height.
   */
  int getHeight();

  /**
   * Returns the starting red value.
   *
   * @return starting red value.
   */
  int getRed();

  /**
   * Returns the starting green value.
   *
   * @return starting green value.
   */
  int getGreen();

  /**
   * Returns the starting blue value.
   *
   * @return starting blue value.
   */
  int getBlue();

  /**
   * Returns the end time of this command.
   *
   * @return the end time.
   */
  int getEnd();

  /**
   * Replaces the destination shape of this command.
   *
   * @param x x val of this shape.
   * @param y y val of shape.
   * @param w width of shape.
   * @param h height.
   * @param r red.
   * @param g green.
   * @param b blue.
   * @return the new destination shape.
   */
  IShape replaceDest(int x, int y, int w, int h, int r, int g,
                     int b);

  /**
   * Replaces the destination shape of this command.
   *
   * @param x x val of this shape.
   * @param y y val of shape.
   * @param w width of shape.
   * @param h height.
   * @param r red.
   * @param g green.
   * @param b blue.
   * @return the new destination shape.
   */
  IShape replaceCurrent(int x, int y, int w, int h, int r, int g, int b);

  /**
   * Gets the current destination shape.
   *
   * @return destination shape.
   */
  IShape getDest();

  /**
   * Copies this command.
   *
   * @return a copy of this command.
   */
  Command clone();

  /**
   * Gets the name of this command/keyframe.
   *
   * @return this name.
   */
  String getName();

  /**
   * Gets the current shape of this command.
   *
   * @return current shape.
   */
  IShape getCurrent();

  /**
   * Updates the current shape's value by the correct amounts per each field.
   *
   * @param currentTick the current tick at time of call.
   */
  void update(int currentTick);

  /**
   * Draws the graphic onto the canvas.
   *
   * @param g           the canvas of the animator.
   * @param currentTick the tick representing the current time.
   */
  void getDrawing(Graphics2D g, int currentTick);

  /**
   * Dispatches the generation of SVG headers to AbstractShape.
   *
   * @return String representation of a SVG shape header.
   */
  String generateSVGHeader();

  /**
   * Generates the closing tag for an SVG header by dispatching to AbstractShape.
   *
   * @return String representation of a SVG closing tag.
   */
  String generateEndTag();

  StringBuilder generateAnimationTag();


  /**
   * Dispatches the creation of animation tags for each of the possible transformations a shape can
   * have.
   *
   * @return StringBuilder representing all of the animations over this Command.
   */
  //StringBuilder generateAnimationTag();

}
