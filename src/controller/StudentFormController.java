package controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import javafx.util.converter.DateTimeStringConverter;
import javafx.util.converter.FormatStringConverter;
import model.Student;
import model.StudentTM;
import service.StudentService;
import util.MaterialUI;

import java.text.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class StudentFormController {

    public TextField txtNIC;
    public TextField txtFullName;
    public TextField txtAddress;
    public TextField txtContactNumber;
    public TextField txtEmail;
    public TextField txtDOB;
    public JFXButton btnSave;
    public Label lblTitle;
    public Label lblAge;
    public AnchorPane root;
    public ImageView imgLogo;

    private StudentService studentService = new StudentService();

    public void initialize() {
        MaterialUI.paintTextFields(txtNIC, txtFullName, txtAddress, txtDOB, txtContactNumber, txtEmail);

        Platform.runLater(()->{

            if (root.getUserData() != null){
                StudentTM tm = (StudentTM) root.getUserData();
                Student student = studentService.findStudent(tm.getNic());

                txtNIC.setEditable(false);
                txtNIC.setText(student.getNic());
                txtFullName.setText(student.getFullName());
                txtAddress.setText(student.getAddress());
                txtContactNumber.setText(student.getContact());
                txtEmail.setText(student.getEmail());
                txtDOB.setText(student.getDateOfBirth().toString());

                btnSave.setText("UPDATE STUDENT");
                lblTitle.setText("Update Student");
                imgLogo.setImage(new Image("/view/assets/Update Student.png"));
            }
        });

        setMaxLength(txtDOB, 10);
        setMaxLength(txtContactNumber, 11);

        txtDOB.textProperty().addListener((observable, oldValue, newValue) -> {

            if (txtDOB.getLength() == 10){
//                try {
//                    Date dob = parseDate(txtDOB.getText());
//                    Date today = new Date();
//
//                    long diff = today.getTime() - dob.getTime();
//                    double year = diff / (1000 * 60 * 60 * 24 * 365.0) ;
//                    System.out.println("Year: " + year);

                    LocalDate dob2 = LocalDate.parse(txtDOB.getText());
                    Period between = Period.between(dob2, LocalDate.now());

                    lblAge.setText(between.getYears() + " Years and " + between.getMonths() + " Months " + between.getDays() + " Days old");

//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
            }

        });
    }

//    private Date parseDate(String input) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        return sdf.parse(input);
//    }

    private void setMaxLength(TextField txt, int maxLength){
        txt.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength){
                txt.setText(txt.getText(0,maxLength));
            }
        });
    }

    public void txtDOB_OnKeyTyped(KeyEvent keyEvent) {

        if (keyEvent.getCharacter().equals("-") && (txtDOB.getText().length() == 4 || txtDOB.getText().length() == 7)){
            return;
        }

        if (!Character.isDigit(keyEvent.getCharacter().charAt(0))){
            keyEvent.consume();     // This is not going to forward to the Java FX Runtime Env.
            return;
        }

        if ((txtDOB.getText().length() == 4 || txtDOB.getText().length() == 7) && (txtDOB.getCaretPosition() == txtDOB.getLength())){
            txtDOB.appendText("-");
            txtDOB.positionCaret(txtDOB.getText().length() +1);
        }
    }

    public void txtContactNumber_OnKeyTyped(KeyEvent keyEvent) {
        if (keyEvent.getCharacter().equals("-") && (txtContactNumber.getText().length() == 3)){
            return;
        }

        if (!Character.isDigit(keyEvent.getCharacter().charAt(0))){
            keyEvent.consume();     // This is not going to forward to the Java FX Runtime Env.
            return;
        }

        if ((txtContactNumber.getText().length() == 3) && (txtContactNumber.getCaretPosition() == txtContactNumber.getLength())){
            txtContactNumber.appendText("-");
            txtContactNumber.positionCaret(txtContactNumber.getText().length() +1);
        }
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        try {
            Student student = new Student(txtNIC.getText(),
                    txtFullName.getText(),
                    txtAddress.getText(),
                    LocalDate.parse(txtDOB.getText()),
                    txtContactNumber.getText(),
                    txtEmail.getText());

            if (btnSave.getText().equals("ADD NEW STUDENT")){
                studentService.saveStudent(student);
            }else{
                StudentTM tm = (StudentTM) root.getUserData();
                tm.setFullName(txtFullName.getText());
                tm.setAddress(txtAddress.getText());
                studentService.updateStudent(student);
            }
            new Alert(Alert.AlertType.NONE, "Student has been saved successfully", ButtonType.OK).show();
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR, "Failed to save the student", ButtonType.OK).show();
        }
    }
}
