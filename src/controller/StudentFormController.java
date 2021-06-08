package controller;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.MaterialUI;

public class StudentFormController {

    public TextField txtNIC;
    public TextField txtFullName;
    public TextField txtAddress;
    public TextField txtContactNumber;
    public TextField txtEmail;
    public TextField txtDOB;
    public JFXButton btnSave;
    public Label lblTitle;

    public void initialize() {
        MaterialUI.paintTextFields( txtNIC, txtFullName, txtAddress, txtDOB,  txtContactNumber, txtEmail);
    }
}
