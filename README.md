# PackmanGame
Object Oriented course  - Ex3


This project is a pacman game that runs on a map of part of the city Ariel.
It taks the bounds of the map in coords , and an image of the map.
It is coordinates between them.
In this project you can either draw pacmans and fruits 
(In our case, we were insperated by the children book, "The lion who loved strawberries"),
Or load csv files with coordinates, types, and other data of the pacmans and the fruits.

Our Packages and classes(for detailed information about the functions, please read the Java docs):

Algorithms:

Path.java 
PathNode.java
ShortestPathAlgo.java
ShortestPathAlgoThread.java

Coords:

coords_converter.java
coords_converter.java - A class that cont
ains calculations between points: 
distance, add, vector3D,azimuth_elevation_dist.

File_Format:

csv2kml.java 
Path2KML.java - A class that converts the game results so it can be presented on googlEarth.
GameData:

Fruit.java - present a fruit with ID, weight, and coords.
Pacman.java - presents a pacman with ID, coords, eating radius and speed.
Game.java - contains an ArrayList of Fruit and another one for Pacman.
It can be build from a csv file with the function csvToGame, and you can insert a game data to a csv file,
with the function GameToCsv.
timeData.java
Geom:

Geom_element.java
Point3D.java

Map:

Map.java
MyFrame.java - An extend to Jframe. A GUI that allows the user to play the game.
It allow te user to draw fruits and pacman. By clicking once on fruit/pacman,
it will allow you to start drawing, the second click will cancel the option to draw.
By clicking "clear", will clear the screen from all the pacmans and fruits.
By clicking "load csv", this will load a game from a csv file, and presents,
For this, we used file chooser and the code from this tutorial: https://www.youtube.com/watch?v=9VrtranTJnc
on the map,  all the fruits and pacmans, In accordance with their actual coords. 
By clicking "Start" the game will start. 
By clicking "save KML" this will save the results of the game as a kml file that can be displayed on google earth.

SortestPathAlgoThread:
a class that exist in Algorithms packege that extends the Thread class in java.
this class job is to move all the pacmans in the map to thaere fruits by calcutating
there next step withe the vector3D function in mycoords class, until the packman get to his destnation.
this class uses threds so all the packmans in the map moving in the same time
