How to run: 

Locate downloaded jar file in your command line then type ‘java -jar Asteroids.jar’ into your command line.
Alternatively, you may be able to simply select the executable jar to run depending on the settings on your computer. 
If you are still having issues, I recommend downloading the source code from github and compiling & running in your preferred IDE.

Acknowledgments:

Although much of this code was written by me the following classes were provided by our professor as the primary focus of this 
project was to focus on object oriented design, while utilizing provided classes and libraries: 

  **GameDriver.java** : This driver class specified the basic behavior of the program, and to test for functionality. It basically sets 
  the size of the game window and prepares the GUI to be updated with animated objects.

  **GameUtils.java** : Contains utility methods for working with Poses, Points, and Vectors in Asteroids. 
  **Point.java** : This code is an encapsulation of a two-dimensional point provided by our professor.
  **Pose.java**: A Pose is a Point with an associated heading.
  **Vector2D.java** : Class representing a two-dimensional vector.

  **StdDraw**
  **StdDraw.java** : This program utilizes StdDraw, a class belonging to a public Java library called StdLib provided by Princeton University. 
  It provides methods for creating drawings. These drawings are created rapidly to create the game animations. Each time the 'game' is updated, 
  the coordinates and other attributes of each GameElement object are updated and drawn on the screen using this provided class.
