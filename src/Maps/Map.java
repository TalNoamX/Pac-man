package Maps;

import java.awt.MouseInfo;

import javax.swing.JFrame;
public class Map {
	
	public static void main(String[] args)
	{
		MyFrame window = new MyFrame();
		window.setVisible(true);
		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ForMouseOnly e=new ForMouseOnly();
		e.main(args);
		//while(true) {
			//System.out.println("(" + MouseInfo.getPointerInfo().getLocation().x + 
		      //        ", " + 
		        //      MouseInfo.getPointerInfo().getLocation().y + ")");
		}
	}

//}
