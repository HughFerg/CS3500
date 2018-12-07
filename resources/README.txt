Our Animator is represented by the concrete class AnimatorModelImpl, which implements the
AnimatorModel interface. Animation commands are represented as a Command object, with start/end
times, concrete shape at start of the command (to be updated as time passes) and shape at end of command. We
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

Our model is responsible for ensuring that our data makes sense by checking for things such as
negative ticks and and null models. We decided that it is the responsibility of our controller
to deliver our data to our model in the way we desire, thus our model does not initialize our
invariants but still maintains them. These invariants being no overlaps and no empty space between
animations.

Our SVG view is constructed by dispatching as far as we reliably can down to our base shapes. For 
color we are able to share implementation via our abstractShape class but most other transformations
are required to be specified by our individual shapes.

Our textView is build off of the same render method from the previous assignment.

Our VisualView works by alternating between painting components which places the images that are visible 
on the canvas and ticking the model which updates all of the commands to the new positions of all
the
shapes. These two actions are separated by a thread.sleep which sleeps for 1 / TPS to get the correct
frame rate.

Our Editor view is a panel with editing operations on it (Add/update/delete) and containing a visual
 view. The editing processes are handled by the controller which dispatches to the model and views.

 Our controller handles both the model and the view, as well as editing/stopping operations like
 pause and loop. We had to refactor our design for this assignment by moving the conception of ticks
  and looping out of the model and into the controller, which uses that to render the animations
  correctly. We also created a read=only model so that the controller cannot mutate it incorrectly.

At the end of the day, we were not able to get keyframes editing implemented correctly in the their
views. We also had a peculiar bug where after resetting our animations become very jumpy. As we
were updating our model to be more flexible and more easily function under both of our sets of
views, we broke reset and were never able to get it back to its original functionality.
