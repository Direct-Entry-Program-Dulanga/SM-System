package controller;

import javafx.scene.control.TextField;
import util.MaterialUI;

public class StudentFormController {

    public TextField txtNIC;
    public TextField txtFullName;
    public TextField txtFullName1;
    public TextField txtFullName11;

    public void initialize() {
        MaterialUI.paintTextFields(txtFullName, txtNIC, txtFullName1, txtFullName11);
    }
}
