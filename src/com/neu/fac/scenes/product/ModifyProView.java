package com.neu.fac.scenes.product;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModifyProView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("modifyProduct.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("修改产品信息");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
