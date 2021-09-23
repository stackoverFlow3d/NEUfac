package com.neu.fac.scenes.equipment;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddEquipmentView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addEquipment.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("添加设备");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
