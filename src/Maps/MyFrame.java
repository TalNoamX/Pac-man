package Maps;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.*;

import Algorithms.PathNode;
import Algorithms.ShortestPathAlgo;
import GameData.Fruit;
import GameData.Game;
import GameData.Pacman;
import Geom.Point3D;

public class MyFrame extends JFrame implements MouseListener,ComponentListener {

	private static final long serialVersionUID = 1L;


	private BufferedImage myImage;
	private Map map;
	private BufferedImage pacmanImg;
	private BufferedImage FruitImg;
	private MenuItem loadCSV;
	private MenuItem run;
	private MenuItem saveKml;
	private MenuItem pacman;
	private MenuItem fruit;
	private MenuItem clear;
	//will tell us whether to add a pacman or a fruit when pressed
	private boolean fruitButton = false;
	private boolean pacmanButton = false;
	private int fruitID;
	private int pacmanID;
	private Game game;
	private ShortestPathAlgo SPA;
	Dimension fSize; //The frame size
	double heighty;
	double widthx;
	int imgheight;
	int imgwidth;

	/**
	 * constructor 
	 * @param path The Images Path
	 * @param top The
	 * @param bottom
	 */
	public MyFrame(String path, Point3D leftTop, Point3D rightBottom, Point3D rightTop) {
		map = new Map(path, leftTop, rightBottom, rightTop);//Setting a new map
		myImage = map.getmyImage();// setting the map image
		setMenu();	//setting the menu bar with all the options
		imgwidth=myImage.getWidth();
		imgheight=myImage.getHeight();
		//adding the image to the frame
		JLabel label1 = new JLabel(new ImageIcon(myImage));
		add(label1);
		game = new Game();
		setActionListeners();//adding action listeners to the menu Items
		setPacFruImg();//setting the image of the pacman and the fruit
		this.addMouseListener(this); //adding mouselisteners
		this.addComponentListener(this);
		fSize = this.getSize();
		pacmanID=0;
		fruitID=0;

	}

	/**
	 * Setting the Menu Items - 
	 * first will be "file" the contain run, load CSV, and save as kml.
	 * Second will be "draw images" that will make it possible to make game by drawing pacmans and fruits on the map
	 */
	private void setMenu() {
		MenuBar menuBar = new MenuBar();
		Menu menu1 = new Menu("File");  
		loadCSV = new MenuItem("Load csv");
		run = new MenuItem("Run");
		saveKml = new MenuItem("Save as kml");
		menu1.add(loadCSV);
		menu1.add(run);
		menu1.add(saveKml);
		Menu menu2 = new Menu("Draw Images");  
		pacman = new MenuItem("Pacman");
		fruit = new MenuItem("Fruit");
		clear = new MenuItem("clear");
		menu2.add(pacman);
		menu2.add(fruit);
		menu2.add(clear);
		menuBar.add(menu1);
		menuBar.add(menu2);
		this.setMenuBar(menuBar);
	}

