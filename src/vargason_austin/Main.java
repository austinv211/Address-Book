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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
        //Panes to store items in form
        BorderPane borderPane = new BorderPane();
        TableView<Person> personTableView = new TableView<>();
        HBox hBox = new HBox();

        //items to add into Hbox
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

        //set up columns for table
        TableColumn<Person, String> col_firstName = new TableColumn<Person, String>("FirstName");
        col_firstName.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));

        TableColumn<Person, String> col_lastName = new TableColumn<Person, String>("LastName");
        col_lastName.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));

        TableColumn<Person, String> col_email = new TableColumn<Person, String>("Email");
        col_email.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));


        //set the columns
        personTableView.getColumns().setAll(col_firstName, col_lastName, col_email);

        //set the items
        personTableView.setItems(getPeople());

        //set the items
        ObservableList<Person> people = getPeople();

        //set up HBox
        hBox.getChildren().addAll(firstNameField, lastNameField, emailField, submitButton);

        //set hBox properties
        hBox.setSpacing(5);

        //add items to borderPane
        borderPane.setCenter(personTableView);
        borderPane.setBottom(hBox);

        //scene to return
        Scene mainScene = new Scene(borderPane);

        //set the enter key to fire the button for the scene
        mainScene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                submitButton.fire();
            }
        });

        //return the scene
        return mainScene;
    }

    //method to get People for Table
    public static ObservableList<Person> getPeople() {
        Person austin = new Person("Austin", "Vargason", "vargasona@gmail.com");

        ObservableList<Person> personObservableList = FXCollections.observableArrayList(austin);

        return personObservableList;
    }

    //main method used for testing inside of IDE
    public static void main(String[] args) {
        launch(args);
    }
}
