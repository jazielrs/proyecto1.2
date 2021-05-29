/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.DoublyLinkedList;
import domain.ListException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author paovv
 */
public class FXMLModifyCoursesController implements Initializable {

    CircularDoublyLinkedList coursesList;
    DoublyLinkedList careerList;

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Text txtCourseID;
    @FXML
    private Text txtCourseName;
    @FXML
    private Text txtCredits;
    @FXML
    private Text txtCareerID;
    @FXML
    private Text txtCareer;
    @FXML
    private TextField txtFieldCourseID;
    @FXML
    private TextField txtFieldCourseName;
    @FXML
    private TextField txtFieldCredits;
    @FXML
    private TextField txtFieldCareerID;
    @FXML
    private ComboBox comboCareer;
    @FXML
    private Button btnClean;
    @FXML
    private ComboBox comboChooseID;
    @FXML
    private Button btnModifyCourse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            coursesList = util.Utility.getCircularDoublyList();
            careerList = util.Utility.getDoublyList();
            ObservableList<String> list = FXCollections.observableArrayList(convertListToArray());
            comboCareer.setItems(list);
            
            ObservableList<String> list1 = FXCollections.observableArrayList(convertIDCoursesToArray());
            comboChooseID.setItems(list1);
        } catch (ListException ex) {
            Logger.getLogger(FXMLAddCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String[] convertListToArray() throws ListException {
        String[] array = new String[careerList.size()];
        for (int i = 1; i <= careerList.size(); i++) {
            array[i - 1] = String.valueOf(careerList.getNode(i).data);
        }
        return array;
    }

    public String[] convertIDCoursesToArray() throws ListException {
        String[] a = new String[coursesList.size()];

        Course course = new Course();
        for (int i = 1; i <= coursesList.size(); i++) {
            course = (Course) coursesList.getNode(i).data;
            a[i-1] = course.getId();
        }
        return a;
    }
    
    @FXML
    private void btnModifyCourse(ActionEvent event) {
    }

    @FXML
    private void btnClean(ActionEvent event) {
    }

    @FXML
    private void comboChooseID(ActionEvent event) throws ListException {

        String output = (String) this.comboChooseID.getSelectionModel().getSelectedItem();

        this.txtCourseID.setVisible(true);
        this.txtCourseName.setVisible(true);
        this.txtCredits.setVisible(true);
        this.txtCareer.setVisible(true);
        this.txtCareerID.setVisible(true);
        this.txtFieldCourseID.setVisible(true);
        this.txtFieldCourseName.setVisible(true);
        this.txtFieldCredits.setVisible(true);
        this.comboCareer.setVisible(true);
        this.txtFieldCareerID.setVisible(true);

        Course course = new Course();
        for (int i = 1; i <= coursesList.size(); i++) {
            course = (Course) coursesList.getNode(i).data;
            if (util.Utility.equals(course.getCarrerID(), output)) {
                this.txtFieldCourseID.setText(course.getId());
                this.txtFieldCourseName.setText(course.getName());
                this.txtFieldCredits.setText(String.valueOf(course.getCredits()));
            }
        }
    }

    

}
