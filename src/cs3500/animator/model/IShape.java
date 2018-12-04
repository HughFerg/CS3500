package cs3500.animator.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Represents functionality of a shape in the Animator model.
 */
public interface IShape extends Attributes{

  /**
   * Getter method for retrieving a shape's coordinate without allowing for mutation.
   *
   * @return The Coordinate of a shape
   */
  Point getCoordinates();

  /**
   * Getter method for retrieving a shape's Color without allowing for mutation.
   *
   * @return The Color of a shape
   */
  Color getColor();

  /**
   * Getter method for retrieving a shape's width without allowing for mutation.
   *
   * @return The width of a shape
   */
  int getWidth();

  /**
   * Getter method for retrieving a shape's height without allowing for mutation.
   *
   * @return The height of a shape
   */
  int getHeight();

  /**
   * Replaces this shape's abstract qualities with the given ones.
   *
   * @param x x location.
   * @param y y location.
   * @param w width.
   * @param h height.
   * @param r red val.
   * @param g green val.
   * @param b blue val.
   */
  void replace(int x, int y, int w, int h, int r, int g,
               int b);

  /**
   * Returns the next shape to render based on the current command's destination shape and the
   * amount they should transform to the next shape (deltaT).
   *
   * @param destination the destination shape.
   * @param deltaT      the amount to transform the shape's fields.
   * @return the shape to be rendered on the next tick.
   */
  IShape getNextShape(IShape destination, int deltaT);

  /**
   * Returns the shape representation of this shape for rendering in the view.
   *
   * @return this shape in Java.Awt shape format.
   */
  void getDrawing(Graphics2D g);

  /**
   * Returns the type of shape that this is.
   *
   * @return the shape type.
   */
  String getShapeType();

  /**
   * Abstract method for dispatching to each shape to render their own headings.
   *
   * @param name The name of the shape that a header is being generated for
   * @return The String representing the header for an SVG file
   */
  String generateSVGHeader(String name);

  /**
   * Abstract method for dispatching to each shape to render their own end tag.
   *
   * @return A String representing the closing tag for a SVG header
   */
  String generateEndTag();

  /**
   * Gets the next color for this shape based on the destination shape and the given delta T.
   *
   * @param destination the shape to eventually be transformed into.
   * @param deltaT      the current tick - the transformation end time.
   * @return The color of the shape after a time of deltaT.
   */
  Color getNextColor(IShape destination, int deltaT);

  /**
   * Gets the next point for his shape given the target shape and the deltaT.
   * @param destination the destination shape to eventually transform into.
   * @param deltaT the current tick - transformation end time
   * @return  The point of a shape after a time of deltaT.
   */
  Point getNextPoint(IShape destination, int deltaT);

  /**
   * Abstract method for dispatching to each shape to render their own animation tag for position.
   *
   * @param start  the starting tick of the animation
   * @param end    the ending tick of the animation
   * @param source the starting state of the shape that is being transformed
   * @return StringBuilder representing all of the animations needed to move the position
   */
  StringBuilder generatePositionTag(int start, int end, IShape source);

  /**
   * Abstract method for dispatching to each shape to render their own animation tag for dimension.
   *
   * @param start  the starting tick of the animation
   * @param end    the ending tick of the animation
   * @param source the starting state of the shape that is being transformed
   * @return StringBuilder representing all of the animations needed to change the dimension
   */
  StringBuilder generateDimensionTag(int start, int end, IShape source);

  /**
   * Generates the color tag for the given Abstract shape.
   *
   * @param start  the start time of the motion.
   * @param end    the end time of the motion.
   * @param source the shape to be generated from/
   * @return string representation of the given shape's color tag.
   */
  StringBuilder generateColorTag(int start, int end, IShape source);

  /**Returns the Attributes
   *
   *
   * @return
   */
  Attributes getAttributes();
}
