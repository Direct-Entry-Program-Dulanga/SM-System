package controller;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.StudentService;
import util.MaterialUI;

public class SearchStudentsFormController {

    public TextField txtQuery;
    public TableView tblResults;

    private StudentService studentService = new StudentService();

    public void initialize(){
        MaterialUI.paintTextFields(txtQuery);
        tblResults.getItems().add(new Object());
        tblResults.getItems().add(new Object());
        tblResults.getItems().add(new Object());
        tblResults.getItems().add(new Object());
    }
}
