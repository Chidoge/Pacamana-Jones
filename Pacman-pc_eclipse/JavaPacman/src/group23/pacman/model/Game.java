package group23.pacman.model;

import java.io.File;
import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
	This is the class that handles all the game logics - collisions, level handling, and creation of the map.
 */
public class Game {
	
	/* Each game has a main character */
	private Pacman pacman;
	
	/* Currently only one ghost, intend to change this to arraylist later */
	private Ghost ghost;
	
	/* Media variables for sound effects */
	private Media chompNoise;
	private MediaPlayer mediaPlayer;
	
	/* ArrayList to access other game objects */
	private ArrayList<GameObject> objects;
	
	/* Board to determine valid coordinates and movements */
	private Board board;
	
	/* Keeps track of user's game progress */
	private int score;
	
	/* Each game has a unique map */
	private char map;
	
	/* Game has array list of moving objects */
	private ArrayList<MovingCharacter> characters;
	
	public Game(char map) {
		
		this.map = map;
		
		/* Create new board (with user selected map) to define valid coordinates */
		board = new Board();
		board.createBoard(map);
		
		/* Get reference to objects created on the board */
		objects = board.getObjects();
		
		/* Add character objects to ArrayList of MovingCharacter interface */
		characters = new ArrayList<MovingCharacter>();
		pacman = new Pacman(board.getPacman()[0],board.getPacman()[1]);
		ghost = new Ghost(board.getGhost()[0],board.getGhost()[1]);
		characters.add(pacman);
		characters.add(ghost);
		
		chompNoise = new Media(new File("bin/assets/sfx/chompNoise.mp3").toURI().toString());
		
		score = 0;
	
	}
	
	
	public void update( ) {
		
		checkCollisions();
		pacman.update();
		ghost.update();	
	}
	
	/* Checks character movement collisions and player pellet collisions */
	private void checkCollisions() {
		
		ghost.queueMovement();
		
		for (MovingCharacter character : characters) {
			
			/* If the currently queued direction is not equal to the current direction we are moving in, and it is possible 
			   for us to turn in our current (x,y) position, test if turn is valid (not into a wall), then set the queued direction
			   if valid. */
			if (character.checkforQueuedAction() && board.validTurningPoint((int) character.getX(), (int) character.getY())) {
				if (board.isValidDestination(character.getQDirection(), (int) character.getX(), (int) character.getY())){
					character.setDirection(character.getQDirection());
					character.updateDestination();
					return;
				}
			}
			if (board.isValidDestination(character.getDirection(), (int) character.getX(), (int) character.getY())) {
				character.updateDestination();
			}
			
		}
		
		
	
		/* Loops through the game objects to check if the player has collided with a pellet. Pellet is removed on collision */
		for (GameObject object : objects) {

			if (pacman.collidedWith(object)) {
				if (object.getType() == GameObject.TYPE.PELLET) {
					playSfx(chompNoise);
					objects.remove(object);
					score++;
					break;
				}
		
			}
		}
	}
	
	
	/* Public getter to reference pacman object */
	public Pacman getPacman() {
		
		return this.pacman;
	}
	
	/* Public getter to reference ghost object(s) */
	public Ghost getGhost() {
		
		return this.ghost;
	}
	
	/* Public getter to reference other game objects (i.e walls, pellets ) */
	public ArrayList<GameObject> getOtherGameObjects() {
		
		return this.objects;
	}
	
	/* Public getter to reference map type */
	public char getMap() {
		
		return this.map;
	}	

	
	/* Plays pacman munching sound effect */
	public void playSfx(Media sfx) {
		mediaPlayer = new MediaPlayer(sfx);
		mediaPlayer.setVolume(0.3);
		mediaPlayer.play();
	}
	
	/* Returns the user's score in string format */
	public String getScore() {
		
		String tempScore = Integer.toString(this.score);
		tempScore = new StringBuilder(tempScore).reverse().toString();
        while (tempScore.length() < 4){
           	tempScore = tempScore + "x";
        }
        return tempScore;
	}


}
