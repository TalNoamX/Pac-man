package Algorithms;
import GIS.*;
import java.io.File;

import File_format.csv2kml;
/**
 * A class that scanns a directory and all it's sub-directorys to find csv files,
 *than turn them into kml files. In addition, adding each csv file to a layer, and each layer to a project.
 *
 * @author Oranit
 *
 */
public class MultyCSV {
	project project;

	public MultyCSV() {
		project = new project();
	}
	/**
	 * in order to read multiple csv files, the user must create a file first with the requested directory,
	 *  and than send the file itself to findFile().
	 * @param dir is the directory in the storage you would like to start the searching.
	 * @return the directory that was send
	 */
	public File findFile(File dir) {
		File result = null; //initialize result
		File[] dirlist = dir.listFiles(); //put in array of file, all the folders is the directory file.
		for(int i = 0; i < dirlist.length; i++) { 
			if(dirlist[i].isDirectory()) {
				result = findFile(dirlist[i]); //Recursive call of the function to find all the files is this specific directory.
				if (result!=null) break; // recursive call found the file; terminate the loop
			} 
			else if(dirlist[i].getName().endsWith(".csv")) { // when find a .csv file convert it to kml file.
				layer layer = new layer(); //new layer to pub in the project
				csv2kml csv2kml = new csv2kml(); //new csv2kml object
				csv2kml.csvReader(dirlist[i].getParent()+"\\"+dirlist[i].getName(), layer); //call the csv reader
				String name =dirlist[i].getName().substring(0, dirlist[i].getName().indexOf(".csv")); //make a String with the name of the csv file to use it next line for the kml file name. 
				if(!layer.isEmpty()) csv2kml.kmlWriter(name, layer); //in case we got any unvalid csv files.
				project.add(layer); //add the layer to the project 
			}
		}
		return result; // will return null if we didn't find anything
	}
	project getProject() {
		return project;
	}

}
