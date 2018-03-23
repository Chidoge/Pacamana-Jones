package group23.pacman.view;

import group23.pacman.MainApp;
import group23.pacman.controller.GameStateController;
import group23.pacman.model.Game;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;


public class GameViewController {
	
	private GraphicsContext graphicsContext;
	@FXML
	private ImageView background_map;
	
	private MainApp mainApp;
	
	private GameStateController gameStateController;
	
	private char map;
	private boolean running = true;
	
	public GameViewController() {
		
		
	}
	
	
	public void setGame(Game game) {
		
		map = game.getMap();
		
		String backgroundImage;
		switch (map) {
    	case 'c' :
    		backgroundImage = "bg/background-classic_game.png";
    		break;
	    case 's' :
	    	backgroundImage = "bg/background-sea_game.png";
	    	break;
	    case 'd' :
	    	backgroundImage = "bg/background-desert_game.png";
	    	break;
	    default :
	    	backgroundImage = "bg/background-sea_game.png";
	    	break;
		}
		
		background_map.setImage(new Image(backgroundImage));
		
		gameStateController = new GameStateController(this,game);
		gameStateController.listen();
		
		Canvas canvas = new Canvas(1366,768);
		mainApp.getPane().getChildren().add(canvas);
		graphicsContext = canvas.getGraphicsContext2D();
		
	}
	
	public void start() {
		
		new AnimationTimer() {
			public void handle(long time) {	
				if (running == true) {
					graphicsContext.clearRect(0, 0, 1366, 768);
					gameStateController.update();
					draw(graphicsContext);
				}
			}
		}.start();
	}
	
	public void draw(GraphicsContext graphicsContext) {
		
		gameStateController.getGame().getPacman().draw(graphicsContext);
		gameStateController.getGame().getGhost().draw(graphicsContext);
		gameStateController.getGame().drawObjects(graphicsContext);
	}
	
	/* Pauses/starts the game */
	public void changeState() {
		
		this.running = !this.running;
	}
	
	public Scene getScene() {
		
		return this.mainApp.getScene();
	}
	
	
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
	}
}
