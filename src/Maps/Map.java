package Maps;

import java.awt.MouseInfo;

import javax.swing.JFrame;
public class Map {

	public static void main(String[] args)
	{
		
		MyFrame window = new MyFrame();
		window.setVisible(true);
		int wei=window.myImage.getWidth();
		int hai=window.myImage.getHeight();
		window.setSize(wei,hai);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}

//}
