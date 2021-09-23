package com.neu.fac.scenes.facManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FacManagerView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("facManager.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("云工厂管理");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
