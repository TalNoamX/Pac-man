package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;

import GameData.Fruit;
import GameData.Game;
import GameData.Pacman;



public class Path2KML {
	public static void main(String[] args) {
		Game game = new Game();
		 
	}
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
		// This is a constant pattern to begin each kml file
		StringBuilder sb0 = new StringBuilder();
		sb0.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style "
				+ "id=\"pacman\"><IconStyle><Icon><href>http://icons.iconarchive.com/icons/mazenl77/I-like-buttons-3a/32/Cute-Ball-Games-icon.png</href></Icon><hotSpot x=\"32\" y=\"1\" "
				+ "xunits=\"pixels\" yunits=\"pixels\"/></IconStyle></Style><Style "
				+ "id=\"fruit\"><IconStyle><Icon><http://icons.iconarchive.com/icons/ergosign/free-spring/32/strawberry-icon.png</href></Icon><hotSpot "
				+ "x=\"32\" y=\"1\" xunits=\"pixels\" yunits=\"pixels\"/></IconStyle></Style><Style");
		sb0.append("\"<Style id=\"line\">\"<LineStyle>\r\n" + 
				"        <color>501408FF</color>\r\n" + 
				"        <width>4</width>\r\n" + 
				"      </LineStyle>\r\n" + 
				"      <PolyStyle>\r\n" + 
				"        <color>501408FF</color>\r\n" + 
				"      </PolyStyle>\r\n" + 
				"    </Style>");
		pw.write(sb0.toString());
		
		for(Pacman p: game.pList()){
			StringBuilder sbPacman = new StringBuilder();
			sbPacman.append("<Placemark>\r\n" + 
					"<description><![CDATA[id: <b>"+ p.getID()+"</b><br/>speed: <b>"+p.getSpeed()+"</b><br/>radius: <b>"+p.getRadius()+"</b>]]></description><TimeStamp>\r\n" + 
					"<when>"+p.GetTime()+"</when>\r\n" + 
					"</TimeStamp>\r\n" + 
					"<styleUrl>#pacman</styleUrl>\r\n" + 
					"<Point>\r\n" + 
					"<coordinates>"+p.getPoint().x()+", "+p.getPoint().y()+"</coordinates>\r\n" +
					"</Point>\r\n" + 
					"</Placemark>");
			pw.write(sbPacman.toString());
		}
		for(Pacman p: game.pList()) {
			StringBuilder sbFruitLocation = new StringBuilder();
			for(int i = 0; i<p.getPath().size();i++) {
				StringBuilder sbFruit = new StringBuilder();
				sbFruitLocation.append(p.getPath().get(i).getFruit().getPoint().x()+", "+p.getPath().get(i).getFruit().getPoint().y()+"\n");
				sbFruit.append("<Placemark>\r\n" + 
						"<description><![CDATA[id: <b>"+p.getPath().get(i).getFruitID()+"</b><br/>weight: <b>"+p.getPath().get(i).getFruit().getWeight()+"</b>]]></description><TimeStamp>"+
						"<when>"+p.getPath().get(i).GetTime()+"</when>\r\n" + 
						"</TimeStamp>\r\n" + 
						"<styleUrl>#fruit</styleUrl>\r\n" + 
						"<Point>\r\n" + 
						"<coordinates>"+p.getPath().get(i).getFruit().getPoint().x()+", "+p.getPath().get(i).getFruit().getPoint().y()+"</coordinates>\r\n" +
						"</Point>\r\n" + 
						"</Placemark>");
				pw.write(sbFruit.toString());
			}
			StringBuilder sbLine = new StringBuilder();
			sbLine.append("</Placemark> <Placemark>\r\n" + 
					"      <styleUrl>#line</styleUrl>\r\n" + 
					"      <LineString>\r\n" + 
					"        <extrude>1</extrude>\r\n" + 
					"        <tessellate>1</tessellate>\r\n" + 
					"        <coordinates>\n");
			pw.write(sbLine.toString());
			pw.write(sbFruitLocation.toString());
			sbLine = new StringBuilder();
			sbLine.append("      </coordinates>\r\n" + 
					"      </LineString>\r\n" + 
					"    </Placemark>\n");
			pw.write(sbLine.toString());
			for(Pacman p1: game.pList()){
				StringBuilder sbPacman = new StringBuilder();
				sbPacman.append("<Placemark>\r\n" + 
						"<description><![CDATA[id: <b>"+ p1.getID()+"</b><br/>speed: <b>"+p1.getSpeed()+"</b><br/>radius: <b>"+p1.getRadius()+"</b>]]></description><TimeStamp>\r\n" + 
						"<when>"+/*p.getPath().get(i).GetTime()*/"</when>\r\n" + //needs to be fixed
						"</TimeStamp>\r\n" + 
						"<styleUrl>#pacman</styleUrl>\r\n" + 
						"<Point>\r\n" + 
						"<coordinates>"+p1.getPoint().x()+", "+p1.getPoint().y()+"</coordinates>\r\n" +
						"</Point>\r\n" + 
						"</Placemark>");
				pw.write(sbPacman.toString());
			}
		}	
		// This is a constante pattern to end each kml file
		StringBuilder sb2 = new StringBuilder();
		sb2.append("</Document></kml>");
		pw.write(sb2.toString());
		pw.close();//closing the file
		return true;
	}
}

