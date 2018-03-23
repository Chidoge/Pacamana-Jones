package group23.pacman.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Board {
	
	private static final int TILE_SIZE = 10;
	private static final int OFFSET = 1;
	
	
	private Game game;
	private ArrayList<GameObject> objects;
	
	private boolean[][] status;
	private int[] ghostCoords;
	private int[] pacmanCoords;
	private char map;

	public Board() {
		
		/* Create the list and arrays of objects/states to be placed on the map */
		pacmanCoords = new int[2];
		ghostCoords = new int[2];
		status = new boolean[101][71];
		objects = new ArrayList<GameObject>();
	}
	
	public void createBoard() {
		
		map = game.getMap();
		String line,mapTxt;
		
		/* Parse the map.txt file, loads the map into the game */
		try {
			switch (map) {
				case 's' :
					mapTxt = "mapOne.txt";
					break;
				case 'd' :
					mapTxt = "mapOne.txt";
					break;
				default :
					mapTxt = "mapOne.txt";
					break;
				
			}
			
			FileReader fileReader = new FileReader(mapTxt);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int row = 0;
			int position;
			/* Creates objects on the map based on their value in the text file
			 * 0 creates a wall
			 * P creates a pellet
			 * 1 is an empty position
			 * R is a position that the character can be in but cannot turn *
			 * S is the spawn point of the main character
			 * G is the spawn point of a ghost
			 */
			while ((line = bufferedReader.readLine()) != null ) {
				position = 0;
				for (int i =0;i< line.length();i++) {
					if (line.charAt(i)==('0')) {
						Rectangle rect = new Rectangle();
						rect.setX(position*TILE_SIZE + 33);
						rect.setY(row*TILE_SIZE + 34);
						rect.setWidth(TILE_SIZE);
						rect.setHeight(TILE_SIZE);
						Wall wall = new Wall(rect,map);
						status[position][row] = false;
						objects.add(wall);
						position++;
					}
					else if (line.charAt(i) == 'P') {
						Pellet pellet = new Pellet(position*TILE_SIZE + 33,row*TILE_SIZE + 34);
						objects.add(pellet);
						status[position][row] = false;
						position++;
					}
					else if (line.charAt(i) == '1' ) {
						status[position][row] = false;
						position++;
					}
					else if (line.charAt(i) == 'R' ) {
						status[position][row] = true;
						position++;
					}
					else if (line.charAt(i) == 'S'){
						pacmanCoords[0] = (position-2)*TILE_SIZE + 33;
						pacmanCoords[1] = (row-2)*TILE_SIZE + 34;
						position++;
					}
					else if (line.charAt(i) == 'G'){
						ghostCoords[0] = (position-2)*TILE_SIZE + 33;
						ghostCoords[1] = (row-2)*TILE_SIZE + 34;
						position++;
					}
				
				}
							
				
				row++;
			}
			bufferedReader.close();
		} 
		
		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file ");
		}

		catch (IOException ex) {
			System.out.println("Error reading file ");
		}
	}
	
	/* Public setter to reference the game object */
	public void setGame(Game game) {
		
		this.game = game;
	}
	
	/* Passes objects back to the game class - to check for collisions */
	public ArrayList<GameObject> getObjects() {
		
		return this.objects;
	}
	
	/* Checks if Player is in the exact x,y position to do a 90 degree turn */
	public boolean validTurningPoint(int x, int y) {
		
		if (((x - 33)%10 == 0) && ((y - 34)%10 == 0)){
			return this.status[(x - 33)/10][(y - 34)/10 ];
		}
		else {
			return false;
		}
	}
	
	/* Checks if a location is valid when the character is moving in a certain direction */
	public boolean isValidDestination(char direction, int x, int y) {
		
    	switch (direction) {
			case 'U':
				return this.status[(x - 33)/10][(y - OFFSET - 34)/10];
			case 'D':
				return this.status[(x - 33)/10][(y + TILE_SIZE - 34)/10];
			case 'L':
				return this.status[(x - OFFSET - 33)/10][(y - 34)/10];
			case 'R':
				return this.status[(x + TILE_SIZE - 33)/10][(y - 34)/10];
    	}
    	return false;
    }
	
	public int[] getPacman() {
		
		return pacmanCoords;
	}
	public int[] getGhost() {
		
		return ghostCoords;
	}
	
}