	/**
	 * setting the Icons for pacmans and fruits.
	 */
	public void setPacFruImg() {
		try {
			File file = new File("Pacman.jpg");
			pacmanImg = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			File file = new File("strawberry-icon.jpg");
			FruitImg = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Setting all the needed ACtionListeners For the menuItem.
	 */
	public void setActionListeners() {
		loadCSV.addActionListener(new ActionListener() {	//Loading CSV game files usinf FileChooser 
			public void actionPerformed(ActionEvent e) {
				JButton open = new JButton();
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new java.io.File("þþDocuments"));
				fileChooser.setDialogTitle("Load CSV");
				if(fileChooser.showOpenDialog(open)==JFileChooser.APPROVE_OPTION) {

				}
				if(fileChooser.getSelectedFile().getAbsolutePath().endsWith(".csv")) {
					game.csvToGame(fileChooser.getSelectedFile().getAbsolutePath());
					//					game2=new Game(game);
					//setPoints2Pixel();//Changes all the points coords to mach the map.
					repaint();
				}
				else JOptionPane.showMessageDialog(null, "Not a CSV file, Please try again");
			}

		});
		run.addActionListener(new ActionListener() {	//Starting The Game.
			public void actionPerformed(ActionEvent e) {
				//setPixel2Points();
				SPA = new ShortestPathAlgo(game,MyFrame.this);
				SPA.Start();
				
			}
		});
		saveKml.addActionListener(new ActionListener() {	//Saving The game Data as kml.
			public void actionPerformed(ActionEvent e) {

			}
		});
		pacman.addActionListener(new ActionListener() {	//Drawing pacmans.
			public void actionPerformed(ActionEvent e) {
				if(fruitButton) fruitButton = false;//In case we drew fruits before
				if(pacmanButton) pacmanButton = false;//pressing twice on the button will cancel the drawing
				pacmanButton=true;
			}
		});

		fruit.addActionListener(new ActionListener() {	//Drawing fruits
			public void actionPerformed(ActionEvent e) {
				if (pacmanButton) pacmanButton = false;//in case we drew pacmans before
				if(fruitButton) fruitButton = false; //pressing twice on the button will cancel the drawing
				fruitButton=true;
			}
		});
		clear.addActionListener(new ActionListener() {	//Clearing The screen from the Images
			public void actionPerformed(ActionEvent e) {
				game.pList().clear();
				game.fList().clear();
				repaint();
			}
		});
	}

	/**
	 * Setting the points we got from a csv file to match the map
	 */
	//	public synchronized void setPoints2Pixel() {
	//		for(Pacman p: game.pList()) {
	//		//	Pacman temp=new Pacman(p);
	//			p.setPoint(map.coords2pixels(p.getPoint()));
	//		}
	//		for(Fruit f: game.fList()) {
	//		//	Fruit temp=new Fruit(f);
	//			f.setPoint(map.coords2pixels(f.getPoint()));
	//		}
	//	}
	public synchronized void setPixel2Points() {
		for(Pacman p: game.pList()) {
			p.setPoint(map.pixels2coords(p.getPoint()));
		}
		for(Fruit f: game.fList()) {
			f.setPoint(map.pixels2coords(f.getPoint()));
		}
	}
	public Point3D setPixelPoint(Point3D point) {
		point=map.coords2pixels(point);
		return point;
	}
	public Point3D setCoordsPoint(Point3D point) {

		point=map.pixels2coords(point);
		return point;
	}
	/**
	 * Drawing the maps image, and painting the pacman and the fruits.
	 */
	public  void paint(Graphics g) {
		g.drawImage(myImage, 8,53, this.getWidth()-16,this.getHeight()-61,this);//Drawing the map image
		if(game.pList()!=null) {//if pacman list is not null enter
			Iterator<Pacman> itP=game.pList().iterator();// iterator for pacman
			if(game.fList()!=null) {
				Iterator<Fruit> itF=game.fList().iterator(); // iterator for fruit
				while(itP.hasNext()) {//Drawing the Pcamans.
					Pacman temp=itP.next();
					Point3D pixelPoint = map.coords2pixels(temp.getPoint());
					int x = (int)(pixelPoint.x()*(widthx/imgwidth));//convert the point to pixel depends on the img size
					int y = (int)(pixelPoint.y()*(heighty/imgheight));
					g.drawImage(pacmanImg, x, y, pacmanImg.getWidth(), pacmanImg.getHeight(), this);
				}

				while(itF.hasNext()) {//Drawing the Fruits.
					Fruit temp = itF.next();
					Point3D pixelPoint = map.coords2pixels(temp.getPoint());
					int x = (int)(pixelPoint.x()*(widthx/imgwidth));
					int y = (int)(pixelPoint.y()*(heighty/imgheight));
					g.drawImage(FruitImg, x, y, FruitImg.getWidth(), FruitImg.getHeight(), this);
				}
				
				for(int j=0;j<game.pList().size();j++) {//drawing the line path of the pacman 
					Pacman pac=game.pList().get(j);
					if(pac.getPath()!=null) {//if the path of the pacman is not null enter
						for(int i=0;i<pac.getPath().size();i++) {
							if(i==0) {//if i==0 than draw the line between the pacman and his first fruit
								Point3D pacpoint=new Point3D(map.coords2pixels(pac.getPath().getStartLocation()));//get the pacman point and convert it to pixel
								Point3D frupoint=new Point3D (map.coords2pixels(pac.getPath().get(i).getFruit().getPoint()));//the same with the first fruit
								int xpac = (int)(pacpoint.x()*(widthx/imgwidth));//change the point depends on the image size
								int ypac = (int)(pacpoint.y()*(heighty/imgheight));
								int xfruit = (int)(frupoint.x()*(widthx/imgwidth));
								int yfruit = (int)(frupoint.y()*(heighty/imgheight));
								g.setColor(Color.blue);
								g.drawLine(xpac, ypac, xfruit, yfruit);//draw the line
							}
							else {//else draw line between this fruit  and the previus one
								Point3D prevfru=new Point3D (map.coords2pixels(pac.getPath().get(i-1).getFruit().getPoint()));
								Point3D curfru=new Point3D (map.coords2pixels(pac.getPath().get(i).getFruit().getPoint()));
								int xprev = (int)(prevfru.x()*(widthx/imgwidth));
								int yprev = (int)(prevfru.y()*(heighty/imgheight));
								int xcur = (int)(curfru.x()*(widthx/imgwidth));
								int ycur = (int)(curfru.y()*(heighty/imgheight));
								g.setColor(Color.blue);
								g.drawLine(xprev, yprev, xcur,ycur);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {//get pacman and fruit with mouse click
		int x=(int)(e.getX()/(widthx/imgwidth));//derivative the coords with imag size so when we multiply it in paint it will be where it shuld be
		int y=(int)(e.getY()/(heighty/imgheight));
		Point3D p=new Point3D(x, y);

		Point3D gpsPoint = map.pixels2coords(p);//get the point as coords

		if(fruitButton) {//if true than we add a fruit
			game.fList().add(new Fruit(++fruitID, gpsPoint.x(), gpsPoint.y(), gpsPoint.z(), 1));//The default fruit weight is 1
		}
		if(pacmanButton) {//if true than we add a pacman
			game.pList().add(new Pacman(++pacmanID, gpsPoint.x(),gpsPoint.y(), gpsPoint.z(), 1, 1));//The default radius and speed is 1
		}
		repaint();
	}
	public void componentResized(ComponentEvent e) {

		widthx=e.getComponent().getWidth();
		heighty=e.getComponent().getHeight();

		repaint();
	}
	public void winner() {
		Iterator<Pacman> itP=game.pList().iterator();
		Pacman pac=new Pacman(game.pList().get(0));
		while(itP.hasNext()) {
			Pacman temp=itP.next();
			JOptionPane.showMessageDialog(null, "Pacman "+pac.getID()+" with score of"+temp.getScore());
		}
		
	}

	//Unneeded functions:
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void componentHidden(ComponentEvent arg0) {}
	public void componentMoved(ComponentEvent arg0) {}
	public void componentShown(ComponentEvent arg0) {}

	public static void main(String[] args) {
		MyFrame window = new MyFrame("Ariel1.png", new Point3D(32.10566,35.20238), new Point3D(32.10191,35.21237),new Point3D(32.10566,35.21241));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.pack();
	}
}
