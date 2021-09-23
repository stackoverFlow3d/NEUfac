package com.neu.fac.scenes.equipment;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EquipTypeView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("equipmentTypeManager.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("设备类型管理");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
