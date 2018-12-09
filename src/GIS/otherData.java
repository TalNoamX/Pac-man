package GIS;

/**
 * A class that collect all the information about an element that isn't time stamp or coordinates.
 *@author Oranit
 *@author Tal
 */
public class otherData {
	private String MAC, SSID, AuthMode, Channel,	RSSI, AccuracyMeters, Type;
	
	public otherData(String mac, String ssid,String authMode, String channel, String rssi, String accuMeters, String type) {
		MAC = mac;
		SSID = ssid;
		AuthMode = authMode;
		Channel = channel;
		RSSI = rssi;
		AccuracyMeters = accuMeters;
		Type = type;
	}
	
	public String MAC() {
		return MAC;
	}
	public String SSID() {
		return SSID;
	}
	public String AuthMode() {
		return AuthMode;
	}
	public String Channel() {
		return Channel;
	}
	public String RSSI() {
		return RSSI;
	}
	public String AccuracyMeters() {
		return AccuracyMeters;
	}
	public String Type() {
		return Type;
	}
	
	
	public String toString() {
		return this.MAC()+" "+this.SSID()+" "+this.AuthMode()+" "+this.Channel()+" "+this.RSSI()+" "+this.AccuracyMeters()+" "+this.Type();
	}


}
