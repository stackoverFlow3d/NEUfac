package com.neu.fac.view;

import com.neu.fac.controller.OrderController;
import com.neu.fac.controller.ProductController;
import com.neu.fac.pojo.OrderEntity;
import com.neu.fac.pojo.UserEntity;
import com.neu.fac.utils.DataCheckUtils;
import com.neu.fac.utils.DataTrans;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ModifyOrder {
    private OrderController orderController = OrderController.getInstance();
    private ProductController productController = ProductController.getInstance();
    private OrderEntity orderEntity = DataTrans.getOrderEntity();
    @FXML
    private Button cancelButton;

    @FXML
    private TextField addressPick;

    @FXML
    private TextField amountText;

    @FXML
    private ChoiceBox<String> productChoice;

    @FXML
    private Button confrimButton;

    @FXML
    private DatePicker deadlinePick;

    @FXML
    private DatePicker finsishPick;

    @FXML
    void confrim(ActionEvent event) throws IOException {
        orderEntity.setProductName(productChoice.getSelectionModel().getSelectedItem());
        orderEntity.setAmount(amountText.getText());
        orderEntity.setFinsishDay(finsishPick.getValue().toString());
        orderEntity.setDeadLine(deadlinePick.getValue().toString());
        orderEntity.setAcpAddress(addressPick.getText());

        //提示信息框初始化
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("修改订单");
        alert.setHeaderText("温馨提示:");
        //信息校验判断
        String message = DataCheckUtils.validateData(orderEntity);
        if (message == null) {
            boolean flag = orderController.modifyOrder(orderEntity.getId(),orderEntity);
            if (flag) {
                alert.setContentText("修改成功!");
                alert.showAndWait();
                Stage stage = (Stage) confrimButton.getScene().getWindow();
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
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void initialize(){
        addressPick.setText(orderEntity.getAcpAddress());
        amountText.setText(orderEntity.getAmount());
        LocalDate localDate = LocalDate.parse(orderEntity.getFinsishDay(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate localDate1 = LocalDate.parse(orderEntity.getDeadLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        finsishPick.setValue(localDate);
        deadlinePick.setValue(localDate1);
        ObservableList<String> data = FXCollections.observableList(productController.getProductStringList());
        productChoice.setValue(orderEntity.getProductName());
        productChoice.setItems(data);
    }
}
