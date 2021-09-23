package com.neu.fac.scenes.facManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BorrowView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("equipBorrower.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("租用设备");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
