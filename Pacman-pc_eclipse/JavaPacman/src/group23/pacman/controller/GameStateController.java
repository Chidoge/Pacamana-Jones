package group23.pacman.controller;

import group23.pacman.model.Game;
import group23.pacman.view.GameViewController;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameStateController {
	
	private Scene scene;
	
	private Game game;
	
	private GameViewController gameViewController;
	
	public GameStateController(GameViewController gameViewController,Game game) {
		
		this.gameViewController = gameViewController;
		this.scene = gameViewController.getScene();
		this.game = game;
		
	}
	
	public void listen() {
		
		 scene.setOnKeyPressed(new EventHandler<KeyEvent> (){
		    	@Override
		    	public void handle(KeyEvent e) {
			    	if (e.getCode() == KeyCode.UP) {
			    		game.getPacman().queueMovement('U');
			    	}
			    	else if (e.getCode() == KeyCode.DOWN) {
			    		game.getPacman().queueMovement('D');
			    	}
			    	else if (e.getCode() == KeyCode.LEFT) {
			    		game.getPacman().queueMovement('L');
			    	}
			    	else if (e.getCode() == KeyCode.RIGHT) {
			    		game.getPacman().queueMovement('R');
			    	}
			    	else if (e.getCode() == KeyCode.P) {
			    		gameViewController.changeState();
			    	}
		    	}
		    });
	}
	
	public void update() {
		
		game.update();
	}
	
	public Game getGame() {
		
		return this.game;
	}

}
