import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage; 
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameScene {
	
	
	Stage mainStage;
	
	private Group root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;
    private Game game;
    private ArrayList<Wall> walls;
    private Rectangle rect;

	public GameScene(Stage mainStage) {
		
		this.mainStage = mainStage;
        
		root = new Group();
		scene = new Scene(root);
	    canvas = new Canvas( 1366, 768 );
	    ImageView iv = new ImageView(new Image("background-main.png"));
	    root.getChildren().add(iv);
	    root.getChildren().add(canvas);
	    game = new Game();
		walls = game.getWalls();
	    for (Wall w :walls) {
	    	
	    	rect = new Rectangle(w.getX(),w.getY(),w.getWidth(),w.getHeight());
	    	rect.setFill(Color.WHITE);
	    	
	    	root.getChildren().add(rect);
	    }
	    gc = canvas.getGraphicsContext2D();
	    
	    

	    scene.setOnKeyPressed(new EventHandler<KeyEvent> (){
	    	@Override
	    	public void handle(KeyEvent e) {
	    	/* switch to switch statements later */
		    	if (e.getCode() == KeyCode.UP) {
		    		game.getPacman().setDirection(1);
		    	}
		    	else if (e.getCode() == KeyCode.DOWN) {
		    		game.getPacman().setDirection(2);
		    	}
		    	else if (e.getCode() == KeyCode.LEFT) {
		    		game.getPacman().setDirection(3);
		    	}
		    	else if (e.getCode() == KeyCode.RIGHT) {
		    		game.getPacman().setDirection(4);
		    	}
	    	}
	    });
	    
	    mainStage.setScene(scene);
	    mainStage.show();

	}
	
	public void setGameMode(int gameType) {
		
		if (gameType == 1) {
			/*game = new Game();
			walls = game.getWalls();*/
		}
		
		
	}
	
	
	public void start() {
		
		 new AnimationTimer() {
			 	
		        public void handle(long currentNanoTime)
		        {
		        	
		        	gc.clearRect(0, 0, 1440, 900);

		        	game.update();
		            gc.drawImage(game.getPacman().getImage() , game.getPacman().getX(), game.getPacman().getY() );

		        }
		    }.start();
		
	}
	


	
	
	
}
