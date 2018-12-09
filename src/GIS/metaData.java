package GIS;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import Geom.Point3D;
/**
 * A representation of Time stamp of a point
 * @author Oranit
 * @author Tal
 */
public class metaData implements Meta_data {
	private String FirstSeen;
	/**
	 * FirstSeen as called in the csv file
	 * @param ftSeen
	 */
	public metaData(String ftSeen) {
		FirstSeen = ftSeen;
	}
	public String FirstSeen() {
		return FirstSeen;
	}
	
	/**
	 *  returns the Universal Time Clock associated with this data; 
	 */
	@Override
	public long getUTC() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long milliseconds = 0;
		try {
			Date d = format.parse(this.FirstSeen);
		    milliseconds = d.getTime();
			}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return Math.abs(milliseconds);
		
	}
	
	public String toString() {
		return FirstSeen;
	}

	@Override
	public Point3D get_Orientation() {
		return null;
	}

}
