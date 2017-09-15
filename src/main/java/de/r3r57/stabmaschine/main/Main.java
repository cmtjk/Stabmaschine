package de.r3r57.stabmaschine.main;

import de.r3r57.stabmaschine.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		new View().show();
	}
	
	public static void main(String[] args) {
		launch();
	}

}
