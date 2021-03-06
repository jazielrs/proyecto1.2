/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.FileManage;
import domain.Mail;
import domain.SinglyLinkedList;
import domain.Student;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Cristopher.za
 */
public class FXMLAddStudentController implements Initializable {

    FileManage fileManage = new FileManage();
    SinglyLinkedList list;
    Mail mail = new Mail();
    ObservableList listObservableList = FXCollections.observableArrayList();
    @FXML
    private TextField studentAddress;
    @FXML
    private TextField studentName;
    @FXML
    private TextField studentFirstName;
    @FXML
    private TextField studentIdPerson;
    @FXML
    private TextField studentMovil;
    @FXML
    private TextField studentEmail;
    @FXML
    private TextField studentIdCarne;
    @FXML
    private ComboBox<Integer> studentIdCarrer;
    @FXML
    private DatePicker studentAge;
    @FXML
    private Button addButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        list = util.Utility.getSinglyList();
    }

    @FXML
    private void addButton(ActionEvent event) throws InterruptedException {

        Date birthday = Date.from(this.studentAge.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

        try {
            Student student = new Student(Integer.parseInt(studentIdPerson.getText()), studentIdCarne.getText(),
                    studentFirstName.getText(), studentName.getText(), birthday,
                    studentMovil.getText(), studentEmail.getText(), studentAddress.getText(),
                    studentIdCarrer.getValue());
            list.add(student);
            mail.run(studentEmail.getText(), "Informaci??n de Matricula", "Felicidades "+ studentName.getText() +" "+ studentFirstName.getText()+ ".\nN??mero de c??dula: "+studentIdPerson.getText()+"\nUsted ha sido admitido por la Universidad de Costa Rica\n"
                    + "En la carrera con la identificaci??n: "+studentIdCarrer.getValue()+"\nT?? n??mero de Carnet es: "+studentIdCarne.getText()+"\nAhora Formas Parte de esta Gran comunidad Universitaria\n");
            outMessage("Se ha registrado un nuevo estudiante");
        } catch (NumberFormatException err) {
            outMessage("Ha ingresado datos incorrectos (NumberFormatException)");
        }

    }

    public void outMessage(String content) {
        Alert out = new Alert(Alert.AlertType.INFORMATION);
        out.setTitle("Alerta");
        out.setContentText(content);
        out.show();
    }

    private void loadData() {
        listObservableList.removeAll(listObservableList);
        listObservableList.addAll(12, 12, 12);//Cargar Enlaces de datos del m??dulo de carreras
        studentIdCarrer.getItems().addAll(listObservableList);
    }

}
