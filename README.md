# Pac-man Game
Object Oriented course  - Ex3

This project is a pacman game that runs on a map of part of the city Ariel.
It taks the bounds of the map in coords ,and an image of the map.
It is coordinates between them.
In this project you can either draw pacmans and fruits 
(In our case, we were inspired by the children's book, "The lion who loved strawberries"),
Or load csv files with coordinates, types, and other data of the pacmans and the fruits.
When the game is finished, you can save the results in a KML file that fits GoogleEarth.

Our Packages and classes(for detailed information about the functions, please read the Java docs):

Algorithms:

PathNode.java - This class represent node for the path of every pacman.
It saves every detail the algorithm needs to calculate the shortest path algorithm.

Path.java - This class represent the full path for very pacman with the specific fruit it eats and how long it would take.

ShortestPathAlgo.java -
This class is made to calculate the shortest path from one pacman or more. 
after we have the shortest path it updates every pacman's path to be as fast as possible.
When all the paths are ready, with "Start" function it will call the thread to move the pacmans at the same time.

ShortestPathAlgoThread.java - this class extend Thread class, and use to move the pacmans on the map all the way to their fruits  

Coords:

coords_converter.java - This interface represents a basic coordinate system converter, including:
 1. The 3D vector between two lat,lon, alt points 
 2. Adding a 3D vector in meters to a global point.
 3. convert a 3D vector from meters to polar coordinates
 
MyCoords.java - A class that implements coords_conveter.
ains calculations between points: 
distance, add, vector3D,azimuth_elevation_dist.

File_Format:

Path2KML.java - A class that converts the game results so it can be presented on googlEarth, with a timeline. It displays the pacmans and the fruits, in the order they were created, and than the order the pacmans eat the fruits. When a pacman eats a fruit, there will be a "V" sign on the fruit and a "done" will be written next to it.

GameData:

Fruit.java - present a fruit with ID, weight, and coords.

Pacman.java - presents a pacman with ID, coords, eating radius and speed.

Game.java - contains an ArrayList of Fruit and another one for Pacman.
It can be build from a csv file with the function csvToGame, and you can insert a game data to a csv file, with the function GameToCsv.

timeData.java - A class represent Time. every pacman and fruit have the time of creation and time when got eaten.

Geom:

Geom_element.java - An interdace that presents distance in 2 and 3 dimensions.

Point3D.java - This class represents a 3D point in space.

Map:

Map.java

MyFrame.java - An extend to Jframe. A GUI that allows the user to play the game.
It allow the user to draw fruits and pacman. By clicking once on fruit/pacman,
it will allow you to start drawing, the second click will cancel the option to draw.
By clicking "clear", will clear the screen from all the pacmans and fruits.
By clicking "load csv", this will load a game from a csv file, and presents,
For this, we used file chooser and the code from this tutorial: https://www.youtube.com/watch?v=9VrtranTJnc
on the map,  all the fruits and pacmans, In accordance with their actual coords. 
By clicking "Start" the game will start. 
By clicking "save KML" this will save the results of the game as a kml file that can be displayed on google earth with a timeline.

mapJunit - test to map functions.
