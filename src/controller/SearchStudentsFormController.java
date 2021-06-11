package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;
import model.StudentTM;
import service.StudentService;
import util.MaterialUI;

import java.util.List;

public class SearchStudentsFormController {

    public TextField txtQuery;
    public TableView<StudentTM> tblResults;

    private StudentService studentService = new StudentService();

    public void initialize(){
        MaterialUI.paintTextFields(txtQuery);

        tblResults.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblResults.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tblResults.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        txtQuery.textProperty().addListener((observable, oldValue, newValue) -> loadAllStudents(newValue));

        loadAllStudents(null);
    }

    private void loadAllStudents(String query){
        tblResults.getItems().clear();

        for (Student student : studentService.findStudents(query == null || query.trim().isEmpty() ? "": query)) {
            tblResults.getItems().add(new StudentTM(student.getNic(), student.getFullName(), student.getAddress()));
        }
    }
}
