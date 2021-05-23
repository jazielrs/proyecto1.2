/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import domain.DoublyLinkedList;
import domain.Career;
import domain.FileManage;
import domain.ListException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLAddCareerController implements Initializable {

    DoublyLinkedList careerList;
    int id = 0;
    @FXML
    private TextField txtFieldCareerDescription;
    @FXML
    private TextField txtFieldCareerID;
    @FXML
    private Button btnAddCareer;
    @FXML
    private Button btnClear;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.careerList = util.Utility.getDoublyList();
    }

    @FXML
    private void btnAddCareer(ActionEvent event) throws IOException, ListException {
        
        FileManage fileManage = new FileManage();
        if (!"".equals(this.txtFieldCareerDescription.getText())) {
            Career career = new Career(this.txtFieldCareerDescription.getText());
            if (!careerList.isEmpty()) {//Hay al menos una carrera ingresada
                Career auxCareer;
                boolean existCareer = false;
                for (int i = 1; i <= careerList.size(); i++) {
                    auxCareer = (Career) careerList.getNode(i).data;//Crea nueva carreray amenta id
                    if (career.getDescription().equalsIgnoreCase(auxCareer.getDescription())) {
                        existCareer = true;
                    }
                }
                if (!existCareer) {
                    careerList.add(career);
                    fileManage.addCareer(career);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Add Career");
                    alert.setHeaderText("Carreer");
                    alert.setContentText("New career added");
                    alert.showAndWait();
                }
            }else {//Lista de carreras vacia
                careerList.add(career);
                fileManage.addCareer(career);
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Alert");
            alert.setContentText("Please, insert a career");
            alert.showAndWait();
        }
    }

    @FXML
    private void btnClear(ActionEvent event) {
        this.txtFieldCareerDescription.setText("");
    }

}
