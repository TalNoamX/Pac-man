package GameData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class represent Time. every pacman and fruit have the time of creation and time when got eaten.
 * @author Tal
 * @author Oranit
 * @author Amitai
 */
public class timeData {
	private String timeStamp;
	/**
	 * Constructor using the same time format for everything.
	 */
	public timeData() { 
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

	}
	/**
	 * This function gets a timestamp and give back UTC time in milliseconds
	 * @return the time in milliseconds
	 */
	public long getUTC() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //time format
		long milliseconds = 0;
		try {
			Date d = format.parse(this.timeStamp);
			milliseconds = d.getTime();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return (milliseconds/1000);
	}

	public String getTime() {
		return timeStamp;
	}

}