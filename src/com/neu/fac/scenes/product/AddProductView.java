package com.neu.fac.scenes.product;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddProductView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("添加产品");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
