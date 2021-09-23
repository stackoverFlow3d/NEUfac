package com.neu.fac.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChoiceBidView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("choicBid.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("选标");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
