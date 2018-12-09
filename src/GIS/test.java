package GIS;

import java.util.Arrays;

import Geom.Point3D;

public class test {

	public static void main(String[] args) {
		layer l = new layer();
		element e = new element("40:65:a3:35:4c:c4","Efrat","[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS]",	"01/12/2017 10:49",	"1",	"-75",	"32.17218268",	"34.81446402",	"13.65040889",	"6",	"WIFI");
		l.add(e);
		metaData data =(metaData) e.getData();
		String srt = data.FirstSeen();
		boolean f = l.contains(e);
		
		System.out.println(data.getUTC());
	}
}
