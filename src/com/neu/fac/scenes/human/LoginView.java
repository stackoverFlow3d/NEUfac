package com.neu.fac.scenes.human;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginView {
    //导入css
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("登录");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
