package de.r3r57.stabmaschine.resources;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Stab extends VBox {

    private int diskCounter = 0;
    private double diskHeight = 230;

    private Button addButton, removeButton, resetButton;
    private Label isEmptyLabel, isEmptyValueLabel;

    private Canvas canvas = new Canvas(135, 260);
    private GraphicsContext gc = canvas.getGraphicsContext2D();

    public Stab(int number) {
	super();
	this.setPadding(new Insets(0, 0, 10, 0));
	this.setSpacing(10);
	this.setAlignment(Pos.CENTER);
	this.setStyle("-fx-background-color: lightblue;");

	TextField description = new TextField("" + number);
	description.setAlignment(Pos.BASELINE_CENTER);

	drawStab();
	this.getChildren().addAll(description, canvas, initFunctions());

	initListenerAndEvents();
    }

    private void drawDisks() {
	for (int i = 0; i < diskCounter; i++) {
	    gc.setFill(Color.RED);
	    gc.fillRoundRect(35, diskHeight, 65, 15, 10, 10);
	    gc.setFill(Color.GREY);
	    gc.fillText((i + 1) + "__", 10, diskHeight);
	    diskHeight -= 17;
	}
    }

    public void removeDisk() {
	diskCounter -= 1;
	if (diskCounter < 0)
	    diskCounter = 0;
	drawStab();
    }

    public void addDisk() {
	diskCounter += 1;
	if (diskCounter > 12)
	    diskCounter = 12;
	drawStab();
    }

    private void drawStab() {
	resetCanvas();

	gc.setFill(Color.GRAY);
	gc.fillRoundRect(60, 15, 15, 250, 10, 25);
	gc.fillRect(20, 245, 95, 15);

	drawDisks();

    }

    private HBox initEmptyLables() {
	HBox labelHBox = new HBox(5);
	labelHBox.setAlignment(Pos.CENTER);

	isEmptyLabel = new Label("isEmpty() -");
	isEmptyValueLabel = new Label("true");
	
	isEmptyValueLabel.setStyle("-fx-font-weight: bold");
	
	isEmptyLabel.setMaxWidth(Double.MAX_VALUE);
	isEmptyValueLabel.setMaxWidth(Double.MAX_VALUE);
	isEmptyValueLabel.setTextFill(Color.GREEN);

	labelHBox.getChildren().addAll(isEmptyLabel, isEmptyValueLabel);

	return labelHBox;
    }

    private VBox initFunctions() {
	VBox buttonsVBox = new VBox(5);
	buttonsVBox.setAlignment(Pos.BASELINE_CENTER);

	addButton = new Button("add()");
	removeButton = new Button("remove()");
	resetButton = new Button("Reset");

	
	addButton.setMaxWidth(Double.MAX_VALUE);
	removeButton.setMaxWidth(Double.MAX_VALUE);

	addButton.setStyle("-fx-font-weight: bold");
	removeButton.setStyle("-fx-font-weight: bold");

	resetButton.setScaleX(.75);
	resetButton.setScaleY(.75);

	buttonsVBox.getChildren().addAll(initEmptyLables(), addButton, removeButton, resetButton);

	return buttonsVBox;
    }

    private void initListenerAndEvents() {
	addButton.setOnAction(actionEvent -> {
	    addDisk();
	    isEmpty();
	    actionEvent.consume();
	});

	removeButton.setOnAction(actionEvent -> {
	    removeDisk();
	    isEmpty();
	    actionEvent.consume();
	});

	resetButton.setOnAction(actionEvent -> {
	    diskCounter = 0;
	    drawStab();
	    isEmpty();
	});
    }

    private void isEmpty() {

	if (diskCounter <= 0) {
	    isEmptyValueLabel.setText("true");
	    isEmptyValueLabel.setTextFill(Color.GREEN);
	} else {
	    isEmptyValueLabel.setText("false");
	    isEmptyValueLabel.setTextFill(Color.RED);

	}
    }

    private void resetCanvas() {
	gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	diskHeight = 230;
    }

}