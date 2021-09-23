package com.neu.fac.scenes.product;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProductMangerView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("productmanager.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("产品管理");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
