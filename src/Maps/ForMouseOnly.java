package Maps;

import java.awt.MouseInfo;

public class ForMouseOnly {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		   int x = MouseInfo.getPointerInfo().getLocation().x;
	        int y = MouseInfo.getPointerInfo().getLocation().y;
	        while (true) {

	            if (x != MouseInfo.getPointerInfo().getLocation().x || y != MouseInfo.getPointerInfo().getLocation().y) {
	                System.out.println("(" + MouseInfo.getPointerInfo().getLocation().x + ", "
	                        + MouseInfo.getPointerInfo().getLocation().y + ")");
	                x = MouseInfo.getPointerInfo().getLocation().x;
	                y = MouseInfo.getPointerInfo().getLocation().y;
	            }
	        }
	    }
	
	}


