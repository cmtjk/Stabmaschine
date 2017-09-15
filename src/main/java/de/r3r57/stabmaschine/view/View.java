package de.r3r57.stabmaschine.view;

import de.r3r57.stabmaschine.resources.Stab;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends Stage {

    private HBox mainHBox;
    private Button addButton, removeButton;

    public View() {

	setFrameOptions();

	Pane rootPane = createContentPane();
	Scene scene = new Scene(rootPane, 900, 550);

	initListenerAndEvents();

	rootPane.setStyle("-fx-font-size: 11pt");
	this.setScene(scene);

    }

    private VBox createContentPane() {

	VBox mainVBox = new VBox(10);

	HBox headerHBox = new HBox();
	headerHBox.setAlignment(Pos.BASELINE_RIGHT);
	headerHBox.setStyle("-fx-background-color: darkgrey");
	headerHBox.setPadding(new Insets(10));

	addButton = new Button("+");
	removeButton = new Button("-");

	addButton.setPrefWidth(50);
	removeButton.setPrefWidth(50);

	headerHBox.getChildren().addAll(removeButton, addButton);

	mainHBox = new HBox(10);
	mainHBox.setPadding(new Insets(10));
	mainHBox.setAlignment(Pos.BASELINE_CENTER);

	mainVBox.getChildren().addAll(headerHBox, mainHBox);

	return mainVBox;

    }

    private void addStab() {
	mainHBox.getChildren().add(new Stab(mainHBox.getChildren().size()));
    }

    private void removeStab() {
	mainHBox.getChildren().remove(mainHBox.getChildren().size() - 1);
    }

    private void initListenerAndEvents() {
	addButton.setOnAction(actionEvent -> {
	    if (mainHBox.getChildren().size() < 6) {
		addStab();
	    }
	    actionEvent.consume();
	});

	removeButton.setOnAction(actionEvent -> {
	    if (mainHBox.getChildren().size() > 0) {
		removeStab();
	    }
	    actionEvent.consume();
	});

    }

    private void setFrameOptions() {
	this.setTitle("Stabmaschine - (ↄ) C. Matějka");
	this.setOnCloseRequest(e -> Platform.exit());
    }

}
