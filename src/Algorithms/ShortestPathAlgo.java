package Algorithms;

import java.util.Iterator;
import GameData.Game;
import GameData.Pacman;
import Maps.MyFrame;
/**
 * This class made to calculate the shortest path from one pacman or more. 
 * after we have the shortest path it update every pacman's path to be as fast as possible.
 * and when all the paths are ready with "Start" function it call the thread to move the pacmans at the same time.
 * @author 1.Tal 
 * @author 2.Amitai
 * @author 3.Oranit
 *
 */
public class ShortestPathAlgo {
	private Game game;
	private Path shortestPath;
	private MyFrame MF;

	/**
	 * constructor
	 * @param game to get the pList and fList
	 * @param MF to print the pacmans later 
	 */
	public ShortestPathAlgo(Game game,MyFrame MF) {
		this.game=game;
		this.pacmansPath(); // we want to split the run time of this algorithm so we call this function upon initialization.
		this.MF=MF;
	}

	/**
	 * This function activate the shortest path.
	 *  it take every pacman to it's fruit "physically" by sending threads to the ShortestPathAlgoThread class.
	 */
	public void Start() { 
		Iterator<Pacman> pacIter=game.pList().iterator();
		while(pacIter.hasNext()) { //run on the pacman arraylist
			Pacman pac=pacIter.next();
			Thread T = new ShortestPathAlgoThread(pac, MF);
			T.start(); // call the run function
		}
		MF.results();
	}
	
	/**
	 * this function takes the fastest path and compare it with each pacmans path, then delete the fruits that this pacman do not eat,
	 *  delete is in his own path.
	 */
	private void pacmansPath() { 
		this.getTheShortestPath(); //call the TheShortestPath function
		for(int i=0;i<game.pList().size();i++) { // loop that runs on the pacmans
			int pacmanID=game.pList().get(i).getID(); //save pacman ID

			for(int k=0;k<shortestPath.size();k++) { // loop that runs on the The shortest path
				int shortListPacID=shortestPath.get(k).getPacmanID(); //save fruit ID

				for(int j=0;j<game.pList().get(i).getPath().size();j++) { //loop that run on the pacman's path.
					if(shortestPath.get(k).getFruitID() == game.pList().get(i).getPath().get(j).getFruitID()) { //check if the fruit ID on the ShortestPath is the same as the fruit ID on the pacman's path.
						if(shortListPacID != pacmanID) { //check if the pacman ID on the ShortestPath is NOT the same as this pacman's ID
							game.pList().get(i).getPath().remove(j); // delete this pathNode from this pacman's path because he do not suppose the eat it
						}
					}
				}

			}

		}

	}
	
	/**
	 * This function creates the Shortest Path this the path class.
	 * @return the shortestPath is the best way to split the pacmans among the fruit.
	 */
	private Path getTheShortestPath() { 
		for(int i=0;i<game.pList().size();i++) { //loop that runs on the pacmans
			Path temp = new Path(game.fList(),game.pList().get(i)); //call the "path" class the create a new pacman path
			game.pList().get(i).setPath(temp); // set the path of every pacman. we use it in pacmansPath after.
			if(i==0) { //if it the first time we run save it as the shortestPath
				shortestPath=new Path(temp);
			}
			else { //if its not the first time start to compare
				for(int j=0;j<temp.size();j++) { // loop that runs on the pacman path
					for(int k=0;k<shortestPath.size();k++) { // loop that runs on the shortestPath 
						if(temp.get(j).getFruitID() == shortestPath.get(k).getFruitID()) { //check if the fruit ID is equal
							if(temp.get(j).getRunTime()<shortestPath.get(k).getRunTime()) { // if temp node have better run time then shortestPath we set this point for a new pacman(pacman of temp).
								shortestPath.get(k).setRunTime(temp.get(j).getRunTime());
								shortestPath.get(k).setPacmanID(temp.get(j).getPacmanID());
							}
						}
					}
				}
			}
		}
		return shortestPath;
	}
}