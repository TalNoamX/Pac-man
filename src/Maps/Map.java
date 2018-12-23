package Maps;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Coords.MyCoords;
import Geom.Point3D;

public class Map {

	private BufferedImage myImage;
	private Point3D leftTop;
	private Point3D rightBottom;
	private Point3D rightTop;
	private int imgWidth;
	private int imgHeight;

	/**
	 *  Constructor
	 * @param path the images path
	 * @param leftTop the left top corner of the map in coords.
	 * @param rightBottom the righ tBottom corner of the map in coords.
	 * @param rightTop the right Top corner of the map in coords.
	 */
	public Map(String path, Point3D leftTop, Point3D rightBottom, Point3D rightTop) {
		this.leftTop=leftTop;
		this.rightBottom=rightBottom;
		this.rightTop=rightTop;
		setMyImage(path);//get path of the map Image
		imgHeight=myImage.getHeight();//get this pic height
		imgWidth = myImage.getWidth(); // get this pic width
	}

	/**
	 * Get new height
	 * @param height the map height
	 */
	public void setHeight(int height) {
		imgHeight=height;
	}

	/**
	 * Get new width
	 * @param width the map width
	 */
	public void setWidth(int width) {
		imgWidth=width;
	}
	/**
	 * This function reads an image that will be the map
	 * @param path the files path
	 */
	private void setMyImage(String path) {
		try {
			File file = new File(path);//new file object with the url of the image
			myImage = ImageIO.read(file);//read the image
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the map image
	 * @return the image
	 */
	public BufferedImage getmyImage() {
		return myImage;
	}

	/**
	 * This function coonvert point coordinates to point pixels
	 * @param point the point in coords
	 * @return the point in pixles
	 */
	public Point3D coords2pixels(Point3D point) {
		MyCoords coords = new MyCoords();
		Point3D vector = coords.vector3D(leftTop, point);// get the vector between the left top point to the point we enter
		double leftRight = this.imgWidth/coords.distance3d(leftTop, rightTop); //the distance(x) between left top point and the right top point
		double topBottom = this.imgHeight/coords.distance3d(rightTop, rightBottom); //the distance(y) between right top point and the right botom point
		int y = (int) Math.round(vector.x()*(-1)*topBottom); // the point y value
		int x =(int) Math.round(vector.y()*leftRight); // the x point value
		point=new Point3D(x,y); // the value of point in pixel
		return point;
	}

	/**
	 * This function transform a pixel point to coordinate point
	 * @param point the point in pixles
	 * @return the point in coords
	 */
	public Point3D pixels2coords(Point3D point) {
		MyCoords coords = new MyCoords();
		double leftRight = this.imgWidth/coords.distance3d(leftTop, rightTop);
		double topBottom = this.imgHeight/coords.distance3d(rightTop, rightBottom);
		double x=point.y()/(topBottom*(-1));// x value of the point in coords
		double y=point.x()/leftRight; // y value of the point in coords
		point=new Point3D(x,y); // the new point in meters
		point=coords.add(leftTop, point); // transform the start point by point
		return point;
	}

	/**
	 *  
	 * @param p1 point in pixles
	 * @param p2 point in pixles
	 * @return the distance between two pixels point(in meters)
	 */
	public double distBetPixels(Point3D p1,Point3D p2) {
		p1=pixels2coords(p1);//convert p1 to gps point
		p2=pixels2coords(p2);//convaertp2 to gps point
		MyCoords coords=new MyCoords();
		double distance= coords.distance3d(p1, p2);// get the distance in meters of the gps point
		return distance;
	}

	/**
	 *  
	 * @param p1 point in pixles
	 * @param p2 point in pixles
	 * @return the azimuth between two pixels points on the map
	 */
	public double AzimuthBetPixels(Point3D p1,Point3D p2) { 
		p1=pixels2coords(p1); //converts p1 to gps point
		p2=pixels2coords(p2); //converts p2 to gps point 
		MyCoords coords=new MyCoords();
		double[] arr= coords.azimuth_elevation_dist(p1, p2);//get a new arr with the azimuth elevation distance
		double azimuth=arr[0]; // get the azimuth value of the arr
		return azimuth;
	}
}

