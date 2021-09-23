package com.neu.fac.view.product;

import com.neu.fac.controller.ProductController;
import com.neu.fac.controller.ProductTypeController;
import com.neu.fac.pojo.ProductEntity;
import com.neu.fac.utils.DataCheckUtils;
import com.neu.fac.utils.DataTrans;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyProduct {
    private ProductTypeController productTypeController = ProductTypeController.getInstance();
    private ProductController productController = ProductController.getInstance();
    private ProductEntity product = DataTrans.getProductEntity();
    @FXML
    private ChoiceBox<String> typeChoice;

    @FXML
    private TextField sizeText;

    @FXML
    private Button cancelbutton;

    @FXML
    private Button comfrimButtom;

    @FXML
    private TextField desText;

    @FXML
    private TextField nameText;

    @FXML
    void comfrim(ActionEvent event) throws IOException {
        product.setType(typeChoice.getSelectionModel().getSelectedItem());
        product.setName(nameText.getText());
        product.setDescription(desText.getText());
        product.setSize(sizeText.getText());

        //提示信息框初始化
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("添加产品");
        alert.setHeaderText("温馨提示:");
        //信息校验判断
        String message = DataCheckUtils.validateData(product);
        if (message == null) {
            boolean flag = productController.modifyProduct(product.getId(),product);
            if (flag) {
                alert.setContentText("添加成功!");
                alert.showAndWait();
                Stage stage = (Stage) comfrimButtom.getScene().getWindow();
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
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelbutton.getScene().getWindow();
        stage.close();
    }

    public void initialize(){
        ObservableList<String> data = FXCollections.observableList(productTypeController.getTpyeStringList());
        nameText.setText(product.getName());
        desText.setText(product.getDescription());
        sizeText.setText(product.getSize());
        typeChoice.setValue(product.getType());
        typeChoice.setItems(data);
    }

}
