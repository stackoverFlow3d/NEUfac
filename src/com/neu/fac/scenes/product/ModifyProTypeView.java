package com.neu.fac.scenes.product;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModifyProTypeView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("modifyProType.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("修改产品类别");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
