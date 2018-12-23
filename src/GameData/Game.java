package GameData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import Geom.Point3D;
/**
 * This class is a data structure for the game.
 * it keeps two ArrayLists, one for the fruits and one for the pacmans.
 * @author Tal
 *@author Oranit
 *@author Amitai
 */
public class Game {

	private ArrayList<Pacman> pList;
	private ArrayList<Fruit> fList;

	/**
	 * constructor
	 */
	public Game() {
		pList = new ArrayList<Pacman>();
		fList = new ArrayList<Fruit>();
	}
	/**
	 * copy constructor
	 * @param game copy from this object
	 */
	public Game(Game game) {
		pList=new ArrayList<Pacman>(game.pList());
		fList=new ArrayList<Fruit>(game.fList());
	}
	/**
	 * This function give the user the option to use the ArrayList methods
	 * @return ArrayList<Pacman>
	 */
	public ArrayList<Pacman> pList() {
		return pList;
	}
	/**
	 * This function give the user the option to use the ArrayList methods
	 * @return ArrayList<Fruit>
	 */
	public ArrayList<Fruit> fList() {
		return fList;
	}
	/**
	 * This method take a csv file, and read it's information into the Game ArrayLists
	 * @param path , is the folder path to the file.
	 */
	public void csvToGame(String path) {
		String csvFile = path;
		String line = ""; //saves each line
		String type=""; // will tell us if it's a packman or a fruit
		int row = 1;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if(row>1) {//first line is headlines
					type = data[0];// determine the type
					if(type.equals("P")) {//adding packman
						this.pList.add(new Pacman (data[1],data[2],data[3],data[4],data[5],data[6]));
					}
					else if (type.equals("F")) {//adding Fruits
						this.fList.add(new Fruit(data[1],data[2],data[3],data[4],data[5]));
					}
				}
				row++;
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method take's a Game class, and read it's information into the a selected csv file.
	 * @param path , is the folder path to the file.
	 */
	public void GameToCsv(String path) { 
		String fileName = path+".csv";
		PrintWriter pw = null;
		try 
		{
			pw = new PrintWriter(new File(fileName));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		StringBuilder sb0 = new StringBuilder();
		sb0.append("Type, id, Lat, Lon, Alt, speed\\weight, Radius,"+ pList.size()+","+fList.size()+"\n");//printing headlines
		pw.write(sb0.toString());
		StringBuilder sb1 = new StringBuilder();
		for(Pacman p: pList) {//adding all the pacmans
			sb1.append("P,"+p.getID()+","+((Point3D) p.getPoint()).x()+","+((Point3D) p.getPoint()).y()+","+((Point3D) p.getPoint()).z()+","+p.getSpeed()+","+p.getRadius()+"\n");
		}
		pw.write(sb1.toString());
		StringBuilder sb2 = new StringBuilder();
		for(Fruit f:fList) {//adding all the fruits
			sb2.append("P,"+f.getID()+","+((Point3D) f.getPoint()).x()+","+((Point3D) f.getPoint()).y()+","+((Point3D) f.getPoint()).z()+","+f.getWeight()+","+"\n");
		}
		pw.write(sb2.toString());
		pw.close();//closing the file
	}

}
