package vargason_austin;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

import java.util.InputMismatchException;

public class Person {
    //data fields
    private String firstName;
    private String lastName;
    private String email;

    //constructor
    public Person(String firstName, String lastName, String email) {
        //if the email meets the parameters, set options
        if (email.contains("@") && email.endsWith(".com")) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;

            //show alert
            showSuccessMessage();
        }
        else {
            throw new InputMismatchException("Invalid email format provided");
        }
    }

    //getters
    public String getFirstName() {
        String copyFirstName = copyString(this.firstName);

        return copyFirstName;
    }

    public String getLastName() {
        String copyLastName = copyString(this.lastName);

        return copyLastName;
    }

    public String getEmail() {
        String copyEmail = copyString(this.email);

        return email;
    }

    //return copy of Strings
    public String copyString(String oldString) {
        String newString = new String(oldString);

        return newString;
    }

    //method to show success message
    private void showSuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UNIFIED);
        alert.setContentText("Person object created successfully");
        alert.show();
    }

    //toString method
    @Override
    public String toString() {
        String output = "";

        output += "First Name: " + this.getFirstName();
        output += "Last Name: " + this.getLastName();
        output += "email: " + this.getEmail();

        return output;
    }
}
