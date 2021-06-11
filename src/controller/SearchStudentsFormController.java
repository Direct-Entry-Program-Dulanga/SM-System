package controller;

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

        loadAllStudents();
        loadAllStudents();
    }

    private void loadAllStudents(){
        tblResults.getItems().clear();

        for (Student student : studentService.findAllStudents()) {
            tblResults.getItems().add(new StudentTM(student.getNic(), student.getFullName(), student.getAddress()));
        }
    }
}
