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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //get previous data table
        ObservableList<Person> personObservableList = inputPersonData();
        Scene mainScene = getMainScene(personObservableList);
        primaryStage.setTitle("Test Form");//set the title
        primaryStage.setScene(mainScene);//set the scene
        primaryStage.show();//show the window
    }

    //method to get Main Scene for project
    public static Scene getMainScene(ObservableList<Person> personObservableList) {
        //Panes to store items in form
        BorderPane borderPane = new BorderPane();
        TableView<Person> personTableView = new TableView<>();
        HBox hBox = new HBox();

        //set up columns for table
        TableColumn<Person, String> col_firstName = new TableColumn<Person, String>("FirstName");
        col_firstName.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        col_firstName.setMinWidth(200);

        TableColumn<Person, String> col_lastName = new TableColumn<Person, String>("LastName");
        col_lastName.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        col_lastName.setMinWidth(200);

        TableColumn<Person, String> col_email = new TableColumn<Person, String>("Email");
        col_email.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        col_email.setMinWidth(300);

        //set the columns
        personTableView.getColumns().setAll(col_firstName, col_lastName, col_email);

        //set the items
        personTableView.setItems(personObservableList);

        //items to add into Hbox
        TextField firstNameField =  new TextField();
        firstNameField.setPromptText("first name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("last name");
        TextField emailField = new TextField();
        emailField.setPromptText("email address");
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");

        //set handler for submit button
        addButton.setOnAction(event -> {
            Person person = new Person(firstNameField.getText(), lastNameField.getText(), emailField.getText());
            personObservableList.add(person);

            //clear the text fields
            firstNameField.clear();
            lastNameField.clear();
            emailField.clear();

            //save changes to file
            saveOutput(personObservableList);
        });

        //set handler for delete button
        deleteButton.setOnAction(event -> {
            ObservableList<Person> selectedPeople = personTableView.getSelectionModel().getSelectedItems();

            personObservableList.removeAll(selectedPeople);

            saveOutput(personObservableList);
        });

        //set up HBox
        hBox.getChildren().addAll(firstNameField, lastNameField, emailField, addButton, deleteButton);

        //set hBox properties
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER);

        //add menu bar
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("_File");
        MenuItem file_deleteAll = new MenuItem("Delete All");

        file_deleteAll.setOnAction(event -> {
            personObservableList.removeAll(personObservableList);
        });

        fileMenu.getItems().add(file_deleteAll);
        menuBar.getMenus().add(fileMenu);

        //add items to borderPane
        borderPane.setTop(menuBar);
        borderPane.setCenter(personTableView);
        borderPane.setBottom(hBox);

        //scene to return
        Scene mainScene = new Scene(borderPane);

        //set the enter key to fire the button for the scene
        mainScene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                addButton.fire();
            }
        });

        //return the scene
        return mainScene;
    }

    //method to input previous saved data
    public static ObservableList<Person> inputPersonData() {
        ObservableList<Person> personObservableList = FXCollections.observableArrayList();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./dataFiles/people.dat"));
            ArrayList<Person> personArrayList = (ArrayList<Person>) ois.readObject();
            personObservableList = FXCollections.observableArrayList(personArrayList);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return personObservableList;
    }

    //method to output file to
    public static void saveOutput(ObservableList<Person> personObservableList) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./dataFiles/people.dat"));
            oos.writeObject(new ArrayList<Person>(personObservableList));
            oos.close();
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
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
