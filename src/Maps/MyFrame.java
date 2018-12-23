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
import File_format.Path2KML;
import GameData.Fruit;
import GameData.Game;
import GameData.Pacman;
import Geom.Point3D;

/**
 * A GUI class that presents the Game.
 * @author Oranit
 * @author Amitay
 * @author Tal
 *
 */
public class MyFrame extends JFrame implements MouseListener,ComponentListener {

	private static final long serialVersionUID = 1L;
	private BufferedImage myImage; //The image
	private Map map; 
	private BufferedImage pacmanImg; //An Icon for the pacman
	private BufferedImage FruitImg;// An Icon for the Fruit
	//Menu Items
	private MenuItem loadCSV;
	private MenuItem run;
	private MenuItem saveKml;
	private MenuItem pacman;
	private MenuItem fruit;
	private MenuItem clear;
	//will tell us whether to add a pacman or a fruit when pressed
	private boolean fruitButton = false;
	private boolean pacmanButton = false;
	//ID for fruit and pamans in case the user is drawing it on the screen
	private int fruitID; 
	private int pacmanID;
	private Game game;
	private ShortestPathAlgo SPA;
	double height;
	double width;
	int imgheight;// The map image height
	int imgwidth;// The map image width

	/**
	 * 
	 * @param path - The image's path.
	 * @param leftTop - left top corner of the map.
	 * @param rightBottom - right bottom corner of the map.
	 * @param rightTop - right top corner of the map.
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
					repaint();
				}
				else JOptionPane.showMessageDialog(null, "Not a CSV file, Please try again");
			}

		});
		run.addActionListener(new ActionListener() {	//Starting The Game.
			public void actionPerformed(ActionEvent e) {
				if(game.pList().size()==0||game.fList().size()==0) {
					JOptionPane.showMessageDialog(null, "The game is null. try again");
					return;
				}
				SPA = new ShortestPathAlgo(game,MyFrame.this);
				SPA.Start();
			}
		});
		saveKml.addActionListener(new ActionListener() {	//Saving The game Data as kml.
			public void actionPerformed(ActionEvent e) {
				if(game.pList().size()==0) {
					JOptionPane.showMessageDialog(null, "The game is null. try again");
					return;
				}
				try {
					Path2KML p2k = new Path2KML(game);
					String name = JOptionPane.showInputDialog("Enter a name for the KML", null);
					p2k.kmlWriter(name);
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "The game is null or not finished yet. try again");
				}
			}
		});
		pacman.addActionListener(new ActionListener() {	//Drawing pacmans.
			public void actionPerformed(ActionEvent e) {
				if(fruitButton) fruitButton = false;//In case we drew fruits before
				pacmanButton=true;
			}
		});

		fruit.addActionListener(new ActionListener() {	//Drawing fruits
			public void actionPerformed(ActionEvent e) {
				if (pacmanButton) pacmanButton = false;//in case we drew pacmans before
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
	 *  Drawing the maps image, and painting the pacman and the fruits.
	 *  @param g Graphics object that will draw everything we need.
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
					int x = (int)(pixelPoint.x()*(width/imgwidth));//convert the point to pixel depends on the img size
					int y = (int)(pixelPoint.y()*(height/imgheight));
					g.drawImage(pacmanImg, x, y, pacmanImg.getWidth(), pacmanImg.getHeight(), this);
				}

				while(itF.hasNext()) {//Drawing the Fruits.
					Fruit temp = itF.next();
					Point3D pixelPoint = map.coords2pixels(temp.getPoint());
					int x = (int)(pixelPoint.x()*(width/imgwidth));
					int y = (int)(pixelPoint.y()*(height/imgheight));
					g.drawImage(FruitImg, x, y, FruitImg.getWidth(), FruitImg.getHeight(), this);
				}

				for(int j=0;j<game.pList().size();j++) {//drawing the line path of the pacman 
					Pacman pac=game.pList().get(j);
					if(pac.getPath()!=null) {//if the path of the pacman is not null enter
						for(int i=0;i<pac.getPath().size();i++) {
							if(i==0) {//if i==0 than draw the line between the pacman and his first fruit
								Point3D pacpoint=new Point3D(map.coords2pixels(pac.getPath().getStartLocation()));//get the pacman point and convert it to pixel
								Point3D frupoint=new Point3D (map.coords2pixels(pac.getPath().get(i).getFruit().getPoint()));//the same with the first fruit
								int xpac = (int)(pacpoint.x()*(width/imgwidth));//change the point depends on the image size
								int ypac = (int)(pacpoint.y()*(height/imgheight));
								int xfruit = (int)(frupoint.x()*(width/imgwidth));
								int yfruit = (int)(frupoint.y()*(height/imgheight));
								g.setColor(Color.blue);
								g.drawLine(xpac, ypac, xfruit, yfruit);//draw the line
							}
							else {//else draw line between this fruit  and the previus one
								Point3D prevfru=new Point3D (map.coords2pixels(pac.getPath().get(i-1).getFruit().getPoint()));
								Point3D curfru=new Point3D (map.coords2pixels(pac.getPath().get(i).getFruit().getPoint()));
								int xprev = (int)(prevfru.x()*(width/imgwidth));
								int yprev = (int)(prevfru.y()*(height/imgheight));
								int xcur = (int)(curfru.x()*(width/imgwidth));
								int ycur = (int)(curfru.y()*(height/imgheight));
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
		int x=(int)(e.getX()/(width/imgwidth));//derivative the coords with imag size so when we multiply it in paint it will be where it shuld be
		int y=(int)(e.getY()/(height/imgheight));
		Point3D p = new Point3D(x, y);
		Point3D gpsPoint = map.pixels2coords(p);//get the point as coords
		if(fruitButton) {//if true than we add a fruit
			Fruit f = new Fruit(++fruitID, gpsPoint.x(), gpsPoint.y(), gpsPoint.z(), 1);
			game.fList().add(f);//The default fruit weight is 1
		}
		if(pacmanButton) {//if true than we add a pacman
			Pacman pacman = new Pacman(++pacmanID, gpsPoint.x(),gpsPoint.y(), gpsPoint.z(), 1, 1);
			game.pList().add(pacman);//The default radius and speed is 1
		}
		repaint();
	}
	public void componentResized(ComponentEvent e) {

		width=e.getComponent().getWidth();
		height=e.getComponent().getHeight();

		repaint();
	}

	/**
	 * Prints the results of the game, the algorithm already know who's winning so it prints in the middle of the game.
	 */
	public void results() {
		Iterator<Pacman> itP=game.pList().iterator();//An iterator for the pacmans.
		StringBuilder score = new StringBuilder();//StringBuilder that will present the score.
		while(itP.hasNext()) {
			Pacman temp=itP.next();
			Iterator<PathNode> itNode=temp.getPath().iterator();// An iterator for each pacman's pathNode.
			while(itNode.hasNext()) {
				PathNode node=itNode.next();
				temp.addScore(node.getFruit().getWeight());
			}
			score.append("Pacman "+temp.getID()+" score"+" "+temp.getScore()+"\n");//appending the score of each pacmans
		}
		JOptionPane.showMessageDialog(null, score.toString());//showing the score on the screen.

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
