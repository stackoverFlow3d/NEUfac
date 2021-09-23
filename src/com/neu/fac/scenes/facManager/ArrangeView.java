package com.neu.fac.scenes.facManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ArrangeView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("arrange.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("安排生产");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
