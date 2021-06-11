package controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
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
        TableColumn<StudentTM, HBox> lastCol = (TableColumn<StudentTM, HBox>) tblResults.getColumns().get(3);

        lastCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StudentTM, HBox>, ObservableValue<HBox>>() {
            @Override
            public ObservableValue<HBox> call(TableColumn.CellDataFeatures<StudentTM, HBox> param) {
                ImageView imgEdit = new ImageView("/view/assets/Edit.png");
                ImageView imgTrash = new ImageView("/view/assets/Trash.png");

                imgEdit.getStyleClass().add("action-icons");
                imgTrash.getStyleClass().add("action-icons");

                return new ReadOnlyObjectWrapper<>(new HBox(10, imgEdit, imgTrash));
            }
        });

        txtQuery.textProperty().addListener((observable, oldValue, newValue) -> loadAllStudents(newValue));

        loadAllStudents("");
    }

    private void loadAllStudents(String query){
        tblResults.getItems().clear();

        for (Student student : studentService.findStudents(query)) {
            tblResults.getItems().add(new StudentTM(student.getNic(), student.getFullName(), student.getAddress()));
        }
    }
}
