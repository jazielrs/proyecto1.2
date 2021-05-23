/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.sun.javafx.logging.PlatformLogger.Level;
import domain.Career;
import domain.DoublyLinkedList;
import domain.ListException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Jaziel
 */
public class FXMLShowCareerController implements Initializable {
    
    DoublyLinkedList careerList;
    
    @FXML
    private TableView<Career> careerTable;
    private TableColumn<Career, Integer> columID;
    private TableColumn<Career, String> columDescription;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private TableColumn<?, ?> ID;
    @FXML
    private TableColumn<?, ?> Description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        careerList = util.Utility.getDoublyList();
        try {
        this.careerTable.getColumns().clear();    
            
        ObservableList<Career> list = FXCollections.observableArrayList(convertListToArray());
        
        this.columID = new TableColumn("ID");
        this.columID.setMinWidth(249);
        this.columID.setCellValueFactory(new PropertyValueFactory<Career, Integer>("id"));
 
        this.columDescription= new TableColumn("Description");
        this.columDescription.setMinWidth(250);
        this.columDescription.setCellValueFactory(new PropertyValueFactory<Career, String>("description"));
      
        this.careerTable.setItems(list);
        this.careerTable.getColumns().addAll(columID,columDescription); 
        } catch (ListException ex) {
            Logger.getLogger(FXMLShowCareerController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }  
  
    public Career[] convertListToArray() throws ListException {
        Career[] array = new Career[careerList.size()];
        for (int i = 1; i <= careerList.size(); i++) {
            array[i - 1] = (Career) careerList.getNode(i).data;
        }
        return array;
    }
    
    
}
