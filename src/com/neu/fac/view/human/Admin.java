package com.neu.fac.view.human;
import com.neu.fac.scenes.equipment.EquipTypeView;
import com.neu.fac.scenes.equipment.EquipmentView;
import com.neu.fac.scenes.product.ProductMangerView;
import com.neu.fac.scenes.human.FacMangerView;
import com.neu.fac.scenes.product.ProductTypeManagerView;
import com.neu.fac.scenes.human.UserMangerView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Admin {
     private UserMangerView userMangerView = new UserMangerView();
     private FacMangerView facMangerView = new FacMangerView();
     private ProductTypeManagerView productTypeManagerView = new ProductTypeManagerView();
     private ProductMangerView productMangerView= new ProductMangerView();
     private EquipTypeView equipTypeView = new EquipTypeView();
     private EquipmentView equipmentView = new EquipmentView();
    @FXML
    private Button proTypeButton;

    @FXML
    private Button facMesButton;

    @FXML
    private Button proMesButton;

    @FXML
    private Button euipTypeButton;

    @FXML
    private Button euipMangeButton;

    @FXML
    private Button userMangeButton;

    @FXML
    void userMange(ActionEvent event) throws Exception {
        Stage userMangeStage = (Stage) userMangeButton.getScene().getWindow();
        userMangerView.start(userMangeStage);
    }

    @FXML
    void facMes(ActionEvent event) throws Exception {
        Stage facMesStage = (Stage) facMesButton.getScene().getWindow();
        facMangerView.start(facMesStage);
    }

    @FXML
    void proType(ActionEvent event) throws Exception {
        Stage proTypeStage = (Stage) proTypeButton.getScene().getWindow();
        productTypeManagerView.start(proTypeStage);
    }

    @FXML
    void proMes(ActionEvent event) throws Exception {
        Stage proMesStage = (Stage) proMesButton.getScene().getWindow();
        productMangerView.start(proMesStage);
    }

    @FXML
    void euipType(ActionEvent event) throws Exception {
        Stage euipTypeStage = (Stage) euipTypeButton.getScene().getWindow();
        equipTypeView.start(euipTypeStage);
    }

    @FXML
    void euipMange(ActionEvent event) throws Exception {
        Stage euipStage = (Stage) euipMangeButton.getScene().getWindow();
        equipmentView.start(euipStage);
    }



}

