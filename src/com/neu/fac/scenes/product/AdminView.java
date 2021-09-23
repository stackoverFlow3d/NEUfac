package com.neu.fac.scenes.product;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("东软智能云制造管理系统");
        stage.setScene(scene);
        stage.show();
    }
}
