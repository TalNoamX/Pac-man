package File_format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import GIS.*;
import Geom.Point3D;
/**
 * A class that converts csv files to kml, in order to present them in "Google Earth".
 * @author Oranit
 * @author Tal
 *
 */
public class csv2kml 
{
	/**
	 * A csv reader tha enters each line in the csv file to a new elemnt,
	 *  and than the element to a new layer.
	 * @param name the name of the file that we want to read
	 * @param layer the layer that we want to enter the data to.
	 */
	public boolean csvReader(String name, layer layer) {
		String csvFile = name;
		String line = "";
		int row = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				row++;
				if(row>2) {
					String[] data = line.split(",");
					//making sure we got valid csv files.
					if(data.length==11) {
						layer.add(new element(data[0],data[1],data[2],data[3],data[4],data[5],data[7],data[6],data[8],data[9],data[10]));
					}
				}
			}
			return true;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * A kml writer
	 * @param name the name of the file requested.
	 * @param layer using the layer's elements to write the kml file.
	 */
	public boolean kmlWriter(String name, layer layer) {

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
		// This is a constante pattern to begin each kml file
		StringBuilder sb0 = new StringBuilder();
		sb0.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + "");
		sb0.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style"
				+ " id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style "
				+ "id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style"
				+ " id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\r\n" + "");
		pw.write(sb0.toString());

		Iterator<element> it = layer.iterator();
		while(it.hasNext()) {//each element will be translated to a kml file.
			element gis = it.next();
			metaData mD = (metaData) gis.getData();
			Point3D pD = (Point3D) gis.getGeom();
			StringBuilder sb1 = new StringBuilder();
			sb1.append("<Placemark>\n");
			sb1.append("<name><![CDATA["+gis.getOtherData().SSID()+"]]></name>\n");	//adding ssid
			sb1.append("<description><![CDATA[BSSID: <b>"+gis.getOtherData().MAC()+"</b><br/>");//adding mac
			sb1.append("Capabilities: <b>"+gis.getOtherData().AuthMode()+"</b><br/>");//adding authmode
			sb1.append("Frequency: <b>"+gis.getOtherData().RSSI()+"</b><br/>"); //adding rssi
			sb1.append("Timestamp: <b>"+gis.getData().getUTC()+"</b><br/>");//adding a timestamp using utc
			sb1.append("Date: <b>"+mD.FirstSeen()+"</b>]]></description><styleUrl>#red</styleUrl>\n");//adding the time
			sb1.append("<Point>\n");
			sb1.append("<coordinates>"+pD.x()+","+pD.y()+"</coordinates></Point>\n");//adding coordinates
			sb1.append("</Placemark>\n");
			pw.write(sb1.toString());//writing the element's details in the file
		}
		// This is a constante pattern to end each kml file
		StringBuilder sb2 = new StringBuilder();
		sb2.append("</Folder>\n");
		sb2.append("</Document></kml>");
		pw.write(sb2.toString());
		pw.close();//closing the file
		return true;
	}
}