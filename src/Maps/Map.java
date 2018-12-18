package Maps;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Coords.MyCoords;
import Geom.Point3D;

public class Map {
	
	private BufferedImage myImage;
	private Point3D top;
	private Point3D bottom;
	private int imgWidth;
	private int imgHeight;
	private Point3D lonLat;

	public Point3D degToPix(Point3D p) {
		double lat = ((p.x()-bottom.x())*(myImage.getWidth()))/(top.x()-bottom.x());
		double lon = ((p.y()-bottom.y())*(myImage.getHeight()))/(top.y()-bottom.y());
		return new Point3D(lat,lon);
//		MyCoords mc = new MyCoords();
//		Point3D vector = mc.vector3D(p, top);
//		int xPix = (int) (vector.x()*myImage.getWidth()/lonLat.x());
//		int yPix = (int) (vector.y()*myImage.getHeight()/lonLat.y());
//		Point3D pix = new Point3D (xPix, yPix);
//		return pix;
	}

	public Point3D pixTodeg (Point3D pixle) {
		double lat = ((myImage.getWidth()*top.x())+pixle.y()*(bottom.x()-top.x()))/myImage.getWidth();
		double lon = ((myImage.getHeight()*bottom.y())+(myImage.getHeight()-pixle.x())*(top.y()-bottom.y()))/myImage.getHeight();
		Point3D gps = new Point3D(lat,lon);
		return gps;
	}
	
	public double pixDistance(Point3D p1, Point3D p2) {
		double distance = Math.sqrt(Math.pow(p2.x()-p1.x(),2)+Math.pow(p2.y()-p1.y(), 2));
		return distance;
	}
	
//	/**
//	 * lon + lat in meters.
//	 */
//	private void setlonLat() {
//		MyCoords mc = new MyCoords();
//		double lat = mc.distance3d(bottom, thirdEdge);
//		double lon = mc.distance3d(top, thirdEdge);
//		Point3D lonLat = new Point3D (lat,lon);
//		this.lonLat=lonLat;
//	}

	public Map(String path, Point3D top, Point3D bottom, int imgWidth, int imgHeight) {
		if(!top.equals(bottom)) {
			this.top=top;
			this.bottom=bottom;
		}
		else {
			top = new Point3D(0,0);
			bottom = new Point3D(0,0);
		}
		this.imgHeight=imgHeight;
		this.imgWidth=imgWidth;
//		setlonLat();
		setMyImage(path);
	}
	
	public void setHeight(int height) {
		imgHeight=height;
	}
	public void setWidth(int width) {
		imgWidth=width;
	}
	private void setMyImage(String path) {
		try {
			File file = new File(path);
			myImage = ImageIO.read(file);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getmyImage() {
		return myImage;
	}
}
