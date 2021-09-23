package com.neu.fac.scenes.human;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.TableView;

public class AddUserView {
    public void start(Stage window) throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("addUser.fxml"));
        Scene scene =  new Scene(root);
        stage.setTitle("添加用户");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
