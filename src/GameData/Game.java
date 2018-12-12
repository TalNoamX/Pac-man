package GameData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import Geom.Point3D;

public class Game {

	private ArrayList<Packman> pList;
	private ArrayList<Fruit> fList;
	private int pacmanNum;
	private int fruitNum;

	public Game() {
		pList = new ArrayList<Packman>();
		fList = new ArrayList<Fruit>();

	}
	public ArrayList<Packman> pList() {
		return pList;
	}
	public ArrayList<Fruit> fList() {
		return fList;
	}
/*
	***********************OPTIONAL***********************************************
	*THIS SECTION IS NOT CLEAR YET! DO NOT TOUCH IT (TAL'S WORK)
	*maybe we will need add and remove function and maybe we will use the original ArrayList add and remove.
	
		public boolean addPackman(Packman p) {
			if (pList.add(p)==true) {
				packmanNum++;
				return true;
			}
			else return false;
		}
		
		public boolean addFruit(Fruit f) {
			if (fList.add(f)==true) {
				fruitNum++;
				return true;
			}
			else return false;
		}
		public boolean removeFruit(Fruit f) {
			if (fList.remove(f)==true) {
				fruitNum--;
				return true;
			}
			else return false;
		}
	************************************************************************************* */
	public Game(String name) {
		pList = new ArrayList<Packman>();
		fList = new ArrayList<Fruit>();
		String csvFile = name;
		String line = "";
		String type="";
		int row = 1;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				if(row==1) {
					this.setPackmanNum(Integer.parseInt(data[7]));
					this.setFruitNum(Integer.parseInt(data[8]));
				}
				else {
					type = data[0];
					if(type.equals("P")) {
						this.pList.add(new Packman (data[1],data[2],data[3],data[4],data[5],data[6]));
					}
					else if (type.equals("F")) {
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

	public void gameToCsv(String name) { 
		String fileName = name+".csv";
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
		sb0.append("Type, id, Lat, Lon, Alt, speed\\weight, Radius,"+ pList.size()+","+fList.size()+"\n");
		pw.write(sb0.toString());
		StringBuilder sb1 = new StringBuilder();
		for(Packman p: pList) {
			sb1.append("P,"+p.getID()+","+((Point3D) p.getGeom()).x()+","+((Point3D) p.getGeom()).y()+","+((Point3D) p.getGeom()).z()+","+p.getSpeed()+","+p.getRadius()+"\n");
		}
		pw.write(sb1.toString());
		StringBuilder sb2 = new StringBuilder();
		for(Fruit f:fList) {
			sb2.append("P,"+f.getID()+","+((Point3D) f.getGeom()).x()+","+((Point3D) f.getGeom()).y()+","+((Point3D) f.getGeom()).z()+","+f.getWeight()+","+"\n");
		}
		pw.write(sb2.toString());
		pw.close();//closing the file
	}

	public void setPackmanNum(int pacmanNum) {
		this.pacmanNum = pacmanNum;
	}

	public void setFruitNum(int fruitNum) {
		this.fruitNum = fruitNum;
	}

	public int getPackmenNum() {
		return this.pacmanNum;
	}

	public int getFruitNum() {
		return this.fruitNum;
	}
}
