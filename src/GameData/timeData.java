package GameData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class timeData {
	private String timeStamp;

	public timeData() {
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

	}
		
	public long getUTC() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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