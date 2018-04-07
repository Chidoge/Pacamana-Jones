package group23.pacman.model;

import group23.pacman.view.Animation;
import group23.pacman.view.AnimationManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/** The class which handles the Ghost object, the antagonist(s) of the main character in this game
 */
public class Ghost extends GameObject implements MovingCharacter {
	
	
	public enum STATE {
		DEAD,
		ALIVE
	}
	
	/* Constants */
	private static final int SPRITE_HEIGHT = 30;
	private static final int SPRITE_WIDTH = 30;
	private static final int OFFSET = 10;
	
	/* Pixels moved per update */
	private static final int SPEED = 2;
	
	/* Handles character animations */
	private AnimationManager animationManager;
	
	/* AI for ghost, and the type of AI*/
	private AI ai;
	private boolean isAI;
	
	/* Direction to move and planned direction */
	private char vector;
	private char queuedDirection;
	
	private STATE state;
	
	/* Condition for moving in the ghost spawn point */
	private boolean hasLeftSpawn;
	
	/* Ghost position */
	private int x;
	private int y;
	
	
	public Ghost(int x,int y,Board board, int type) {
		
		setUpAnimations();

		
		hitBox = new Rectangle();
		this.hitBox.setX(x + OFFSET/2);
		this.hitBox.setY(y + OFFSET/2);
		this.hitBox.setHeight(SPRITE_HEIGHT - OFFSET);
		this.hitBox.setWidth(SPRITE_WIDTH - OFFSET);
		this.type = GameObject.TYPE.GHOST;
		this.state = Ghost.STATE.ALIVE;
		
		this.x = x;
		this.y = y;
		
		this.vector = 'S';
		this.queuedDirection = 'S';
		
		hasLeftSpawn = false;
		
		/* A Ghost can be player controlled or computer controlled.
		 * 0 is a player, and so no AI is created.
		 * 1 is a random movement type AI. 
		 * 2 is an AI which prioritizes shortening the distance between the ghost and pacman. */
		if (type != 0) {
			ai = new AI(board, type);
			isAI = true;
		}
		else {
			isAI = false;
		}
	}
	
	
	public void update(int pacmanX, int pacmanY, char direction) {
		
		/* If this character is meant to be an AI, generate movement using the AI object created in this class */
		if (isAI) {
			if (ai.canTurn((int)getX(), (int)getY())) {
				queueMovement(ai.chooseMovement(hasLeftSpawn, vector, (int)getX(), (int)getY(), pacmanX, pacmanY, direction));
			}
		}
		
		/* Play the animation for this character */
		animationManager.update();
		animationManager.playAction(0);
		
	}
	
	public void queueMovement(char queuedDirection) {
		
		this.queuedDirection = queuedDirection;
	}

    
    public void setDirection(char vector) {
    	
    	this.vector = vector;	
    }
    
    public void setState(STATE state) {
    	
    	this.state = state;
    }
    
    public void setHasLeftSpawn() {
    	this.hasLeftSpawn = true;
    }
    
    public char getDirection() {
    	
    	return this.vector;
    }
    
    public char getQDirection() {
    	
    	return this.queuedDirection;
    }
   
    
    /* Determines if the ghost has left the spawn point */
    public boolean getHasLeftSpawn() {
    	return this.hasLeftSpawn;
    }
    
    
    /* Determines if the current direction is the same as the queued direction */
    public boolean checkforQueuedAction() {
		
	    return (queuedDirection != vector);
    }
    
    public Rectangle getHitBox() {
    	return this.hitBox;
    }
    
    /* Determines if the current direction is the opposite direction of the queued direction */
    public boolean oppositeDirection() {
    	
    	switch (vector) {
    		case 'S':
    			return true;
			case 'U':
				if (queuedDirection == 'D') {
					return true;
				}
			case 'D':
				if (queuedDirection == 'U') {
					return true;
				}
			case 'L':
				if (queuedDirection == 'R') {
					return true;
				}
			case 'R':
				if (queuedDirection == 'L') {
					return true;
				}
    	}
    	return false;
    }
     
    
    /* Updates (x,y) coordinates of character */
    public void updateDestination() {
    
    		
    	if (this.vector == 'U') {
			this.hitBox.setY((int)hitBox.getY() - SPEED);
			this.y = y - SPEED;
		}
		else if (this.vector == 'D') {
			this.hitBox.setY((int)hitBox.getY() + SPEED);
			this.y = y + SPEED;
		}
		else if (this.vector == 'L') {
			this.hitBox.setX((int)hitBox.getX() - SPEED);
			this.x = x - SPEED;
		}
		else if (this.vector == 'R') {
			this.hitBox.setX((int)hitBox.getX() + SPEED);
			this.x = x + SPEED;
		}
    }

	
	public void draw(GraphicsContext graphicsContext) {
		
		animationManager.draw(graphicsContext, this.x, this.y);
	}

	/* Reset position when Ghost or Pacman dies and Pacman still has lives left. */
	public void reset(int x, int y) {
		
		this.hitBox.setX(x + OFFSET/2);
		this.hitBox.setY(y + OFFSET/2);
		this.x = x;
		this.y = y;
		this.hasLeftSpawn = false;
		setDirection('S');
		setState(Ghost.STATE.ALIVE);
	}
	
	
	private void setUpAnimations() {
		
		Image ghostOpen = new Image("assets/Ghost/tempGhostOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image ghostClosed = new Image("assets/Ghost/tempGhostClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image[] frames = new Image[2];
		frames[0] = ghostOpen;
		frames[1] = ghostClosed;
		
		
		Animation animation = new Animation(frames,0.3f);
		Animation[] animations = new Animation[1];
		animations[0] = animation;
		
		animationManager = new AnimationManager(animations);
	}
	
	
	/* Public getters */
	
    public STATE getState() {
    	
    	return this.state;
    }
    
    public double getX() {
    	
    	return this.x;
    }
    
    public double getY() {
    	
    	return this.y;
    }
	
}
