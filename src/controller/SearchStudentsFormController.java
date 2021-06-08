package controller;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.MaterialUI;

public class SearchStudentsFormController {

    public TextField txtQuery;
    public TableView tblResults;

    public void initialize(){
        MaterialUI.paintTextFields(txtQuery);
    }
}
