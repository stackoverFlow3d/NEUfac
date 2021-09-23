package com.neu.fac.view.product;

import com.neu.fac.controller.ProductController;
import com.neu.fac.controller.ProductTypeController;
import com.neu.fac.pojo.ProductTypeEntity;
import com.neu.fac.utils.DataCheckUtils;
import com.neu.fac.utils.DataTrans;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyProductType {
    private ProductTypeController productTypeController = ProductTypeController.getInstance();
    private ProductTypeEntity productType = DataTrans.getProductTypeEntity();
    private String old;
    private String newType;
    private ProductController productController = ProductController.getInstance();
    @FXML
    private TextField typrText;

    @FXML
    private Button okk;

    @FXML
    private Button noo;

    @FXML
    void yes(ActionEvent event) throws IOException {
        productType.setType(typrText.getText()== null ? "" : typrText.getText());
        newType = productType.getType();
        //提示信息框初始化
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("修改种类");
        alert.setHeaderText("温馨提示:");
        //信息校验判断
        String message = DataCheckUtils.validateData(productType);
        if (message == null) {
            boolean flag = productTypeController.modifyProducttype(productType.getId(),productType);
            if (flag) {
                productController.update(old,newType);
                alert.setContentText("修改成功!");
                alert.showAndWait();
                Stage stage = (Stage) okk.getScene().getWindow();
                stage.close();
            } else {
                alert.setContentText("修改失败,请稍后再试.");
                alert.showAndWait();
            }
        } else {
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

    @FXML
    void returnBack(ActionEvent event) {
        Stage stage = (Stage) noo.getScene().getWindow();
        stage.close();
    }

    public void initialize(){
        old = productType.getType();
        typrText.setText(productType.getType());
    }

}
