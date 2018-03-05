import javafx.scene.image.Image;

public class Pacman implements GameObject{
	
	
	private static final int SPRITE_HEIGHT = 100;
	private static final int SPRITE_WIDTH = 100;
	private Rectangle hitBox;
	private double x;
	private double y;
	private Image sprite;
	private int vector;
	
	public Pacman(Image sprite,int x,int y) {
		
		this.sprite = sprite;
		hitBox = new Rectangle();
		hitBox.setHeight(SPRITE_HEIGHT);
		hitBox.setWidth(SPRITE_WIDTH);
		hitBox.setX(x);
		hitBox.setY(y);
		this.x = x;
		this.y = y;
		this.vector = 0;
	
	}
	
	public void update() {
		
		/* UP */
		if (this.vector == 1) {
			this.y -=5;
		}
		/* DOWN */
		else if (this.vector == 2) {
			this.y +=5;
		}
		/* LEFT */
		else if (this.vector == 3) {
			this.x -=5;
		}
		/* RIGHT */
		else if (this.vector == 4) {
			this.x +=5;
		}

	}
	
    public Rectangle getHitBox(){
    	return this.hitBox;
    }

    public double getX() {
    	
    	return this.x;
    }
    
    public double getY() {
    	
    	return this.y;
    }
    
    public void setDirection(int vector) {
    	
    	if (this.y >= 768 -100 && vector == 1) {
    		
    	}
    	else if (this.y <=0 && vector == 0) {
    		
    	}
    	else if (this.x >= 1368 -100 && vector == 3) {
    		
    	}
    	else if (this.x <= 100 && vector ==2 ) {
    		
    	}
    	else {
    		this.vector =vector;
    	}
    }
    
    public int getDirection() {
    	
    	return this.vector;
    }
    
    public Image getImage() {
    	
    	return this.sprite;
    }
}
