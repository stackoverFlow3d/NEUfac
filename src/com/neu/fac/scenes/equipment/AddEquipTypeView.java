package com.neu.fac.scenes.equipment;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddEquipTypeView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addEquipType.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("添加设备种类");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
