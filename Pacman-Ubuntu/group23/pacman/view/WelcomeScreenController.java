package group23.pacman.view;

import group23.pacman.MainApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
	The controller class for the welcome screen view.
 */
public class WelcomeScreenController {
	
	private MainApp mainApp;
	
	@FXML
	private Button playBtn;
	
	@FXML
	private Button optionsBtn;
	
	@FXML
	private Button exitBtn;
	
	@FXML 
	private ImageView playBtnImage;
	
	@FXML
	private ImageView optionBtnImage;
	
	@FXML
	private ImageView exitBtnImage;
	
	@FXML
	private ImageView background;
	
	
	public WelcomeScreenController() {
		
		
	}
	
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handlePlay(KeyEvent event) {
		
		if (event.getCode() == KeyCode.ENTER) {
			
			mainApp.showLevelSelect();
		}
		
		else if (event.getCode() == KeyCode.LEFT) {
			/* Maybe play a sound effect here */
		}
		else if (event.getCode() == KeyCode.RIGHT) {
			highlightButton(2);
		}

	}
	
	@FXML
	private void handleOptions(KeyEvent event) {
		
		if (event.getCode() == KeyCode.ENTER) {
			//TODO 
			//Show options dialog stage.
		}
		else if (event.getCode() == KeyCode.LEFT) {
			highlightButton(1);
		}
		else if (event.getCode() == KeyCode.RIGHT) {
			highlightButton(3);
			
		}

	}
	
	
	@FXML
	private void handleExit(KeyEvent event) {
		
		if (event.getCode() == KeyCode.ENTER) {
			
			Platform.exit();
		}
		else if (event.getCode() == KeyCode.LEFT) {
			highlightButton(2);
		}
		else if (event.getCode() == KeyCode.RIGHT) {
			/* Maybe play a sound effect here */
		}
		

	}
	
	private void highlightButton(int button) {
		if (button == 1) {
			playBtnImage.setImage(new Image("assets/buttons/button-play-highlighted.png",200,100,false,false));
			optionBtnImage.setImage(new Image("assets/buttons/button-options.png",200,100,false,false));
			exitBtnImage.setImage(new Image("assets/buttons/button-exit.png",200,100,false,false));
		}
		else if (button == 2) {
			playBtnImage.setImage(new Image("assets/buttons/button-play.png",200,100,false,false));
			optionBtnImage.setImage(new Image("assets/buttons/button-options-highlighted.png",200,100,false,false));
			exitBtnImage.setImage(new Image("assets/buttons/button-exit.png",200,100,false,false));
		}
		else if (button == 3) {
			playBtnImage.setImage(new Image("assets/buttons/button-play.png",200,100,false,false));
			optionBtnImage.setImage(new Image("assets/buttons/button-options.png",200,100,false,false));
			exitBtnImage.setImage(new Image("assets/buttons/button-exit-highlighted.png",200,100,false,false));
		}
	}
	
	@FXML
	private void initialize() {
		
		Image mainMenuBackground = new Image("bg/background-main.png");
		background.setImage(mainMenuBackground);

		Image playImage = new Image("assets/buttons/button-play-highlighted.png",200,100,false,false);
		playBtnImage.setImage(playImage);
		
		Image optionImage = new Image("assets/buttons/button-options.png",200,100,false,false);
		optionBtnImage.setImage(optionImage);
		
		Image exitImage = new Image("assets/buttons/button-exit.png",200,100,false,false);
		exitBtnImage.setImage(exitImage);
	}

}