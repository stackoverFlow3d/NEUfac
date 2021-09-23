package com.neu.fac.view.human;

import com.neu.fac.controller.UserController;
import com.neu.fac.pojo.UserEntity;
import com.neu.fac.utils.DataCheckUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddUser {

    @FXML
    private TextField id_text;

    @FXML
    private RadioButton rbb;

    @FXML
    private Button cancelButtton;

    @FXML
    private TextField phone_text;

    @FXML
    private RadioButton rbf;

    @FXML
    private TextField facNam_text;

    @FXML
    private TextField name_text;

    @FXML
    private TextField description_text;

    @FXML
    private TextField password_text;

    @FXML
    private Button confromButtonButton;

    private UserController userController = UserController.getInstance();

    @FXML
    void conform(ActionEvent event) {
        UserEntity user = new UserEntity();
        boolean flag;
        user.setUserName(id_text.getText() == null ? "" : id_text.getText());
        user.setPassWord(password_text.getText() == null ? "" : password_text.getText());
        user.setPhone(phone_text.getText() == null ? "" : phone_text.getText());
        user.setName(name_text.getText() == null ? "" : name_text.getText());
        if (rbf.isSelected()) {
            user.setPower(rbf.getText());
            user.setDiscription(description_text.getText() == null ? "" : description_text.getText());
            user.setFacName(facNam_text.getText() == null ? "" : facNam_text.getText());
        } else if (rbb.isSelected()) {
            user.setPower(facNam_text.getText());
            user.setDiscription("");
            user.setFacName("");
        }
        user.setState("关停");
        //提示信息框初始化
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("注册");
        alert.setHeaderText("温馨提示:");
        //信息校验判断
        String message = DataCheckUtils.validateData(user);
        if (message == null) {
            flag = userController.register(user);
            if (flag) {
                alert.setContentText("用户添加成功!");
                alert.showAndWait();
                Stage stage = (Stage) confromButtonButton.getScene().getWindow();
                stage.close();
            } else {
                alert.setContentText("添加失败,请稍后再试.");
                alert.showAndWait();
            }
        } else {
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage) confromButtonButton.getScene().getWindow();
        stage.close();
    }

    public void initialize() {

        final ToggleGroup group = new ToggleGroup();

        rbf.setToggleGroup(group);

        rbb.setToggleGroup(group);

        //设置单选按钮
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {

            }
        });

    }
}
