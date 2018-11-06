
Our Animator is represented by the concrete class AnimatorModelImpl, which implements the
AnimatorModel interface. Animation commands are represented as a Command object, with start/end
times, shape at start of the command (to be updated as time passes) and shape at end of command. We
chose this because it simplified the process of transformation over time between shapes, and coupled
the 2 shapes together with the times relevant to mutation. A when the user inputs an animation, it
is essentially inputting a future shape.

We chose to have Commands keep track of where they are going over our initial idea of giving shapes
velocities towards their destinations for the duration of the command because on a large scale
velocities could cause rounding errors and not reaching your intended destination exactly.

Shapes are represented by concrete shape classes (Oval, Circle, etc.) which are all subclasses of
AbstractShape. AbstractShape is responsible for the shape's color and coordinates (because every
shape shares them), as well as a width and height. While each concrete shape class has its own
measurement fields, all can be represented with width and height which allows for cleaner retrieval
of information from each shape when transforming them.

Some design notes:
- We currently assume that a shape can move off screen, and if so they are just not rendered.


