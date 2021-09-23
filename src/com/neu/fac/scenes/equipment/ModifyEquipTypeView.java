package com.neu.fac.scenes.equipment;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModifyEquipTypeView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("modifyEquipType.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("修改设备类型");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
