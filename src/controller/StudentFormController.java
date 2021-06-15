package controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Student;
import model.StudentTM;
import service.StudentService;
import util.MaterialUI;

import java.time.LocalDate;
import java.time.Period;

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


    public void ValidateNIC(){
        String nic = txtNIC.getText();
        if(!(nic.length()== 10 || nic.length() == 12)){
            new Alert(Alert.AlertType.ERROR, "Invalid NIC").show();
            return;
        }
        if(nic.length() == 10){
            if(isInteger(nic.substring(0,9)) || nic.endsWith("V") || nic.endsWith("v") ){
                new Alert(Alert.AlertType.INFORMATION, "Valid Old NIC", ButtonType.OK).show();
                return;
            }
        }else if(nic.length() == 12 || isInteger(nic)){
            new Alert(Alert.AlertType.INFORMATION, "Valid new NIC", ButtonType.OK).show();
            return;
        }
        new Alert(Alert.AlertType.ERROR, "Invalid NIC").show();
    }


    public void ValidateName(){
        String name = txtFullName.getText();
        if((name.length()<3 || name.length()>20)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name1").show();
            return;
        }else if (name.startsWith(" ") || name.startsWith(".")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name2").show();
            return;
        }else if(name.chars().allMatch(Character::isLetter)){
            new Alert(Alert.AlertType.INFORMATION,"Valid name").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid Name3").show();
        }

    }

    public void ValidateAddress(){
        String addr = txtFullName.getText();
        if((addr.length()<3 || addr.length()>20)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name1").show();
            return;
        }

        if(addr.startsWith(" ") || addr.startsWith(".")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name2").show();
            return;
        }
        if(addr.chars().allMatch(Character::isLetter)){
            new Alert(Alert.AlertType.INFORMATION,"Valid name").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid Name3").show();
        }

    }

    public void ValidateEmail(){
        String email = txtAddress.getText();
        if((email.length()<4)) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email1").show();
            return;
        }

        if (email.startsWith(" ") || email.startsWith(".") || email.endsWith(".")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Email2").show();
            return;
        }

        if(email.matches("^[a-zA-Z0-9_.]+@[a-zA-Z0-9.-]+$")){
            new Alert(Alert.AlertType.INFORMATION,"Valid Email").show();
            return;
        }
        new Alert(Alert.AlertType.ERROR, "Invalid Email3").show();

    }

    public void AddButton_ONAction(MouseEvent mouseEvent) {
//        ValidateNIC();
//        ValidateName();
        ValidateEmail();

    }

    private boolean isInteger(String nic) {
        try{
            Integer.parseInt(nic);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        try {
            Student student = new Student(

                    txtNIC.getText(),
                    txtFullName.getText(),
                    txtAddress.getText(),
                    LocalDate.parse(txtDOB.getText()),
                    txtContactNumber.getText(),
                    txtEmail.getText());


            if (btnSave.getText().equals("ADD NEW STUDENT") ){
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
