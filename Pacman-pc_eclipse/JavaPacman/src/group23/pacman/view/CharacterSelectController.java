package group23.pacman.view;

import group23.pacman.MainApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class CharacterSelectController {

	@FXML
	private ImageView background;
	@FXML
	private ImageView ghost1;
	@FXML
	private ImageView ghost2;
	@FXML
	private ImageView ghost3;
	@FXML
	private ImageView ghost4;
	
	private MainApp mainApp;
	
	private int ghostIndex;
	
	private static final int MAX_GHOSTS = 4;
	
	private int numPlayers;
	
	private boolean firstPick;
	
	
	private static final int SPRITE_HEIGHT = 150;
	private static final int SPRITE_WIDTH = 150;
	
	
	public CharacterSelectController() {
		
		
	}
	
	@FXML
	private void handleButton(KeyEvent event) {
		
		if (event.getCode() == KeyCode.ENTER) {
			
			if (numPlayers == 2) {
				mainApp.setPlayer2(ghostIndex);
				mainApp.showLevelSelect();
			}
			else if (numPlayers == 3) {
				if (firstPick) {
					mainApp.setPlayer2(ghostIndex);
					ghostIndex = 1;
					highlightGhost(ghostIndex);
					firstPick = false;
				}
				else {
					mainApp.setPlayer3(ghostIndex);
					mainApp.showLevelSelect();
				}
			}
			
		}
		
		else if (event.getCode() == KeyCode.LEFT) {
			
			ghostIndex--;
			ghostIndex = ghostIndex < 1 ? 1 : ghostIndex;
			highlightGhost(ghostIndex);
		}
		
		else if (event.getCode() == KeyCode.RIGHT) {
			
			ghostIndex++;
			ghostIndex = ghostIndex > 4 ? MAX_GHOSTS : ghostIndex;
			highlightGhost(ghostIndex);
		}
		else if (event.getCode() == KeyCode.ESCAPE) {
			Platform.exit();
		}
		
	}
	
	
	@FXML
	private void initialize() {
		
		background.setImage(new Image("bg/blackbg.png"));
		ghost1.setImage(new Image("assets/Elements-CharSel/ghost1-highlighted.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		ghost2.setImage(new Image("assets/Elements-CharSel/ghost2.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		ghost3.setImage(new Image("assets/Elements-CharSel/ghost3.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		ghost4.setImage(new Image("assets/Elements-CharSel/ghost4.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		
		firstPick = true;
		ghostIndex = 1;
	}
	
	
	private void highlightGhost(int ghostIndex) {
		
		if (ghostIndex == 1) {
			ghost1.setImage(new Image("assets/Elements-CharSel/ghost1-highlighted.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost2.setImage(new Image("assets/Elements-CharSel/ghost2.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost3.setImage(new Image("assets/Elements-CharSel/ghost3.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost4.setImage(new Image("assets/Elements-CharSel/ghost4.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		}
		else if (ghostIndex == 2) {
			ghost1.setImage(new Image("assets/Elements-CharSel/ghost1.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost2.setImage(new Image("assets/Elements-CharSel/ghost2-highlighted.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost3.setImage(new Image("assets/Elements-CharSel/ghost3.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost4.setImage(new Image("assets/Elements-CharSel/ghost4.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		}
		else if (ghostIndex == 3) {
			ghost1.setImage(new Image("assets/Elements-CharSel/ghost1.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost2.setImage(new Image("assets/Elements-CharSel/ghost2.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost3.setImage(new Image("assets/Elements-CharSel/ghost3-highlighted.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost4.setImage(new Image("assets/Elements-CharSel/ghost4.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));	
		}
		else if (ghostIndex == 4) {
			ghost1.setImage(new Image("assets/Elements-CharSel/ghost1.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost2.setImage(new Image("assets/Elements-CharSel/ghost2.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost3.setImage(new Image("assets/Elements-CharSel/ghost3.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
			ghost4.setImage(new Image("assets/Elements-CharSel/ghost4-highlighted.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false));
		}
	}
	
	
	public void setPlayers(int players) {
		
		this.numPlayers = players;
	}
	
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
	}
}
