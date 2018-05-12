/*
Name: Main.java
Description: Main file for FinalStudy Project
Author: Austin Vargason
Date Modified: 5/11/18
 */

//vargason_austin package
package vargason_austin;

//imports for Main
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.InputMismatchException;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene mainScene = getMainScene();
        primaryStage.setTitle("Test Form");//set the title
        primaryStage.setScene(mainScene);//set the scene
        primaryStage.show();//show the window
    }

    //method to get Main Scene for project
    public static Scene getMainScene() {
        //gridPane to store items in form
        GridPane gridPane = new GridPane();

        //items to add into GridPane
        TextField firstNameField =  new TextField();
        TextField lastNameField = new TextField();
        TextField emailField = new TextField();
        Button submitButton = new Button("Submit");

        //set handler for submit button
        submitButton.setOnAction(event -> {
            try {
                new Person(firstNameField.getText(), lastNameField.getText(), emailField.getText());
            }
            catch (InputMismatchException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Email Provided");
                alert.setContentText("Please enter a valid email of the form: johndoe@email.com");
                alert.initStyle(StageStyle.UNIFIED);
                alert.show();
            }
        });

        //add items to the GridPane
        gridPane.add(new Label("First Name: "), 0, 0);
        gridPane.add(firstNameField, 1, 0);
        gridPane.add(new Label("Last Name: "), 0, 1);
        gridPane.add(lastNameField, 1, 1);
        gridPane.add(new Label("Email: "), 0, 2);
        gridPane.add(emailField, 1, 2);
        gridPane.add(submitButton, 1, 3);

        //set GridPane options
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        GridPane.setHalignment(submitButton, HPos.RIGHT);

        //scene to return
        Scene mainScene = new Scene(gridPane, 300, 200);

        //set the enter key to fire the button for the scene
        mainScene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                submitButton.fire();
            }
        });

        //return the scene
        return mainScene;
    }

    //main method used for testing inside of IDE
    public static void main(String[] args) {
        Application.launch(args);
    }
}
