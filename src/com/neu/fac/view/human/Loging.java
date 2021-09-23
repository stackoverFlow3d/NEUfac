package com.neu.fac.view.human;

import com.neu.fac.controller.UserController;
import com.neu.fac.pojo.UserEntity;
import com.neu.fac.scenes.BusinessOrderView;
import com.neu.fac.scenes.facManager.FacManagerView;
import com.neu.fac.scenes.product.AdminView;
import com.neu.fac.scenes.human.RegisterView;
import com.neu.fac.utils.DataTrans;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Loging {

    private RegisterView registerView = new RegisterView();
    private AdminView adminView = new AdminView();
    private FacManagerView facManagerView = new FacManagerView();
    private BusinessOrderView businessOrderView = new BusinessOrderView();
    @FXML
    private TextField passWordText;

    @FXML
    private Button registerButton;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane id;

    @FXML
    private TextField userNameText;
    private UserController userController = UserController.getInstance();
    @FXML
    void loginButton(ActionEvent event) throws Exception {
        String username = userNameText.getText();
        String password = passWordText.getText();
        UserEntity loginUser = userController.login(username, password);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("登录");
        alert.setHeaderText("温馨提示:");
        if (loginUser == null) {
            alert.setContentText("登录失败,密码或用户名不正确,请您重试");
            alert.showAndWait();
        } else {
            alert.setContentText("登录成功!");
            alert.showAndWait();
            DataTrans.setUserDate(loginUser);
            if ("admin".equals(loginUser.getUserName())) {
                openAdminView();
            } else if(loginUser.getPower().equals("云工厂")){
                Stage stage4 = (Stage) loginButton.getScene().getWindow();
                facManagerView.start(stage4);
            }else if(loginUser.getPower().equals("经销商")){
                Stage stage5 = (Stage) loginButton.getScene().getWindow();
                businessOrderView.start(stage5);
            }
        }
    }
    //打开管理员场景
    private void openAdminView() throws Exception {
        Stage stage3 = (Stage) loginButton.getScene().getWindow();
        adminView.start(stage3);
    }
    //检测按钮并且打开注册场景
    @FXML
    void registerButton(ActionEvent event) throws Exception {
        Stage stage2 = (Stage) registerButton.getScene().getWindow();
        registerView.start(stage2);
    }

    @FXML
    void initialize() {

    }
}

