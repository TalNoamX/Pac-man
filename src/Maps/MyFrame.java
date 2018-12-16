package Maps;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Geom.Point3D;

public class MyFrame extends JFrame implements MouseListener,ComponentListener {

	private static final long serialVersionUID = 1L;

	private BufferedImage myImage;
	private Map map;
	private ArrayList<Point3D> coords;
	int x = -1;
	int y = -1;

	public MyFrame(String path, Point3D top, Point3D bottom) {
		map = new Map(path, top, bottom);
		myImage = map.getmyImage();
		initGUI();		
		this.addMouseListener(this); 
		this.addComponentListener(this);
		coords = new ArrayList<Point3D>();	
	}

	private void initGUI() {
		MenuBar menuBar = new MenuBar();
		Menu menu1 = new Menu("File");  
		MenuItem item1 = new MenuItem("Load csv");
		MenuItem item2 = new MenuItem("Run");
		menu1.add(item1);
		menu1.add(item2);
		menuBar.add(menu1);
		this.setMenuBar(menuBar);
		JLabel label = new JLabel(new ImageIcon(myImage));
		add(label);
	}

	public void paint(Graphics g) {
		g.drawImage(myImage, 0, 0, this.getWidth()-10,this.getHeight()-10,this);
		Iterator<Point3D> it=coords.iterator();
		while(it.hasNext()) {
			Point3D temp=it.next();
			if(temp.x()!=-1 && temp.y()!=-1) {
				x=(int)temp.x();
				y=(int)temp.y();
				int r = 10;
				x = x - (r / 2);
				y = y - (r / 2);
				g.fillOval(x, y, r, r);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		x=e.getX();
		y=e.getY();
		Point3D p=new Point3D(x, y);
		coords.add(p);
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX()+" "+e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) {
		Point3D rightTop = new Point3D(32.10555556,35.21222222,0);
		Point3D leftBottom = new Point3D(32.10166667,35.20222222,0);
		Point3D rightBottom = new Point3D(32.10166667,35.21222222,0);
		Point3D leftTop = new Point3D(32.103315,35.209039);
		Point3D pixStart = new Point3D(1433,642,0);
		Map map = new Map("Ariel1.png",rightTop,leftBottom);
		System.out.println("pix to deg: " + map.pixTodeg(new Point3D (852,126)));
		System.out.println("deg to pix: " + map.degToPix(leftBottom));
		System.out.println("deg to pix: " + map.degToPix(rightTop));
		System.out.println("deg to pix: " + map.degToPix(rightBottom));
		System.out.println("deg to pix111: " + map.degToPix(leftTop));



	}
}














//MyFrame window = new MyFrame("Ariel1.png", rightTop, leftBottom);
//window.setPreferredSize(new Dimension(window.myImage.getWidth()+100, window.myImage.getHeight()+100));
//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//window.setVisible(true);
//window.pack();