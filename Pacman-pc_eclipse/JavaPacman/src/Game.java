import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class Game {
	
	private Pacman pacman;
	private Ghost ghost;
	
	private ArrayList<Pellet> pellets;
	private ArrayList<GameObject> objects;
	
	
	public Game() {
		
		pacman = new Pacman(30,334);
		ghost = new Ghost(300,334);
		pellets = new ArrayList<Pellet>();
		objects = new ArrayList<GameObject>();
		
		Pellet pellet;
		for (int i=0;i<300;i+=30) {
			for (int j=0;j<300;j+=30) {
				pellet = new Pellet(i,j);
				pellets.add(pellet);
				objects.add(pellet);
			}
		}
		
	}
	
	public void update() {

		pacman.update();
		checkCollisions();
	}
	
	private void checkCollisions() {
		
		for (GameObject object : objects) {
		
			if (pacman.collidedWith(object)) {
				if (object.getType()==1) {
					object.setInvisible();
				}
			}

		}
	}
	
	

	public Pacman getPacman() {
		
		return this.pacman;
	}
	
	public Ghost getGhost() {
		
		return this.ghost;
	}
	
	public void drawPellets(GraphicsContext graphicsContext) {
		
		for (Pellet p : pellets) {
			
			p.draw(graphicsContext);
		}
	}

}