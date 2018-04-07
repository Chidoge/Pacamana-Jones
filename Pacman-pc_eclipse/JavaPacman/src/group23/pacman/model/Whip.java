package group23.pacman.model;

import group23.pacman.view.Animation;
import group23.pacman.view.AnimationManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/** Whip class is the weapon that the Pacman object uses when it picks up the SpecialPellet object */

public class Whip extends GameObject implements MovingCharacter {
	
	/* Pixels moved per update */
	private static final int SPEED = 2;
	
	/* Direction Pacman is facing */
	private char vector;
	
	/* Handles the animations */
	private AnimationManager animationManager;
	
	private boolean shouldPlay;
	
	private int x;
	private int y;
	
	private int bulletX;
	private int bulletY;
	
	private Image bullet;
	
	private int index;
	private long time;
	
	private Rectangle hitBox;
	
	public Whip() {
		this.x = 0;
		this.y = 0;
		hitBox = new Rectangle();
		hitBox.setWidth(10);
		hitBox.setHeight(10);
		hitBox.setX(this.x);
		hitBox.setY(this.y);
		shouldPlay = false;
		setUpAnimations();
		bullet = new Image("assets/tiles/mapBlock-random.png",10,10,false,false);
		time = 0;
		
	}
	
	private void setUpAnimations() {
		
		Image left1 = new Image("assets/Pacman/Whip/left-whip1.png",90,30,false,false);
		Image left2 = new Image("assets/Pacman/Whip/left-whip2.png",90,30,false,false);
		Image left3 = new Image("assets/Pacman/Whip/left-whip3.png",90,30,false,false);
		
		Image right1 = new Image("assets/Pacman/Whip/right-whip1.png",90,30,false,false);
		Image right2 = new Image("assets/Pacman/Whip/right-whip2.png",90,30,false,false);
		Image right3 = new Image("assets/Pacman/Whip/right-whip3.png",90,30,false,false);
		
		Image up1 = new Image("assets/Pacman/Whip/up-whip1.png",30,90,false,false);
		Image up2 = new Image("assets/Pacman/Whip/up-whip2.png",30,90,false,false);
		Image up3 = new Image("assets/Pacman/Whip/up-whip3.png",30,90,false,false);
		
		Image down1 = new Image("assets/Pacman/Whip/down-whip1.png",30,90,false,false);
		Image down2 = new Image("assets/Pacman/Whip/down-whip2.png",30,90,false,false);
		Image down3 = new Image("assets/Pacman/Whip/down-whip3.png",30,90,false,false);
		
		Image[] left = new Image[3];
		left[0] = left1;
		left[1] = left2;
		left[2] = left3;
		
		Image[] right = new Image[3];
		right[0] = right1;
		right[1] = right2;
		right[2] = right3;
		
		Image[] up = new Image[3];
		up[0] = up1;
		up[1] = up2;
		up[2] = up3;
		
		Image[] down = new Image[3];
		down[0] = down1;
		down[1] = down2;
		down[2] = down3;
		
		Animation leftAnim = new Animation(left,0.3f);
		Animation rightAnim = new Animation(right,0.3f);
		Animation upAnim = new Animation(up,0.3f);
		Animation downAnim = new Animation(down,0.3f);
		
		Animation[] animations = new Animation[4];
		animations[0] = leftAnim;
		animations[1] = rightAnim;
		animations[2] = upAnim;
		animations[3] = downAnim;
		
		animationManager = new AnimationManager(animations);
	}
	
	public void playAnimation() {
		
		if (shouldPlay) {
				if (this.vector == 'S') {
					animationManager.playAction(1);
				}
				if (this.vector == 'U') {
					animationManager.playAction(2);
				}
				else if (this.vector == 'D') {
					animationManager.playAction(3);
				}
				else if (this.vector == 'L') {
					animationManager.playAction(0);
				}
				else if (this.vector == 'R') {
					animationManager.playAction(1);
				}
		}
	}
	
	public void whip() {
		
		shouldPlay = true;
	}
	
	public void update(char vector,int x,int y) {
		
		this.vector = vector;
		
		index = animationManager.getFrameIndex();
		
		if (vector == 'U') {
			
			setX(x);
			setY(y - 60);
			
			hitBox.setWidth(10);
			hitBox.setHeight(90);
			hitBox.setX(x+10);
			hitBox.setY(y-60);
			
			
		}
		else if (vector == 'D') {
			
			setX(x);
			setY(y);
			
			hitBox.setWidth(10);
			hitBox.setHeight(90);
			hitBox.setX(x+10);
			hitBox.setY(y+30);
			
		}
		else if (vector == 'L') {
			
			setX(x - 60);
			setY(y);
			
			hitBox.setWidth(90);
			hitBox.setHeight(10);
			hitBox.setX(x-60);
			hitBox.setY(y+10);
			
		}
		else if (vector == 'R') {
			
			setX(x);
			setY(y);
			
			hitBox.setWidth(90);
			hitBox.setHeight(10);
			hitBox.setX(x+30);
			hitBox.setY(y+10);
			
		}
		
		
		if (animationManager.getFrameIndex() == 2) {
			animationManager.stopAction();
			shouldPlay = false;
		}
		animationManager.update();
		playAnimation();
	}
	
	public void draw(GraphicsContext graphicsContext) {
		
		if (shouldPlay) {
			animationManager.draw(graphicsContext,this.x,this.y);
			graphicsContext.drawImage(bullet, hitBox.getX(), hitBox.getY());
		}
		
	}
	
	
    public Rectangle getHitBox() {
    	return this.hitBox;
    }
	
	
	@Override
	public boolean checkforQueuedAction() {
		return false;
	}

	@Override
	public void setDirection(char qDirection) {
		
	}

	@Override
	public char getQDirection() {
		return 0;
	}

	@Override
	public void updateDestination() {
		
	}

	@Override
	public char getDirection() {
		return 0;
	}

	@Override
	public void reset(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	public void setX(int x) {
		// TODO Auto-generated method stub
		this.x = x;
	}
	
	public void setY(int y) {
		// TODO Auto-generated method stub
		this.y = y;
	}


	@Override
	public void setHasLeftSpawn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getHasLeftSpawn() {
		// TODO Auto-generated method stub
		return false;
	}
}
