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
	private Point3D thirdEdge;
	private Point3D lonLat;

	public Point3D degToPix(Point3D p) {
		MyCoords mc = new MyCoords();
		Point3D vector = mc.vector3D(p, top);
		int xPix = (int) (vector.x()*myImage.getWidth()/lonLat.x());
		int yPix = (int) (vector.y()*myImage.getHeight()/lonLat.y());
		Point3D pix = new Point3D (xPix, yPix);
		return pix;
	}

      	
	public Point3D pixTodeg (Point3D pixle) {
		double lat = ((myImage.getWidth()*top.x())+pixle.x()*(bottom.x()-top.x()))/myImage.getWidth();
		double lon = ((myImage.getHeight()*bottom.y())+(myImage.getHeight()-pixle.y())*(top.y()-bottom.y()))/myImage.getHeight();
		Point3D gps = new Point3D(lat,lon);
		return gps;
	}
	
	public double pixDistance(Point3D p1, Point3D p2) {
		double distance = Math.sqrt(Math.pow(p2.x()-p1.x(),2)+Math.pow(p2.y()-p1.y(), 2));
		return distance;
	}
	/**
	 * lon + lat in meters.
	 */
	private void setlonLat() {
		MyCoords mc = new MyCoords();
		double lon = mc.distance3d(thirdEdge, bottom);
		double lat = mc.distance3d(top, thirdEdge);
		Point3D lonLat = new Point3D (lon,lat);
		this.lonLat=lonLat;
	}

	public Map(String path, Point3D top, Point3D bottom) {
		if(!top.equals(bottom)) {
			this.top=top;
			this.bottom=bottom;
			thirdEdge = new Point3D (top.x(),bottom.y());
		}
		else {
			top = new Point3D(0,0);
			bottom = new Point3D(0,0);
			thirdEdge = new Point3D(0,0);	
		}
		setlonLat();
		setMyImage(path);
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


//Point3D top = new Point3D(32.10555556,35.21222222,0);
//Point3D Bottom = new Point3D(32.10166667,35.20222222,0);

////double deglon = Math.abs(top.x()-thirdEdge.x());
//double degLat = Math.abs(bottom.x()-thirdEdge.x()); 
//double lon = pixle.x()*degLat/myImage.getWidth()+this.top.x();
////double lon = pixle.y()*deglon/myImage.getHeight()+this.top.y();
