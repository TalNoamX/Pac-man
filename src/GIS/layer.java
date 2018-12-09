package GIS;


import java.util.ArrayList;
import java.util.Date;
/**
 *A class that represents an array list of elements 
 *@author Oranit
 *@author Tal
 */
public class layer extends ArrayList<element> implements GIS_layer {
	private static final long serialVersionUID = 1L;

	private Meta_data data;
	private Date date;


	@SuppressWarnings("deprecation")
	public layer() {
		date = new Date();
		String time = String.valueOf(date.getDate())+"/"+String.valueOf(date.getMonth())+"/"+String.valueOf(date.getYear())+" "+String.valueOf(date.getHours())+":"+String.valueOf(date.getMinutes())+":"+String.valueOf(date.getSeconds());
		data = new metaData(time);
	}

	public Meta_data get_Meta_data() {
		return data;
	}


}
