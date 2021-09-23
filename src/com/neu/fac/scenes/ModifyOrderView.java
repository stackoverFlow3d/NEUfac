package com.neu.fac.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModifyOrderView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("modifyOrder.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("修改订单");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
