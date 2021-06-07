package controller;

import javafx.scene.control.TextField;
import util.MaterialUI;

public class StudentFormController {

    public TextField txtNIC;
    public TextField txtFullName;
    public TextField txtAddress;
    public TextField txtContactNumber;
    public TextField txtEmail;
    public TextField txtDOB;

    public void initialize() {
        MaterialUI.paintTextFields( txtNIC, txtFullName, txtAddress, txtDOB,  txtContactNumber, txtEmail);
    }
}
