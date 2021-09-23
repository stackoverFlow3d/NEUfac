package com.neu.fac.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
    FXMLLoader fxmlLoader = new FXMLLoader();
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../scenes/human/login.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("东软智能云制造平台");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
