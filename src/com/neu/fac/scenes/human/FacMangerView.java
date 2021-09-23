package com.neu.fac.scenes.human;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FacMangerView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("facManger.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("工厂管理");
        stage.setScene(scene);
        stage.show();
    }
}
