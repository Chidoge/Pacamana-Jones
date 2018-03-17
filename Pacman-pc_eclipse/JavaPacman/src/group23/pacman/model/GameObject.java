package group23.pacman.model;
import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
	
	
	public enum TYPE{
		PELLET,
		WALL,
		GHOST
	}
	
	
	protected TYPE type;
	protected Rectangle hitBox;
	
	
    protected Rectangle getHitBox() {
    	
    	return this.hitBox;
    }

    public double getX() {
    	
    	return this.hitBox.getX();
    }
    
    public double getY() {
    	
    	return this.hitBox.getY();
    }
    
    public void draw(GraphicsContext graphicsContext) {
    	
    }
    
    public TYPE getType() {
    	
    	return this.type;
    }
}
