package com.neu.fac.view.product;

import com.neu.fac.controller.ProductController;
import com.neu.fac.controller.ProductTypeController;
import com.neu.fac.pojo.ProductEntity;
import com.neu.fac.pojo.ProductTypeEntity;
import com.neu.fac.utils.DataCheckUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class AddType {
    private ProductTypeController productTypeController = ProductTypeController.getInstance();
    private ProductController productController = ProductController.getInstance();
    @FXML
    private TextField typrText;

    @FXML
    private Button okk;

    @FXML
    private Button noo;

    @FXML
    void yes(ActionEvent event) {
        ProductTypeEntity productTypeEntity = new ProductTypeEntity();
        productTypeEntity.setType(typrText.getText()== null ? "" : typrText.getText());

        //提示信息框初始化
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("添加种类");
        alert.setHeaderText("温馨提示:");
        //信息校验判断
        String message = DataCheckUtils.validateData(productTypeEntity);
        if (message == null) {
            List<ProductTypeEntity> productTypeEntities = productTypeController.getProductTpyeList();
            for(ProductTypeEntity p1:productTypeEntities){
                if(p1.getType().equals(typrText.getText())){
                    alert.setContentText("不能添加已经存在的产品类型!");
                    alert.showAndWait();
                    return;
                }
            }
            boolean flag = productTypeController.addType(productTypeEntity);
            if (flag) {
                alert.setContentText("添加成功!");
                alert.showAndWait();
                Stage stage = (Stage) okk.getScene().getWindow();
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
    void returnBack(ActionEvent event) {
        Stage stage = (Stage) noo.getScene().getWindow();
        stage.close();
    }

}

