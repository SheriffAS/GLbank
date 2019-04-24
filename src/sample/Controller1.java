package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class Controller1 {
    public TextField passField;
    public TextField nameField;

    public void logUser(ActionEvent actionEvent) {


    }

    public String getLogin() {
        String name = nameField.getText();
        return name;
    }

    public String getPass() {
        String password = passField.getText();
        return password;
    }

    //get conection
}
