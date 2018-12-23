package Maps;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import Coords.MyCoords;
import Geom.Point3D;
/**
 * Test class to map's functions:
 * coords2pixels
 * pixels2coordsTest
 * distBetPixelsTest
 * AzimuthBetPixelsTest
 * @author Oranit
 *
 */
class mapJUnit {
	Point3D leftTop = new Point3D(32.10566,35.20238);
	Point3D rightBottom = new Point3D(32.10191,35.21237);
	Point3D rightTop = new Point3D(32.10566,35.21241);
	Map map = new Map("Ariel1.png",leftTop ,rightBottom,rightTop);
	MyCoords mC = new MyCoords();
	@Test
	void coords2pixelsTest() {
		Point3D coord = new Point3D(32.102495, 35.207480);
		Point3D pixle = new Point3D(731,542);
		Point3D res = map.coords2pixels(coord);
		System.out.println(res);
		Assert.assertTrue(pixle.close2equals(res,5));
	}
	
	@Test
	void pixels2coordsTest() {
		Point3D coord = new Point3D(32.102495, 35.207480);
		Point3D pixle = new Point3D(731,542);
		Point3D res = map.pixels2coords(pixle);
		Assert.assertTrue(res.close2equals(coord,0.0001));
	}
	
	@Test
	void distBetPixelsTest() {//416.9979965382117	
		double realDistance = map.distBetPixels(new Point3D(0,0), new Point3D(1433,642));
		double ExpectedDistance= mC.distance3d(rightBottom, leftTop);
		Assert.assertEquals(realDistance,ExpectedDistance,5);
		ExpectedDistance = mC.distance3d(rightBottom, rightTop);
		realDistance = map.distBetPixels(new Point3D(1433,0), new Point3D(1433,642));
		Assert.assertEquals(realDistance,ExpectedDistance,0.00001);
	}
	
	@Test
	void  AzimuthBetPixelsTest() {
		 double[] azElDi = mC.azimuth_elevation_dist(rightBottom, new Point3D(32.102495, 35.207480));
		 double aziPix= azElDi[0];
		 double aziCoords = map.AzimuthBetPixels(new Point3D(1433,642),new Point3D(729,542));
	     Assert.assertEquals(aziPix,aziCoords,0.1);
	}
	

}
