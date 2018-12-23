package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import Algorithms.PathNode;
import GameData.*;


/**
 * A class that turns the results of a game to KML. 
 * On GoogleEarth it will display the pacmans and the fruits, and once the fruits are eaten it will paint "V" sign on them.
 * @author Oranit
 *
 */
public class Path2KML {

	double timeStamp;
	Game game;

	public Path2KML(Game game) {
		this.game=game;
	}
	
	
	public boolean kmlWriter(String name) {

		String fileName = name+".kml";
		PrintWriter pw = null;
		try 
		{
			pw = new PrintWriter(new File(fileName));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return false;
		}
		//Prints the constant start of the KML, with the icons for the lion, strawberry and a "V" sign that said the fruit was eaten. 
		StringBuilder sb0 = new StringBuilder();
		sb0.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document>\r\n" + 
				"<Style id=\"lion\"><IconStyle><Icon><href>http://icons.iconarchive.com/icons/google/noto-emoji-animals-nature/72/22222-lion-face-icon.png</href></Icon>\r\n" + 
				" <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/></IconStyle></Style>\r\n" + 
				" <Style id=\"fruit\"><IconStyle><Icon><href>http://icons.iconarchive.com/icons/martin-berube/food/32/strawberry-icon.png</href></Icon>\r\n" + 
				" <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/></IconStyle></Style>\r\n" + 
				" <Style id=\"done\"><IconStyle><Icon><href>http://icons.iconarchive.com/icons/saki/snowish/96/Ok-icon.png</href></Icon>\r\n" + 
				" <hotSpot x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/></IconStyle></Style> ");
		pw.write(sb0.toString());
		for(Pacman p: game.pList()){//Prints all the pacmans at their beginning point, with their id, speed, radius, and time of craetion.
			String time = p.GetTime().replaceFirst(" ", "T")+"Z";
			StringBuilder sbPacman = new StringBuilder();
			sbPacman.append("<Placemark>\r\n" +
					"<description><![CDATA[id: <b>"+ p.getID()+"</b><br/>speed: <b>"+p.getSpeed()+"</b><br/>radius: <b>"+p.getRadius()+"</b>]]></description><TimeStamp>\r\n" + 
					"<when>"+time+"</when>\r\n" + 
					"</TimeStamp>\r\n" + 
					"<styleUrl>#lion</styleUrl>\r\n" + 
					"<Point>\r\n" + 
					"<coordinates>"+p.getPath().getStartLocation().y()+","+p.getPath().getStartLocation().x()+","+p.getPath().getStartLocation().z()+"</coordinates>\r\n" +
					"</Point>\r\n" + 
					"</Placemark>");
			pw.write(sbPacman.toString());
		}
		for(Fruit f: game.fList()) {//Prints all the fruits at their beginning point with their weight, Id and time of creation.
			StringBuilder sbFruit = new StringBuilder();
			String time = f.GetTime().replaceFirst(" ", "T")+"Z";
			sbFruit.append("<Placemark>\r\n" + 
					"<description><![CDATA[id: <b>"+ f.getID()+"</b><br/>weight: <b>"+f.getWeight()+"</b>]]></description><TimeStamp>\r\n" + 
					"<when>"+time+"</when>\r\n" + 
					"</TimeStamp>\r\n" + 
					"<styleUrl>#fruit</styleUrl>\r\n" + 
					"<Point>\r\n" + 
					"<coordinates>"+f.getPoint().y()+", "+f.getPoint().x()+","+f.getPoint().z()+"</coordinates>\r\n" +
					"</Point>\r\n" + 
					"</Placemark>");
			pw.write(sbFruit.toString());
		}
		for(Pacman p: game.pList()) {//For each pacman, prints the fruits he will eat,prints the "V" sign, than prints the pacman in it's new location
			StringBuilder sbFruitLocation = new StringBuilder();
			for(PathNode pNode: p.getPath()) {
				StringBuilder sbFruit = new StringBuilder();
				String time = pNode.GetTime().replaceFirst(" ", "T")+"Z";
				sbFruitLocation.append(pNode.getFruit().getPoint().y()+","+pNode.getFruit().getPoint().x()+"\n");
				sbFruit.append("<Placemark>\r\n" + //prints the fruit.
						"<description><![CDATA[id: <b>"+pNode.getFruitID()+"</b><br/>weight: <b>"+pNode.getFruit().getWeight()+"</b>]]></description><TimeStamp>"+
						"<when>"+time+"</when>\r\n" + 
						"</TimeStamp>\r\n" + 
						"<styleUrl>#fruit</styleUrl>\r\n" + 
						"<Point>\r\n" + 
						"<coordinates>"+pNode.getFruit().getPoint().y()+","+pNode.getFruit().getPoint().x()+","+pNode.getFruit().getPoint().z()+"</coordinates>\r\n" +
						"</Point>\r\n" + 
						"</Placemark>");
				sbFruit.append("<Placemark>"+//prints the "V" sign.
						"<name>done</name>" +
						"<description><![CDATA[id: <b>"+pNode.getFruitID()+"</b><br/>weight: <b>"+pNode.getFruit().getWeight()+"</b>]]></description><TimeStamp>\r\n" + 
						"<when>"+time+"</when>\r\n" + 
						"</TimeStamp>\r\n" + 
						"<styleUrl>#done</styleUrl>\r\n" + 
						"<Point>\r\n" + 
						"<coordinates>"+pNode.getFruit().getPoint().y()+","+pNode.getFruit().getPoint().x()+","+pNode.getFruit().getPoint().z()+"</coordinates>\r\n" + 
						"</Point>\r\n" + 
						"</Placemark>");
				sbFruit.append("<Placemark>\r\n" +//prints the pacman.
						"<description><![CDATA[id: <b>"+ p.getID()+"</b><br/>speed: <b>"+p.getSpeed()+"</b><br/>radius: <b>"+p.getRadius()+"</b>]]></description><TimeStamp>\r\n" + 
						"<when>"+time+"</when>\r\n" + 
						"</TimeStamp>\r\n" + 
						"<styleUrl>#lion</styleUrl>\r\n" + 
						"<Point>\r\n" + 
						"<coordinates>"+pNode.getFruit().getPoint().y()+","+pNode.getFruit().getPoint().x()+","+pNode.getFruit().getPoint().z()+"</coordinates>\r\n" +
						"</Point>\r\n" + 
						"</Placemark>");
				pw.write(sbFruit.toString());
			}
		}	
		// This is a constant pattern to end each kml file
		StringBuilder sb2 = new StringBuilder();
		sb2.append("</Document></kml>");
		pw.write(sb2.toString());
		pw.close();//closing the file
		return true;
	}
}

