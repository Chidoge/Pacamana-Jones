import group23.pacman.view.GameScene;
import javafx.application.Application; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.StackPane; 
import javafx.stage.Stage; 
import javafx.scene.image.*;

/**
 * The class that creates the platform and shows the main menu */

public class PacmanGame extends Application{ 

	public static void main(String[] args) {
		
		launch(args);
		
	} 
	
	@Override 
	public void start(Stage gameWindow) {
		
		StackPane pane = new StackPane();
		
		/* Load the main menu background */
		Image mainMenuBackground = new Image("bg\\background-main.png");
		ImageView iv = new ImageView(mainMenuBackground);
		pane.getChildren().add(iv);
		
		/* Set up the play button image and make it listen to click events */
		Image playImage = new Image("assets\\button-play.png",150,100,false,false);
		Button playBtn = new Button("",new ImageView(playImage));
		
		playBtn.setOnAction(new EventHandler<ActionEvent>() { 
			
			@Override 
			public void handle(ActionEvent event) { 
				
				/* Starts the game if play is clicked */
				/* For sea level */
				char level = 's';
				GameScene gameScene = new GameScene(gameWindow,level);
				gameScene.setGameMode(1);
				gameScene.start();
				
				} 
			}); 
		pane.getChildren().add(playBtn);
		
		
		/* Open up the platform */
		gameWindow.setTitle("Pacman");
		gameWindow.setScene(new Scene(pane, 1366, 768)); 
		gameWindow.show(); 
		
		}
	

	
	
}