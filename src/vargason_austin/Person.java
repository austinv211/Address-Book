/*
Name: Person.java
Description: Class to store the data for a person
Author: Austin Vargason
Date Modified:
*/

//vargason_austin package
package vargason_austin;

//imports
import java.io.*;

public class Person implements Serializable {
    //data fields
    private String firstName;
    private String lastName;
    private String email;
    private static final long serialVersionUID = 1L;

    //constructor
    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
