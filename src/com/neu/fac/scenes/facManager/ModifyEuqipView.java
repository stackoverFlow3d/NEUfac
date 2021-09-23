package com.neu.fac.scenes.facManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModifyEuqipView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("modifyFacEquip.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("修改设备");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
