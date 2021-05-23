/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.FileManage;
import domain.SinglyLinkedList;
import domain.Student;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    SinglyLinkedList list = new SinglyLinkedList();
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
            System.out.println("list" + list.toString());
//            mail.run(studentEmail.getText(), "Información de Matricula", "Felicidades "+ studentName.getText() +" "+ studentFirstName.getText()+ ".\nNúmero de cédula: "+studentIdPerson.getText()+"\nUsted ha sido admitido por la Universidad de Costa Rica\n"
//                    + "En la carrera con la identificación: "+studentIdCarrer.getValue()+"\nTú número de Carnet es: "+studentIdCarne.getText()+"\nAhora Formas Parte de esta Gran comunidad Universitaria\n");
//            outMessage("Se ha registrado un nuevo estudiante");
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
        listObservableList.addAll(12, 12, 12);//Cargar Enlaces de datos del módulo de carreras
        studentIdCarrer.getItems().addAll(listObservableList);
    }

}
